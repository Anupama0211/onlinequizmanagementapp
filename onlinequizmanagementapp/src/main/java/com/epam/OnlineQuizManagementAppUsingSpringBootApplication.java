package com.epam;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title="Quiz API", version="1.0", description="Creating Quizzes"))
public class OnlineQuizManagementAppUsingSpringBootApplication {

    public static void main(String[] args) {
       SpringApplication.run(OnlineQuizManagementAppUsingSpringBootApplication.class, args);
    }

    @Bean
    public ModelMapper getMapper() {
        return new ModelMapper();
    }


}
//Learn @Transanctional, @Repository, inorder verify, remove star import, make shorter functions
//http://localhost:8080/swagger-ui/index.html