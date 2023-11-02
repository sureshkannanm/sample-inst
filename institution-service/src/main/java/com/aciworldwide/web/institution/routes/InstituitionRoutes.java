package com.aciworldwide.web.institution.routes;

import com.aciworldwide.web.institution.model.request.CreateInstitutionRequest;
import com.aciworldwide.web.institution.service.InstitutionService;
import io.smallrye.mutiny.vertx.UniHelper;
import io.vertx.ext.web.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InstituitionRoutes implements Routes {

    @Autowired
    private InstitutionService service;

    @Override
    public void addRoutes(Router router) {
        router.get("/institution").respond(ctx -> UniHelper.toFuture(service.getAllInstitutions()));
        router.get("/institution/:id").respond(ctx -> UniHelper.toFuture(service.getInstitutionById(Integer.parseInt(ctx.pathParam("id")))));
        router.post("/institution").respond(ctx -> UniHelper.toFuture(service.createInstitution(ctx.body().asPojo(CreateInstitutionRequest.class))));
    }

}
