package org.gluu.ob.filters;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.gluu.ob.rest.client.IdentityProviderService;
import org.gluu.ob.rest.model.idp.IntrospectionResponse;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
@Priority(1)
@Slf4j
public class AuthorizationFilter implements ContainerRequestFilter {
    private static final String AUTHENTICATION_SCHEME = "Bearer";

    @Context
    UriInfo info;

    @Context
    HttpServletRequest request;

    @Context
    private HttpHeaders httpHeaders;

    @Context
    private ResourceInfo resourceInfo;

    @Inject
    @RestClient
    IdentityProviderService identityProviderService;

    public void filter(ContainerRequestContext context) {
        String authorizationHeader = context.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (!isTokenBasedAuthentication(authorizationHeader)) {
            log.info("Access denied for access token: {}", authorizationHeader);
            abortWithUnauthorized(context);
            return;
        }
        String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();
        try {
            validateToken(token, context);
        } catch (Exception e) {
            log.error("Problems processing access token", e);
            abortWithUnauthorized(context);
        }
    }

    private boolean isTokenBasedAuthentication(String authorizationHeader) {
        return authorizationHeader != null
                && authorizationHeader.toLowerCase().startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                .header(HttpHeaders.WWW_AUTHENTICATE, AUTHENTICATION_SCHEME).build());
    }

    private void validateToken(String token, ContainerRequestContext context) throws Exception {
        token = token.replace("Bearer ", "");
        IntrospectionResponse response = identityProviderService.callIntrospectionEndpoint("Bearer " + token, token);
        if (response.getErrorType() != null) {
            log.info("Introspection response: {}", response);
            throw new Exception("Invalid session.");
        } else {
            context.setProperty("introspection", response);
        }
    }

}
