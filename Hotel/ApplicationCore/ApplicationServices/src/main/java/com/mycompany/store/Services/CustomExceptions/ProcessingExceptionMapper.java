package com.mycompany.store.Services.CustomExceptions;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ProcessingExceptionMapper implements ExceptionMapper<ProcessingException> {

    @Override
    public Response toResponse(ProcessingException exception) {
        return Response
                .status(Response.Status.FORBIDDEN)
                .entity("JSON Format not valid")
                .build();
    }
}
