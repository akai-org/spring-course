package pl.org.akai.springwebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@SpringBootApplication
@ConfigurationPropertiesScan("pl.org.akai.springwebflux.properties")
public class SpringWebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebfluxApplication.class, args);
	}

}
