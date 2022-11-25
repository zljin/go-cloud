package io.leonard.go.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class GoConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoConsumerApplication.class, args);
    }

}
