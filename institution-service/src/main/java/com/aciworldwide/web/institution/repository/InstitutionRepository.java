package com.aciworldwide.web.institution.repository;

import com.aciworldwide.web.institution.entity.Institution;
import io.smallrye.mutiny.Uni;
import io.vertx.codegen.CodeGenProcessor;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.reactive.mutiny.Mutiny;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@Slf4j
public class InstitutionRepository {
    //CodeGenProcessor
    @Autowired
    private Mutiny.SessionFactory sessionFactory;
    public Uni<List<Institution>> findAll() {
        CriteriaBuilder cb = this.sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Institution> query = cb.createQuery(Institution.class);
        query.from(Institution.class);
        return this.sessionFactory.withSession(session -> session.createQuery(query).getResultList());
    }

    public Uni<Institution> findById(Integer id) {
        Objects.requireNonNull(id, "id can not be null");
        return this.sessionFactory.withSession(session -> session.find(Institution.class, id));
    }

    public Uni<Institution> save(Institution institution) {
        if (institution.getId() == null) {
            return this.sessionFactory.withSession(session ->
                    session.persist(institution)
                            .chain(session::flush)
                            .replaceWith(institution)
            );
        } else {
            return this.sessionFactory.withSession(session -> session.merge(institution).onItem().call(session::flush));
        }
    }
}
