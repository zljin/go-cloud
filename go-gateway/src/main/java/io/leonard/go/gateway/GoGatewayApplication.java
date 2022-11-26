package io.leonard.go.gateway;

import io.leonard.go.common.pojo.CommonReturnType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class GoGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoGatewayApplication.class, args);
    }

    @RestController
    public static class ConsumerController {

        @GetMapping("/health")
        public CommonReturnType health() throws Exception {
            StringBuilder sb = new StringBuilder();
            sb.append(" go-gateway is health.");
            sb.append(" IP: " + InetAddress.getLocalHost().getHostAddress());
            return CommonReturnType.create(sb.toString());
        }
    }

}
