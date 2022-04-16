package pl.org.akai.springsecurityrest.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.org.akai.springsecurityrest.database.UserEntity;
import pl.org.akai.springsecurityrest.database.UserRepository;
import pl.org.akai.springsecurityrest.dto.LoginDto;
import pl.org.akai.springsecurityrest.security.GoogleTokenIdValidator;
import pl.org.akai.springsecurityrest.security.JwtBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final GoogleTokenIdValidator googleTokenIdValidator;
    private final UserRepository userRepository;
    private final JwtBuilder jwtBuilder;

    public LoginDto authenticateUser(LoginDto tokenId) {
        UserEntity user = googleTokenIdValidator.validate(tokenId);
        UserEntity userWithId = userRepository.findByEmail(user.getEmail())
                                              .orElse(userRepository.save(user));
        String token = jwtBuilder.getToken(userWithId.getEmail(), getClaims(userWithId));
        return new LoginDto(token);
    }

    private Map<String, Object> getClaims(UserEntity user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("email", user.getEmail());
        claims.put("username", user.getUsername());
        return claims;
    }
}
