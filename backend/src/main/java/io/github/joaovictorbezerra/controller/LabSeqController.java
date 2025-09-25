package io.github.joaovictorbezerra.controller;

import io.github.joaovictorbezerra.constants.api.ApiConstants;
import io.github.joaovictorbezerra.dto.api.ApiResponse;
import io.github.joaovictorbezerra.service.LabSeqService;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

@Path(ApiConstants.LABSEQ_ENDPOINT)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LabSeqController {
    @Inject
    Logger logger;

    @Inject
    @Named(ApiConstants.LABSEQ_SERVICE_IMPL)
    LabSeqService labseqService;

    @GET
    @Path("/{n}")
    public Response computeLabSeq(@PathParam("n") int termNumber) {
        logger.infof("[computeLabSeq]:: Incoming request with term number %d", termNumber);
        var result = labseqService.computeLabSeq(termNumber);
        return Response
                .ok(new ApiResponse<>(result, "Successfully calculated", true))
                .build();
    }
}
