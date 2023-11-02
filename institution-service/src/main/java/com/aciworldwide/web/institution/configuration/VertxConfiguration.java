package com.aciworldwide.web.institution.configuration;

import io.vertx.core.Vertx;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VertxConfiguration {

    @Bean
    public Vertx getVertx() {
        return Vertx.vertx();
    }
}
