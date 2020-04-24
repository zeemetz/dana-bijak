package com.poc.danabijak.minilending;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableRedisRepositories
@SpringBootApplication
public class MiniLendingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniLendingApplication.class, args);
	}

}
