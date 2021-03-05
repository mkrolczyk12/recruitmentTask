package io.github.restfulApiWebservice.restfulApiWebservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RestfulApiWebserviceApplication {
	public static final Logger logger = LoggerFactory.getLogger(RestfulApiWebserviceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RestfulApiWebserviceApplication.class, args);
		logger.info("application started");
		System.out.println("application started");
	}

}
