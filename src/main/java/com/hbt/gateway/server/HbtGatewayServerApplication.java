package com.hbt.gateway.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = ReactiveUserDetailsServiceAutoConfiguration.class)
@EnableEurekaClient
public class HbtGatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HbtGatewayServerApplication.class, args);
	}

}
