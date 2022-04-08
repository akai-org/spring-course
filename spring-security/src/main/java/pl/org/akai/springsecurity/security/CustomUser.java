package pl.org.akai.springsecurity.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.HashMap;
import java.util.Map;

public class CustomUser extends User implements OAuth2User {

    public CustomUser(String username, String... authorities) {
        super(username, "no-password", AuthorityUtils.createAuthorityList(authorities));
    }

    @Override
    public String getName() {
        return this.getUsername();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return new HashMap<>();
    }
}
