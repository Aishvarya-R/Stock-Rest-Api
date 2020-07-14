package com.aishvarya.rest.api.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.aishvarya.rest.api")
@EnableJpaRepositories( basePackages = {"com.aishvarya.rest.api"} )
@EntityScan( basePackages = {"com.aishvarya.rest.api"} )
public class StockApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockApplication.class, args);
    }

}
