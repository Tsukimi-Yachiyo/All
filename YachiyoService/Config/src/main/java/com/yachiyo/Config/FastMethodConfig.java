package com.yachiyo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FastMethodConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
