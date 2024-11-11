package com.nasa.stocktrack.infra.gateways.security;

import com.nasa.stocktrack.application.gateways.TokenGateway;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

@Component
public class TokenService implements TokenGateway {

    private String secretKey = "93624b6ac3de8f0d54043c9d4c6827d2b921127dd58f2699610cf064e8659113";

    private long expiresIn = 86400000;

    @Override
    public String generateToken(String userId) {
        return createToken(new HashMap<>(), userId);
    }

    private String createToken(HashMap<String, Object> claims, String userId) {
        return Jwts
                .builder()
                .claims(claims)
                .subject(userId)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiresIn))
                .signWith(getSignInKey())
                .compact();
    }

    private Key getSignInKey() {
        byte[] bytes = Decoders.BASE64URL.decode(secretKey);

        return Keys.hmacShaKeyFor(bytes);
    }

}
