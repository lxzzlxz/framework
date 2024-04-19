package com.liuzemin.server.framework.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = "com.test")
@EnableSwagger2
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.test")
@EnableHystrix
@EnableScheduling
//@MapperScan(basePackages = {"com.legao.server.framework.*.*.dao"})
public class SystemServer {

    public static void main(String[] args) {

        SpringApplication.run(SystemServer.class, args);
    }
}
