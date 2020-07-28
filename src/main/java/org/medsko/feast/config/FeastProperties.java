package org.medsko.feast.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("feast")
public class FeastProperties {

    private String baseUrl;


}
