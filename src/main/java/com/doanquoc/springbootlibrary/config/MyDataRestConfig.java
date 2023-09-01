package com.doanquoc.springbootlibrary.config;

import com.doanquoc.springbootlibrary.entity.Book;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;


// dung de tat tinh nang endpoint mac dinh cua spring boot la cho phep xai post put delete voi api/books endpoint
// ma khong can phai setting Mapping method. O day chi cho phep endpoint nay su dung de get data.

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
    private  String theAllowedOrigins = "http://localhost:3000";
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config,
                                                     CorsRegistry cors){
        HttpMethod[] theUnsupportedActions = {HttpMethod.POST,
                                            HttpMethod.PATCH,
                                            HttpMethod.DELETE,
                                            HttpMethod.PUT};

        config.exposeIdsFor(Book.class);

        disableHttpMethods(Book.class,config,theUnsupportedActions );

        cors.addMapping(config.getBasePath()+ "/**")
                .allowedOrigins(theAllowedOrigins);
    }

    private void disableHttpMethods(Class theClass,
                                    RepositoryRestConfiguration config,
                                    HttpMethod[] theUnsupportedActions){
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) ->
                        httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) ->
                                httpMethods.disable(theUnsupportedActions));

    }
}
