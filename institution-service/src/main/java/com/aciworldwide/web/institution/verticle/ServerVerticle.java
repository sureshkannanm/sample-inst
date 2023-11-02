package com.aciworldwide.web.institution.verticle;

import com.aciworldwide.web.institution.routes.InstituitionRoutes;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ServerVerticle extends AbstractVerticle {

    @Value("${server.port}")
    private Integer port;

    @Autowired
    private InstituitionRoutes routes;

    @Override
    public void start() {
        log.info("Initializing Server Verticle");
        BodyHandler bodyHandler = BodyHandler.create();
        Router router = Router.router(vertx);
        router.route().handler(bodyHandler);
        routes.addRoutes(router);
        vertx.createHttpServer().requestHandler(router).listen(port);
        log.info("Server Verticle initialized successfully. Listening on port: " + port);
    }

}
