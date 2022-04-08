package pl.org.akai.springsecurity.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.org.akai.springsecurity.database.UserEntity;
import pl.org.akai.springsecurity.database.UserRepository;

import java.nio.CharBuffer;

@Component
@RequiredArgsConstructor
public class InitializingBeanImpl implements InitializingBean {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Value("${spring.admin.password}")
    private char[] defaultPassword;

    @Override
    public void afterPropertiesSet() throws Exception {
        String encodedPassword = passwordEncoder.encode(CharBuffer.wrap(defaultPassword));
        defaultPassword = new char[]{};
        UserEntity userEntity = UserEntity.builder()
                                          .username("admin")
                                          .password(encodedPassword)
                                          .build();
        userRepository.save(userEntity);
    }
}
