package com.rubiconred.workstream.api.led.application.network;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Spring boot application annotation is equal to  @Configuration, @EnableAutoConfiguration, and @ComponentScan

@SpringBootApplication
public class RxRForexTradeApplication {

	public static void main(String[] args) {
		SpringApplication.run(RxRForexTradeApplication.class, args);
	}
}
