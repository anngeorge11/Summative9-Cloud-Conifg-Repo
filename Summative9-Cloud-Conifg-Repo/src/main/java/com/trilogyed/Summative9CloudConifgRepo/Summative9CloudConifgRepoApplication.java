package com.trilogyed.Summative9CloudConifgRepo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class Summative9CloudConifgRepoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Summative9CloudConifgRepoApplication.class, args);
	}

}
