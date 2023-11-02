package com.aciworldwide.web.institution.service;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ext.web.api.service.ServiceRequest;
import io.vertx.ext.web.api.service.ServiceResponse;
import io.vertx.ext.web.api.service.WebApiServiceGen;
import io.vertx.ext.web.validation.RequestParameter;
@WebApiServiceGen
public interface InstitutionApi  {

    String WEBSERVICE_ADDRESS_INSTITUTIONAPI = "institution-api";
    String OPERATION_ID_CREATEINSTITUTION = "createInstitution";
    String OPERATION_ID_GETINSTITUTIONBYID = "getInstitutionById";
    String OPERATION_ID_GETINSTITUTIONS = "getInstitutions";

    void createInstitution(RequestParameter body, ServiceRequest request, Handler<AsyncResult<ServiceResponse>> resultHandler);
    void getInstitutionById(RequestParameter body, ServiceRequest request, Handler<AsyncResult<ServiceResponse>> resultHandler);
    void getInstitutions(RequestParameter body, ServiceRequest request, Handler<AsyncResult<ServiceResponse>> resultHandler);
}
