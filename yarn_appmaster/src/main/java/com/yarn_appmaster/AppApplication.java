package com.yarn_appmaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.yarn.client.YarnClient;

@EnableAutoConfiguration
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args)
                .getBean(YarnClient.class)
                .submitApplication();
    }

}