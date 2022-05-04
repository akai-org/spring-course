package pl.org.akai.springwebflux.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("pl.org.akai")
public class ProjectProperties {

    private String secret;
    private String apiBaseUrl;

}
