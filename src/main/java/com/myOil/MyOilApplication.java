package com.myOil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableCaching 
@SpringBootApplication(scanBasePackages = {"com.myOil.dao","com.myOil.service", "com.myOil.controller"}) 
public class MyOilApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyOilApplication.class, args);
	}
}
