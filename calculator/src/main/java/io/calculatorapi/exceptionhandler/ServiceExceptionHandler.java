package io.calculatorapi.exceptionhandler;

import io.confluent.rest.entities.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ServiceExceptionHandler implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e) {
        e.printStackTrace();

        var badRequestStatusCode = Response.Status.BAD_REQUEST.getStatusCode();
        return Response
            .status(badRequestStatusCode)
            .entity(new ErrorMessage(badRequestStatusCode, e.getMessage()))
            .build();
    }
}
