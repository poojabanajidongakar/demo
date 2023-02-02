package com.prodata.spsv1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.SpringVersion;

@SpringBootApplication
@ComponentScan({"com.prodata","presampling"})
public class Spsv1Application  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		System.out.println("version: " + SpringVersion.getVersion());
		System.out.println("version: " + SpringBootVersion.getVersion());

		SpringApplication.run(Spsv1Application.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(Spsv1Application.class);
	}
}
