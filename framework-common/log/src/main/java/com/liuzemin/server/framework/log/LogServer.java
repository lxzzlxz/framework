package com.liuzemin.server.framework.log;//package com.liuzemin.server.framework.log;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import javax.annotation.PostConstruct;
//
//@SpringBootApplication
//public class LogServer {
//
//    public static void main(String[] args) {
//
//        SpringApplication.run(LogServer.class, args);
//    }
//
//    @PostConstruct
//    public void start(){
//        Logger log = LoggerFactory.getLogger(LogServer.class);
//        Logger access = LoggerFactory.getLogger("access");
//        Logger service = LoggerFactory.getLogger("service");
//
//        log.info("error");
//        access.info(System.currentTimeMillis()+"\t"+"access");
//        service.info(System.currentTimeMillis()+"\t"+"service");
//    }
//}
