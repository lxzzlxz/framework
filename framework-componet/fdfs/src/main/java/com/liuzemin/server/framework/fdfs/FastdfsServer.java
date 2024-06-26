package com.liuzemin.server.framework.fdfs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.liuzemin.server")
@EnableHystrix
@EnableSwagger2
@EnableAsync
public class FastdfsServer {

    public static void main(String[] args) {

        SpringApplication.run(FastdfsServer.class, args);
    }
}
