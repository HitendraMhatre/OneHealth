package com.oneHealth.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
 
//
//@SpringBootApplication
//@Configuration
//@ComponentScan(basePackages = "com.oneHealth.blog.entities")
//@ComponentScan(basePackages="com.*")
//@EntityScan(basePackages="com.*")
//@EnableJpaRepositories(basePackages="com.*")
//public class BlogApplication {
//	public static void main(String[] args) { 
//		SpringApplication.run(BlogApplication.class, args);
//	}	
//
//}

@SpringBootApplication
@RestController
public class BlogApplication {
    public static void main(String[] args) {
    	SpringApplication.run(BlogApplication.class, args);
    }
    @GetMapping
    public String welcome() {
        return "Welcome From OneHealth Team (OneHealth-blogApplication)!!!";
    }
}
