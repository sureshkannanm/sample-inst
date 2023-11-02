package com.aciworldwide.web.institution;

import com.aciworldwide.web.institution.verticle.ServerVerticle;
import io.vertx.core.Vertx;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class VertxEngine {

    @Autowired
    private ServerVerticle verticle;

    @Autowired
    private MainApiVerticle mainVerticle;

    @Autowired
    private Vertx vertx;

    @PostConstruct
    public void start() {
        vertx.deployVerticle(mainVerticle).onSuccess(id ->
                log.info("Server Verticle deployed successfully")
        ).onFailure(err ->
                log.error("Server Verticle deployment failed", err)
        );
    }
}
