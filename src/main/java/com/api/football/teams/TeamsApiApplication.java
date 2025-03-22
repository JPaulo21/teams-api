package com.api.football.teams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class TeamsApiApplication {

	private static final Logger log = LoggerFactory.getLogger(TeamsApiApplication.class);

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(TeamsApiApplication.class);
		Environment env = app.run(args).getEnvironment();

		String info = """
				\n-----------------------------------------------------------------------------------------------------------------------------------------------
				 Application {} is running!
				-----------------------------------------------------------------------------------------------------------------------------------------------
				Access URLs:
					Local:\t\thttp://localhost:{}/football-api/swagger-ui/index.html
					External:\thttp://{}:{}/football-api/swagger-ui/index.html
				-----------------------------------------------------------------------------------------------------------------------------------------------
				""";
		log.info(info, env.getProperty("spring.application.name"),
				env.getProperty("server.port"),
				InetAddress.getLocalHost().getHostAddress(),
				env.getProperty("server.port"));
	}

}
