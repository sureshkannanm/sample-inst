package com.aciworldwide.web.institution.verticle;


import com.aciworldwide.web.institution.service.InstitutionApi;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.openapi.RouterBuilder;
import io.vertx.ext.web.openapi.RouterBuilderOptions;
import io.vertx.serviceproxy.ServiceBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InstitutionVerticle extends AbstractVerticle {

    @Autowired
    private InstitutionApi institutionApi;
    static final Logger LOGGER = LoggerFactory.getLogger(InstitutionVerticle.class);

    HttpServer server;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {

      RouterBuilder.create(this.vertx, "openapi.yaml")
      .flatMap(routerBuilder -> { 
    
        RouterBuilderOptions factoryOptions = new RouterBuilderOptions()
          .setRequireSecurityHandlers(false)
          .setMountResponseContentTypeHandler(true);
        routerBuilder.setOptions(factoryOptions);

        routerBuilder.mountServicesFromExtensions();

        return Future.succeededFuture(routerBuilder.createRouter());
      })
      .flatMap(openapiRouter -> {
        Router router = Router.router(vertx);

        server = vertx.createHttpServer(new HttpServerOptions().setPort(8080).setHost("localhost"))
          .requestHandler(router);

        router.route("/*").subRouter(openapiRouter);

        router.route().last().handler(context ->
          context.response()
            .setStatusCode(404)
            .end(new JsonObject()
              .put("message", "Resource not found")
              .encode())
        );

        
      return server.listen()
          .onSuccess(server -> LOGGER.info("InstitutionVerticle started on port " + server.actualPort()));
      })
      .onSuccess(server -> startPromise.complete())
      .onFailure(startPromise::fail);
        sartInstitutionService();
    }

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception {
    this.server.close()
      .onSuccess(server -> stopPromise.complete())
      .onFailure(stopPromise::fail);
  }

    private void sartInstitutionService() {
        new ServiceBinder(vertx).setAddress("institution-api")
                .register(InstitutionApi.class, institutionApi);
    }

}
