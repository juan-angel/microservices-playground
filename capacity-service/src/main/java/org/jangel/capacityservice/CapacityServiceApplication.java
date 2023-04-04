package org.jangel.capacityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class CapacityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapacityServiceApplication.class, args);
	}

}
