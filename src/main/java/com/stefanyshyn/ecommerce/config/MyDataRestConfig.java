package com.stefanyshyn.ecommerce.config;

import com.stefanyshyn.ecommerce.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfigurer;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    @Value("${allowed.origins}")
    private String[] theAllowedOrigin;

    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.DELETE,
                HttpMethod.POST, HttpMethod.PATCH};
        //disable HTTP methods for Product:Put, Post, Delete, Patch
        disableHttpMethods(Product.class, config, theUnsupportedActions);
        //disable HTTP methods for ProductCategory: Put, Post, Delete, Patch
        disableHttpMethods(ProductCategory.class, config, theUnsupportedActions);
        //disable HTTP methods for Country:Put, Post, Delete, Patch
        disableHttpMethods(Country.class, config, theUnsupportedActions);
        //disable HTTP methods for State: Put, Post, Delete, Patch
        disableHttpMethods(State.class, config, theUnsupportedActions);
        //disable HTTP methods for Order: Put, Post, Delete, Patch
        disableHttpMethods(Order.class, config, theUnsupportedActions);
        //helper
        exposeIds(config);
        cors.addMapping(config.getBasePath() + "/**").allowedOrigins(theAllowedOrigin);
    }

    private static ExposureConfigurer disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        return config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        List<Class> entityClasses = new ArrayList<>();
        for (EntityType tempEntityType : entities) {
            entityClasses.add(tempEntityType.getJavaType());
        }

        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
