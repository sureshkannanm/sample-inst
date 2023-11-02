package com.aciworldwide.web.institution;

import com.aciworldwide.web.institution.verticle.InstitutionVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainApiVerticle extends AbstractVerticle {

    @Autowired
    private InstitutionVerticle institutionVerticle;
    static final Logger LOGGER = LoggerFactory.getLogger(MainApiVerticle.class);

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
    vertx.deployVerticle(institutionVerticle)
      .onFailure(error -> {
        LOGGER.error("InstitutionVerticle : Deployment failed");
        startPromise.fail(error);
      })
      .onSuccess(server -> {
        LOGGER.info("InstitutionVerticle : Deployed");
        startPromise.complete();
      });
    }
    

}
