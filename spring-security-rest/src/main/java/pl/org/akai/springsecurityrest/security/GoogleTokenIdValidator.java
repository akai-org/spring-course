package pl.org.akai.springsecurityrest.security;


import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.org.akai.springsecurityrest.database.UserEntity;
import pl.org.akai.springsecurityrest.dto.LoginDto;

@Service
@RequiredArgsConstructor
public class GoogleTokenIdValidator {

    private final GoogleIdTokenVerifier googleIdTokenVerifier;

    public UserEntity validate(LoginDto studentLoginDTO) {
        try {
            GoogleIdToken idToken = googleIdTokenVerifier.verify(studentLoginDTO.getToken());
            GoogleIdToken.Payload payload = idToken.getPayload();
            return UserEntity.builder()
                             .email(payload.getEmail())
                             .username((String) payload.get("name"))
                             .build();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }
}
