package com.youcloud.mbf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MbfDataLibApplication {

    public static void main(String[] args) {
        SpringApplication.run(MbfDataLibApplication.class, args);
    }

}
