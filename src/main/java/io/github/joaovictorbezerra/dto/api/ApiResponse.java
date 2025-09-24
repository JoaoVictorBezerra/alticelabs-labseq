package io.github.joaovictorbezerra.dto.api;

public record ApiResponse<T>(T data, String message, boolean success) {

}
