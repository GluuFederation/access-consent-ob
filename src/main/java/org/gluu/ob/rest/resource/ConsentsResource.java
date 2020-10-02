package org.gluu.ob.rest.resource;

import lombok.extern.slf4j.Slf4j;
import org.gluu.ob.exception.InternalError;
import org.gluu.ob.rest.model.ApiError;
import org.gluu.ob.rest.model.Consent;
import org.gluu.ob.rest.model.PostConsent;
import org.gluu.ob.rest.model.PutConsent;
import org.gluu.ob.service.ConsentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.gluu.ob.util.ApiConstants.BASE_API_URL;

@Path(BASE_API_URL + "/consents")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class ConsentsResource extends Resource {

    @Inject
    ConsentService consentService;

    @GET
    @Path("/{id}")
    public Response getConsent(@PathParam("id") String consentId) {
        Consent consent = consentService.getConsent(consentId);
        if (consent != null) {
            log.info("Get consent, id: {}", consentId);
            return Response.ok(consent).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ApiError("Consent not found.")).build();
        }
    }

    @POST
    public Response postConsent(PostConsent consent) {
        try {
            String clientId = getClientId();
            Consent newConsent = consentService.create(consent, clientId);
            return Response.status(201).entity(newConsent).build();
        } catch (InternalError internalError) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiError(internalError.getDescription())).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteConsent(@PathParam("id") String consentId) {
        try {
            consentService.revokeConsent(consentId);
            return Response.ok().build();
        } catch (InternalError internalError) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ApiError(internalError.getDescription())).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response putConsent(@PathParam("id") String consentId, PutConsent putConsent) {
        try {
            Consent consent = consentService.putConsentStatus(consentId, putConsent);
            return Response.ok(consent).build();
        } catch (InternalError internalError) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ApiError(internalError.getDescription())).build();
        }
    }

}
