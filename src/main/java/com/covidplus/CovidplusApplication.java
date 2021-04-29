package com.covidplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class CovidplusApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(CovidplusApplication.class);
		application.addListeners(new ApplicationPidFileWriter());
		application.run(args);
	}

}
