package com.liuzemin.server.framework.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.liuzemin.server")
@EnableDiscoveryClient
public class AuthServer {

    public static void main(String[] args) {

        SpringApplication.run(AuthServer.class, args);
    }
}
