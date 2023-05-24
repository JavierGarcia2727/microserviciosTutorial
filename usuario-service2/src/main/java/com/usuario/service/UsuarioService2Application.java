package com.usuario.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UsuarioService2Application {

	public static void main(String[] args) {
		SpringApplication.run(UsuarioService2Application.class, args);
	}

}
