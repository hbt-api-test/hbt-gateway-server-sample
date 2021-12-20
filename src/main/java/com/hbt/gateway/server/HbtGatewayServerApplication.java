package com.hbt.gateway.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HbtGatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HbtGatewayServerApplication.class, args);
	}

}
