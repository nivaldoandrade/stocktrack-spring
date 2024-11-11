package com.nasa.stocktrack.application.gateways;

public interface TokenGateway {
    String generateToken(String userId);
}
