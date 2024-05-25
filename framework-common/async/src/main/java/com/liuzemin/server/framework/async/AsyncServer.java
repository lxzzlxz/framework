package com.liuzemin.server.framework.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;

@SpringBootApplication
//@EnableBinding(value={RabbitmqProcessor.class})
public class AsyncServer {

    public static final Logger log = LoggerFactory.getLogger(AsyncServer.class);

    public static void main(String[] args) {

        SpringApplication.run(AsyncServer.class, args);
    }
}
