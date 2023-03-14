package com.javatechie.multiple.ds.api.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "roleEntityManagerFactory",
        transactionManagerRef = "roleTransactionManager",
        basePackages = {"com.javatechie.multiple.ds.api.role.repository" })
public class RoleDBConfig {

    @Bean(name = "roleDataSource")
    @ConfigurationProperties(prefix = "spring.role.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "roleEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean roleEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                           @Qualifier("roleDataSource") DataSource dataSource) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return builder.dataSource(dataSource).properties(properties)
                .packages("com.javatechie.multiple.ds.api.model.role").persistenceUnit("role").build();
    }

    @Bean(name = "roleTransactionManager")
    public PlatformTransactionManager roleTransactionManager(
            @Qualifier("roleEntityManagerFactory") EntityManagerFactory roleEntityManagerFactory) {
        return new JpaTransactionManager(roleEntityManagerFactory);
    }

}
