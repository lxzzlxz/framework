package com.liuzemin.server.framework.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.liuzemin.server")
@EnableHystrix
@EnableZuulProxy
@EnableSwagger2
public class ZuulServer {

    public static void main(String[] args) {

        SpringApplication.run(ZuulServer.class, args);
    }
}
