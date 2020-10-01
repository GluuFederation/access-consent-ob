package org.gluu.ob.rest.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.gluu.ob.rest.model.idp.IntrospectionResponse;

import javax.ws.rs.*;

@Path("/oxauth")
@RegisterRestClient(configKey = "idp")
public interface IdentityProviderService {

    @GET
    @Path("/restv1/introspection")
    @Produces("application/json")
    IntrospectionResponse callIntrospectionEndpoint(@HeaderParam("Authorization") String accessToken,
                                                    @QueryParam("token") String token);

}
