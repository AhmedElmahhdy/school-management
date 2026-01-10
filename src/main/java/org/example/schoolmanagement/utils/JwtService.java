package org.example.schoolmanagement.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    private final String secret = "YourSuperSecretKeyMustBeAtLeast32CharactersLong12345678";
    private final long expiration = 3600000;

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(Map<String, Object> claims, Object subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, String username) {
        return extractUsername(token).equals(username) &&
                !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
}
