package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.test")
@EnableSwagger2
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.test")
@EnableHystrix
@EnableAsync
//@MapperScan(basePackages = {"com.legao.server.framework.*.*.dao","com.legao.server.framework.*.dao"})
public class SecurityServer{



    public static void main(String[] args){

        SpringApplication.run(SecurityServer.class, args);
    }
}