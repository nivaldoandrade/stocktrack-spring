package com.nasa.stocktrack.infra.gateways.security;

import com.nasa.stocktrack.application.gateways.TokenGateway;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.Function;

@Component
public class TokenService implements TokenGateway {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expires-in}")
    private long expiresIn;

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

    private SecretKey getSignInKey() {
        byte[] bytes = Decoders.BASE64URL.decode(secretKey);

        return Keys.hmacShaKeyFor(bytes);
    }

    public boolean isTokenValid(String token) {
        Claims claims = extractAllClaims(token);

        return !claims.isEmpty();
    }

    public String extractSubject(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


}
