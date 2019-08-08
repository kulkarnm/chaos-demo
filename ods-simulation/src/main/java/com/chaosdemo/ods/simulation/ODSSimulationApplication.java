package com.chaosdemo.ods.simulation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.chaosdemo")
public class ODSSimulationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ODSSimulationApplication.class, args);
	}
	
}
