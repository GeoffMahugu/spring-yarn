package com.yarn_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ClientApplication {

    @RequestMapping("/home")
    String home() {
        return "Hello World! Home page.";
    }

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}