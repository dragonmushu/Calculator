package io.calculatorapi.exceptionhandler;

import io.confluent.rest.entities.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ServiceExceptionHandler implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e) {
        e.printStackTrace();

        var internalServerErrorCode = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
        return Response
            .serverError()
            .entity(new ErrorMessage(internalServerErrorCode, e.getMessage()))
            .build();
    }
}
