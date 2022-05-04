package pl.org.akai.springwebflux.user.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import pl.org.akai.springwebflux.properties.ProjectProperties;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtBuilder {

    private static final int HOUR = 3600000;
    private final SecretKeySpec secretKeySpec;


    public JwtBuilder(ProjectProperties properties) {
        secretKeySpec = new SecretKeySpec(properties.getSecret().getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS512.getJcaName());
    }

    public String generateToken(String username) {
        return Jwts.builder()
                   .setExpiration(new Date(System.currentTimeMillis() + HOUR))
                   .setIssuedAt(new Date())
                   .setIssuer("example")
                   .setSubject(username)
                   .signWith(secretKeySpec, SignatureAlgorithm.HS512)
                   .compact();
    }
}
