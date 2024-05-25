package com.liuzemin.server.framework.auth.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CommonController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/info")
    public String info(){
        System.getProperties();
        return "auth service";
    }

    @RequestMapping("/home")
    public String home(HttpServletRequest req){

        return req.getLocalAddr()+":"+port;
    }

    @RequestMapping("/health")
    public String health() {

        return "auth service active";
    }
}
