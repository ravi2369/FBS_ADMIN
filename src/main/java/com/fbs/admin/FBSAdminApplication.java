package com.fbs.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class FBSAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(FBSAdminApplication.class, args);
    }
}
