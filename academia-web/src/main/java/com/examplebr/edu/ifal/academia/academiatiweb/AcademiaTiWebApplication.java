package com.examplebr.edu.ifal.academia.academiatiweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@ComponentScan(basePackages={"controllers", "resources", "com.examplebr.edu.ifal.academia.academiatiweb.*"})
public class AcademiaTiWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademiaTiWebApplication.class, args);
	}
}
