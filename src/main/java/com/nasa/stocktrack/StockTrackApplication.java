package com.nasa.stocktrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching

public class StockTrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockTrackApplication.class, args);
	}

}
