package com.andregcaires.gameapi.webapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.andregcaires.gameapi.core.interfaces.IGamesLogApplication;

@SpringBootApplication
@ComponentScan(basePackages = {"com.andregcaires.gameapi.core"})
@EnableJpaRepositories(basePackages = {"com.andregcaires.gameapi.context.repositories"})
public class WebappApplication implements CommandLineRunner {
	
	@Autowired
	private IGamesLogApplication gamesLogApplication;
	
	Logger logger = LoggerFactory.getLogger(WebappApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			logger.info("Initializing application...");
			gamesLogApplication.parser();
		} catch(Exception err) {
			logger.error("An error has ocurred: "+ err.getMessage());
		}
	}

}
