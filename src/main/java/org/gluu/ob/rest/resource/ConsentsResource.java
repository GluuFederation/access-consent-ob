package org.gluu.ob.rest.resource;

import lombok.extern.slf4j.Slf4j;
import org.gluu.ob.domain.entity.ConsentEntity;
import org.gluu.ob.domain.repository.ConsentRepository;
import org.gluu.ob.exception.InternalError;
import org.gluu.ob.rest.model.ApiError;
import org.gluu.ob.rest.model.Consent;
import org.gluu.ob.rest.model.PostConsent;
import org.gluu.ob.service.ConsentService;
import org.gluu.ob.util.converters.ConsentConverter;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Optional;

import static org.gluu.ob.util.ApiConstants.*;

@Path(BASE_API_URL + "/consents")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class ConsentsResource {

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
            Consent newConsent = consentService.create(consent);
            return Response.ok(newConsent).build();
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

}
