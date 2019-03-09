package ru.ifmo.cs.iad.iadcurseproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import ru.ifmo.cs.iad.iadcurseproject.entity.Comment;
// import ru.ifmo.cs.iad.iadcurseproject.entity.Repost;
import ru.ifmo.cs.iad.iadcurseproject.repository.*;

import java.lang.reflect.Array;
import java.util.Arrays;

@SpringBootApplication
//@ImportResource("classpath:spring-config.xml")
//@EnableJpaRepositories(basePackages = "ru.ifmo.cs.iad.iadcurseproject.repository")
//@EntityScan(basePackages = {"ru.ifmo.cs.iad.iadcurseproject"})
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
//	java.lang.ExceptionInInitializerError
}
