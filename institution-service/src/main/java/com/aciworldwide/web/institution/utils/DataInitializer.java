package com.aciworldwide.web.institution.utils;

import com.aciworldwide.web.institution.entity.Institution;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.reactive.mutiny.Mutiny;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements ApplicationRunner {


    private final Mutiny.SessionFactory sessionFactory;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Data initialization is starting...");

        Institution first = new Institution(null, "Institute 1");
        Institution second = new Institution(null, "Institute 2");

        sessionFactory
            .withTransaction(
                (conn, tx) -> conn.createQuery("DELETE FROM Institution").executeUpdate()
                    .flatMap(r -> conn.persistAll(first, second))
                    .chain(conn::flush)
                    .flatMap(r -> conn.createQuery("SELECT p from Institution p", Institution.class).getResultList())
            )
            .subscribe()
            .with(
                data -> log.info("saved data: ", data),
                throwable -> log.warn("Data initialization is failed:" + throwable.getMessage())
            );
    }
}