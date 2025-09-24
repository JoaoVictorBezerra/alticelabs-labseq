package io.github.joaovictorbezerra.controller;

import io.github.joaovictorbezerra.constants.api.ApiConstants;
import io.github.joaovictorbezerra.dto.api.ApiResponse;
import io.github.joaovictorbezerra.dto.api.response.HealthCheckResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.time.Instant;

@Path(ApiConstants.MAIN_ENDPOINT)
public class ApiController {
    @Inject
    Logger logger;

    @GET
    public Response healthCheck() {
        logger.info("[healthCheck]:: Incoming request");
        return Response
                .ok(new ApiResponse<>(new HealthCheckResponse(Instant.now(), true), "API is working", true))
                .build();
    }
}
