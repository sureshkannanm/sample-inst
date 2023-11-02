package com.aciworldwide.web.institution.configuration;

import com.aciworldwide.web.institution.entity.Institution;
import org.hibernate.cfg.Configuration;
import org.hibernate.reactive.mutiny.Mutiny;
import org.hibernate.reactive.provider.ReactiveServiceRegistryBuilder;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@org.springframework.context.annotation.Configuration
public class HibernateConfiguration {

    @Value("${hibernate.connection.url}")
    private String jdbcUrl;

    @Value("${hibernate.connection.username}")
    private String jdbcUser;

    @Value("${hibernate.connection.password}")
    private String jdbcPassword;

    @Value("${hibernate.connection.pool_size}")
    private String connectionPoolSize;

    @Value("${hibernate.hbm2ddl.auto}")
    private String ddlAuto;

    @Value("${hibernate.show_sql}")
    private String showSql;

    @Value("${hibernate.format_sql}")
    private String formatSql;

    @Value("${hibernate.highlight_sql}")
    private String highlightSql;

    @Bean
    public Mutiny.SessionFactory sessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addProperties(getProperties());
        configuration.addAnnotatedClass(Institution.class);
        return configuration.buildSessionFactory(
            new ReactiveServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build()
        )
        .unwrap(Mutiny.SessionFactory.class);
    }

    @NotNull
    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.connection.url", jdbcUrl);
        properties.put("hibernate.connection.username", jdbcUser);
        properties.put("hibernate.connection.password", jdbcPassword);
        properties.put("hibernate.connection.pool_size", connectionPoolSize);
        properties.put("hibernate.hbm2ddl.auto", ddlAuto);
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.format_sql", formatSql);
        properties.put("hibernate.highlight_sql", highlightSql);
        return properties;
    }
}
