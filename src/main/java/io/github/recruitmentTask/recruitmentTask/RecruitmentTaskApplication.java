package io.github.recruitmentTask.recruitmentTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RecruitmentTaskApplication {
	public static final Logger logger = LoggerFactory.getLogger(RecruitmentTaskApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RecruitmentTaskApplication.class, args);
		logger.info("application started");
		System.out.println("application started");
	}

}
