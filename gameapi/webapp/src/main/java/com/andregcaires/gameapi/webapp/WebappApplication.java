package com.andregcaires.gameapi.webapp;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.andregcaires.gameapi.core.interfaces.IGamesLogApplication;

@SpringBootApplication
@ComponentScan(basePackages = { "com.andregcaires.gameapi.core", "com.andregcaires.gameapi.webapp.resources",
		"com.andregcaires.gameapi.webapp.configurations", "com.andregcaires.gameapi.context.repositories" })
@EntityScan(basePackages = { "com.andregcaires.gameapi.domain" })
@EnableJpaRepositories(basePackages = { "com.andregcaires.gameapi.context.repositories" })
public class WebappApplication implements CommandLineRunner {

	@Value("classpath:games.log")
	Resource resourceFile;

	@Autowired
	private IGamesLogApplication gamesLogApplication;

	@Autowired
	private Environment environment;

	@Autowired
	private ConfigurableApplicationContext context;

	private final String SWAGGER_ENDPOINT = "/swagger-ui.html";
	private final String HTTP_PREFIX = "http://";

	Logger logger = LoggerFactory.getLogger(WebappApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {

			logger.info("Initializing application...");

			logger.info("Getting input stream...");

			gamesLogApplication.parseLogFileAndSaveData(resourceFile.getInputStream());

			logger.info("Parsing process complete");

			StringBuilder sb = new StringBuilder();
			sb.append("Please access ");
			sb.append(HTTP_PREFIX);
			sb.append(InetAddress.getLocalHost().getHostAddress());
			sb.append(":");
			sb.append(environment.getProperty("server.port"));
			sb.append(SWAGGER_ENDPOINT);
			sb.append(" for API documentation");

			logger.info(sb.toString());

		} catch (Exception err) {
			logger.error("An error has ocurred: " + err.getMessage());
			logger.error("Shutting down application");
			System.exit(SpringApplication.exit(context));
		}
	}

}
