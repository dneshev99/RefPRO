package com.refpro.server;

import com.refpro.server.models.User;
import com.refpro.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

	@Autowired
	static private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
}
