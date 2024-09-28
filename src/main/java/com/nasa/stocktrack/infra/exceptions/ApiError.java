package com.nasa.stocktrack.infra.exceptions;

public record ApiError(String pointer, String reason) {
}
