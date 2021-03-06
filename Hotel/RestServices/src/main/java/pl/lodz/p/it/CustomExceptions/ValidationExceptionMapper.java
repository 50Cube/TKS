package pl.lodz.p.it.CustomExceptions;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    public static class ErrorMessage {
        public final String error;
        public ErrorMessage(String error) {
            this.error = error;
        }
    }

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        return Response
                .status(Response.Status.FORBIDDEN)
                .entity(exception.getMessage())
                .build();
    }
}
