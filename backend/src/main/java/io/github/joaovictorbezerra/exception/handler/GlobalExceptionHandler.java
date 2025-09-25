package io.github.joaovictorbezerra.exception.handler;

import io.github.joaovictorbezerra.dto.api.ApiResponse;
import io.quarkus.logging.Log;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        return switch (exception) {
            case NotFoundException nfe -> handleNotFoundException(nfe);
            case IllegalArgumentException iae -> handleIllegalArgumentException(iae);
            default -> handleGenericException(exception);
        };
    }

    private Response handleNotFoundException(NotFoundException exception) {
        String message = exception.getMessage();

        if (isParameterConversionError(message)) {
            Log.errorf("[handleNotFoundException]:: Parameter conversion error: %s", message);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ApiResponse<>(null, "Parameter must be a valid number", false))
                    .build();
        }

        Log.errorf("[handleNotFoundException]:: Resource not found: %s", message);
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ApiResponse<>(null, "Endpoint not found", false))
                .build();
    }

    private Response handleIllegalArgumentException(IllegalArgumentException exception) {
        Log.errorf("[handleIllegalArgumentException]:: Validation error: %s", exception.getMessage());
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ApiResponse<>(null, exception.getMessage(), false))
                .build();
    }

    private Response handleGenericException(Exception exception) {
        Log.errorf("[handleGenericException]:: Internal server error: %s", exception.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ApiResponse<>(null, "Internal server error", false))
                .build();
    }

    private boolean isParameterConversionError(String message) {
        return message != null &&
                message.contains("Unable to extract parameter") &&
                message.contains("PathParam");
    }
}