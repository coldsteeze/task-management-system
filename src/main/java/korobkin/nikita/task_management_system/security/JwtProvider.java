package korobkin.nikita.task_management_system.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    @Value("${spring.security.jwt.secret}")
    private String jwtSecret;

    @Value("${spring.security.jwt.expiration}")
    private long jwtExpiration;

    public String generateToken(String username) {
        return JWT.create()
                .withSubject("User details")
                .withClaim("username", username)
                .withIssuedAt(new Date())
                .withIssuer("Task-management application")
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpiration * 1000))
                .sign(Algorithm.HMAC256(jwtSecret));
    }

    public String getUsernameFromToken(String token) throws JWTVerificationException {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(jwtSecret))
                .withSubject("User details")
                .withIssuer("Task-management application")
                .build();

        DecodedJWT jwt = jwtVerifier.verify(token);

        return jwt.getClaim("username").asString();
    }
}
