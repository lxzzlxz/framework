package com.liuzemin.server.framework.async.controller;

import com.liuzemin.server.framework.async.model.AsyncMessage;
import com.liuzemin.server.framework.async.selector.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

//    @Autowired
//    private RabbitmqProcessor rabbitmqProcessor;
//
//    @RequestMapping("/test")
//    public String test(){
//        rabbitmqProcessor.output().send(MessageBuilder.withPayload("test").build());
//        for(int i=0;i<500;i++){
//            AsyncMessage asyncMessage = new AsyncMessage("async task "+i,"testProcessor");
//            MessageSender.send(asyncMessage);
//        }
//        return "success";
//    }

}
