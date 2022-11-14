package com.helk.fraud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FraudApplication {

        public static void main(String[] args) {
            org.springframework.boot.SpringApplication.run(FraudApplication.class, args);
        }
}
