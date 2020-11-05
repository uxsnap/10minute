package com.example.smtp;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@SpringBootApplication
public class SmtpApplication {
	public static void main(String[] args) {
		SpringApplication.run(SmtpApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/email").allowedOrigins("*");
			}
		};
	}

//	insert into mail (id, host, name, password)
//	values (0, 'localhost.com', 'zhenek@localhost.com', '12345');
//
//	insert into mail (id, host, name, password)
//	values (1, 'localhost.com', 'zhenek1@localhost.com', '12345');
	@Bean
	public ApplicationRunner initializer(MailRepository mailRepository) {
		return args -> mailRepository.saveAll(Arrays.asList(
			MailEntity.builder()
				.id(0)
				.host("localhost.com")
				.name("zhenek@localhost.com")
				.password("12345")
				.tokenExpire(null)
				.token(null)
				.build(),
			MailEntity.builder()
				.host("localhost.com")
				.name("zhenek1@localhost.com")
				.password("12345")
				.tokenExpire(null)
				.token(null)
				.build()
		));
	}
}
