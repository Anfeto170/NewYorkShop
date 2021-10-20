package com.uelbosque.OnlineShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class OnlineShopSpringApplication extends SpringBootServletInitializer{

	public static void main (String[] args) {
		System.setProperty("server.servlet.context-path", "/TiendaVirtualNewYork");
		SpringApplication.run(OnlineShopSpringApplication.class, args);
	}

}
