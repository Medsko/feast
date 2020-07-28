package org.medsko.feast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class FeastApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeastApplication.class, args);
    }

}
