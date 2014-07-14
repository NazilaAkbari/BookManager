package com.nazi;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@ComponentScan
public class App {

	public static void main(String[] args) throws Throwable {
		SpringApplication.run(App.class, args);
	}

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		DataSource driverManagerDataSource = new DataSource();
		driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
		driverManagerDataSource
				.setUrl("jdbc:postgresql://localhost:5432/bookDB");
		driverManagerDataSource.setUsername("postgres");
		driverManagerDataSource.setPassword("postgres");
		return driverManagerDataSource;
	}
}
