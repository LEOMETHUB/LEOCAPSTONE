package org.sportmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class APIGatewayMain {
    public static void main(String[] args) {
        SpringApplication.run(APIGatewayMain.class,args);
    }

}