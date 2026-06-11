package com.cog.fundmatrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundmatrixApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundmatrixApplication.class, args);
		System.out.print("running on 8082");
	}

}
