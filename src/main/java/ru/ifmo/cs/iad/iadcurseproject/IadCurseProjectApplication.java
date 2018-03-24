package ru.ifmo.cs.iad.iadcurseproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

@SpringBootApplication
//@ImportResource("classpath:spring-config.xml")
public class IadCurseProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(IadCurseProjectApplication.class, args);
	}

//	@Bean
//	public LocalEntityManagerFactoryBean entityManagerFactory() {
//		LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
//		factoryBean.setPersistenceUnitName("cpJpaPu");
//		return factoryBean;
//	}
}
