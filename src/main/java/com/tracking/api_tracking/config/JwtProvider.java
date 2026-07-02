package com.tracking.api_tracking.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtProvider {
    private final String SECRET = "your_very_long_secret_key_must_be_at_least_32_characters_long_to_work_with_HS256";
    private final byte[] keyBytes = Base64.getEncoder().encode(SECRET.getBytes());
    public String generateToken(String username  , List<String> roles ,  Long userId)
    {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles" , roles)
                .claim("userId" , userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256 ,keyBytes).compact();
    }
    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(keyBytes)
                .parseClaimsJws(token)
                .getBody();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = getClaims(token);
        return Long.valueOf(claims.get("userID").toString());
    }
    @SuppressWarnings("unchecked")
    public List<String> getRolesFromToken(String token) {
        Claims claims = getClaims(token);
        return claims.get("roles", List.class);
    }

    // Hàm kiểm tra token hợp lệ
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(keyBytes).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
