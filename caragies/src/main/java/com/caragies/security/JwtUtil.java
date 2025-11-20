package com.caragies.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String key="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWV9.TJVA95OrM7E2cBab30RMHrHDcEfxjoYZgeFONFh7HgQ";
    private static final SecretKey secretKey= Keys.hmacShaKeyFor(key.getBytes());

    public String createToken(String username, String role){
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ (1000L * 60 * 60)))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractClaim(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token){
        return extractClaim(token).getSubject();
    }

    public String extractRole(String token){
        return (String) extractClaim(token).get("role");
    }

    public Date expireDate(String token){
        return extractClaim(token).getExpiration();
    }

    public boolean isExpire(String username, String token){
        if(extractUsername(token).equals(username) && (new Date().before(expireDate(token)))){
            return true;
        }
        return false;
    }
}
