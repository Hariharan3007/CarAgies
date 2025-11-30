package com.caragies.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String key="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ";
    private static final SecretKey secretKey= Keys.hmacShaKeyFor(key.getBytes());
    private final long validityMs = 1000L * 60 * 60;
    public String createToken(String username, String role){
        Date now = new Date();
        Date exp = new Date(now.getTime() + validityMs);
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    private Jws<Claims> parseClaimsJws(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .setAllowedClockSkewSeconds( 60L)
                .build()
                .parseClaimsJws(token);
    }

    public Claims extractClaim(String token) {
        return parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token) {
        return extractClaim(token).getSubject();
    }

    public String extractRole(String token) {
        Object r = extractClaim(token).get("role");
        return r == null ? null : r.toString();
    }

    public Date expireDate(String token) {
        return extractClaim(token).getExpiration();
    }

    public boolean isExpired(String token) {
        try {
            parseClaimsJws(token);
            return false;
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    public boolean isValidToken(String token, String username) {
        try {
            String extracted = extractUsername(token);
            return extracted != null && extracted.equals(username) && !isExpired(token);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}