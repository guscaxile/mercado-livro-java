package com.mercado.java.mercadolivrojava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class MercadoLivroJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercadoLivroJavaApplication.class, args);
	}

}
