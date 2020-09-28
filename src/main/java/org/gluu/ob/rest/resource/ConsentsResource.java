package org.gluu.ob.rest.resource;

import lombok.extern.slf4j.Slf4j;
import org.gluu.ob.persistence.entity.ConsentEntity;
import org.gluu.ob.persistence.repository.ConsentRepository;
import org.gluu.ob.rest.model.Consent;
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
    private ConsentRepository consentRepository;

    @GET
    @Path("/{id}")
    public Response getConsent(@PathParam("id") String consentId) {
        log.info("Get consent, id: {}", consentId);
        Optional<ConsentEntity> consentOptional = consentRepository.findById(Long.parseLong(consentId));
        if (consentOptional.isPresent()) {
            Consent consent = ConsentConverter.toObject(consentOptional.get());
            return Response.ok(consent).build();
        } else {
            return Response.ok().build();
        }
    }

}
