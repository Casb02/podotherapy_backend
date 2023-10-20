package nl.saxion.podotherapy.podotherapy_backend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 uur
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }


    /**
     * Validates the given token using a secret key and JWT verification.
     *
     * @param token The token to be validated.
     * @return True if the token is valid, false otherwise.
     */
    public boolean validateToken(String token) {
        // Get the secret key
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Extracts the username from the JWT token's body
     *
     * @param token The JWT token string
     * @return The username extracted from the token
     */
    public String getUsernameFromBody(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
    }

}