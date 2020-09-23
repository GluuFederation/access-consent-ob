package org.gluu.ob.exception;

import org.gluu.ob.rest.model.ApiError;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GlobalErrorHandler implements ExceptionMapper<Exception> {

    @Inject
    Logger log;

    public Response toResponse(Exception e) {
        if (e instanceof WebApplicationException && ((WebApplicationException) e).getResponse() != null) {
            return ((WebApplicationException) e).getResponse();
        }
        log.error(e.getMessage(), e);
        return Response
                .serverError()
                .entity(new ApiError.ErrorBuilder()
                        .withCode(String.valueOf(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()))
                        .withMessage("Internal Server error")
                        .andDescription("Internal error occurs, for more details please check log files.")
                        .build())
                .build();
    }
}
