package com.abdul.kfh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.abdul.kfh"})
public class KFH_AbdulProjectRun {

	public static void main(String[] args) {
		System.out.println("Starting ....");
    SpringApplication.run(KFH_AbdulProjectRun.class, args);
	}

}
