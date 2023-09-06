package com.oneHealth.discount;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DiscountApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiscountApplication.class, args);
    }
//    @GetMapping
//    public String welcome() {
//        return "Welcome From OneHealth Team (OneHealth-Discount)!!!";
//    }
    @Bean
	public ModelMapper modelMapper() {
	 return new ModelMapper();
	}
	
	@GetMapping
    public ResponseEntity<String> view(){
    	return ResponseEntity.ok("Welcome From OneHealth Team (OneHealth-Discount)!!!");
    }
}
