package com.example.toy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.example.toy.mapper")
public class ToyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToyApplication.class, args);
	}

}
