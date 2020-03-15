package com.mycompany.store.Services.CustomExceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RentableRentedMapper implements ExceptionMapper<RentableRentedException> {

    public static class ErrorMessage {
        public final String error;
        public ErrorMessage(String error) {
            this.error = error;
        }
    }

    @Override
    public Response toResponse(RentableRentedException exception) {
        return Response
                .status(Response.Status.FORBIDDEN)
                .entity("Rentable is rented")
                .build();
    }
}
