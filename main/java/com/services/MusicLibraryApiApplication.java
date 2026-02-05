package com.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com"})
public class MusicLibraryApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MusicLibraryApiApplication.class, args);
    }
}

