package com.yarn_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@SpringBootApplication
//@RestController
//public class ClientApplication {
//    @RequestMapping("/yarn-client")
//    String home() {
//        return "Hello from yarn client.";
//    }
//    public static void main(String[] args) {
//        SpringApplication.run(ClientApplication.class, args);
//    }
//}

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.yarn.client.YarnClient;

@EnableAutoConfiguration
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args)
                .getBean(YarnClient.class)
                .submitApplication();
    }

}
