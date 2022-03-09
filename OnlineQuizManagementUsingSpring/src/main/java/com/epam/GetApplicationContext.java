package com.epam;

import com.epam.dao.GetManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;


@Configuration
@ComponentScan(basePackages = {"com.epam"})
public class GetApplicationContext {

    public static ApplicationContext get() {
         return new AnnotationConfigApplicationContext(GetApplicationContext.class);
    }
    @Bean
    public EntityManager getEntityManager(){
        return GetManager.getEntityManger();
    }
}
