package com.epam;

import com.epam.dao.GetManager;
import com.epam.userinterface.QuizPortalUI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import java.util.ConcurrentModificationException;
import java.util.Scanner;

@SpringBootApplication
public class OnlineQuizManagementAppUsingSpringBootApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context= SpringApplication.run(OnlineQuizManagementAppUsingSpringBootApplication.class, args);
     	QuizPortalUI quizPortalUI=context.getBean(QuizPortalUI.class);
		Scanner scanner = new Scanner(System.in);
		quizPortalUI.launch(scanner);
		scanner.close();
	}

	@Bean
	public EntityManager getEntityManager(){
		return GetManager.getEntityManger();
	}

//	@Override
//	1public void run(String... args) throws Exception {
//		Scanner scanner = new Scanner(System.in);
//		new QuizPortalUI().launch(scanner);
//		scanner.close();
//	}
}
