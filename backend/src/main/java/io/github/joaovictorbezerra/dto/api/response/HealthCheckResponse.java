package io.github.joaovictorbezerra.dto.api.response;

import java.time.Instant;

public record HealthCheckResponse(Instant timestamp, boolean apiStatus) {
}
