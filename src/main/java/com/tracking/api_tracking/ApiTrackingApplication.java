package com.tracking.api_tracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.tracking.api_tracking")

public class ApiTrackingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiTrackingApplication.class, args);
    }

}
