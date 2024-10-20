package com.nasa.stocktrack.application.gateways;

public interface EncryptionGateway {

    String generateHash(String password);
}
