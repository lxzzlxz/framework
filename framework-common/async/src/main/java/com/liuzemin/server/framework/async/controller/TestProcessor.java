package com.liuzemin.server.framework.async.controller;

import com.liuzemin.server.framework.async.model.AsyncMessage;
import com.liuzemin.server.framework.async.processor.IAsyncProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TestProcessor implements IAsyncProcessor {

    public static final Logger log = LoggerFactory.getLogger(TestProcessor.class);

    @Override
    public void process(AsyncMessage asyncMessage) {
        String content = (String) asyncMessage.getContent();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.error(Thread.currentThread().getName() + " "+content);
    }
}
