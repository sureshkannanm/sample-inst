package com.aciworldwide.web.institution.handler;

import com.aciworldwide.web.institution.model.request.CreateInstitutionRequest;
import com.aciworldwide.web.institution.service.InstitutionApi;
import com.aciworldwide.web.institution.service.InstitutionService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.web.api.service.ServiceResponse;
import io.vertx.ext.web.validation.RequestParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class InstitutionHandler implements InstitutionApi {

    @Autowired
    private InstitutionService service;

    @Override
    public void createInstitution(RequestParameter body, ServiceRequest request, Handler<AsyncResult<ServiceResponse>> resultHandler) {
        service.createInstitution(body.getJsonObject().mapTo(CreateInstitutionRequest.class)).subscribe().with(
                result -> resultHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(JsonObject.mapFrom(result)))),
                error -> resultHandler.handle(Future.succeededFuture(ServiceResponse.completedWithPlainText(Buffer.buffer("Internal Server Error")).setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR.name()).setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())))
        );
    }

    @Override
    public void getInstitutionById(RequestParameter body, ServiceRequest request, Handler<AsyncResult<ServiceResponse>> resultHandler) {
        service.getInstitutionById(Integer.parseInt(request.getParams().getJsonObject("path").getString("id")))
            .subscribe().with(
                    result -> {
                        if (Objects.isNull(result))
                            resultHandler.handle(Future.succeededFuture(ServiceResponse.completedWithPlainText(Buffer.buffer("Institution not found")).setStatusMessage(HttpStatus.NOT_FOUND.name()).setStatusCode(HttpStatus.NOT_FOUND.value())));
                        else
                            resultHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(JsonObject.mapFrom(result))));
                    },
                    error -> resultHandler.handle(Future.succeededFuture(ServiceResponse.completedWithPlainText(Buffer.buffer("Internal Server Error")).setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR.name()).setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()))));
    }

    @Override
    public void getInstitutions(RequestParameter body, ServiceRequest request, Handler<AsyncResult<ServiceResponse>> resultHandler) {
        service.getAllInstitutions().subscribe().with(
                result -> resultHandler.handle(Future.succeededFuture(ServiceResponse.completedWithJson(new JsonArray(result)))),
                error -> resultHandler.handle(Future.succeededFuture(ServiceResponse.completedWithPlainText(Buffer.buffer("Internal Server Error")).setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR.name()).setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()))));
    }
}
