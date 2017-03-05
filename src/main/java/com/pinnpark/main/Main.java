package com.pinnpark.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan("com.pinnpark")
@EnableMongoRepositories(basePackages= {"com.pinnpark"})
public class Main {

	public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
