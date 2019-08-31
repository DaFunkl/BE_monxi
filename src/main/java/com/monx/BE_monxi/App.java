package com.monx.BE_monxi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.monx.BE_monxi.configuration.ThreadConfig;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		ThreadConfig.init();
		SpringApplication.run(App.class, args);
	}

}
