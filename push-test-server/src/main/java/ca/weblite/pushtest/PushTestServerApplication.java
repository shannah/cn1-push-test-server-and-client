package ca.weblite.pushtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PushTestServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PushTestServerApplication.class, args);
	}

}
