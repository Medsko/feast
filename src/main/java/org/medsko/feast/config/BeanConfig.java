package org.medsko.feast.config;

import org.medsko.feast.random.EmailDomainBlacklistComposer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class BeanConfig {

    @Lazy   // Bean will only be instantiated when an instance is required (by default, Spring eagerly instantiates all beans).
    @Bean
    public EmailDomainBlacklistComposer blacklistComposer() {
        return new EmailDomainBlacklistComposer();
    }


}
