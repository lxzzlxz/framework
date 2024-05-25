package com.liuzemin.server.framework.async.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.liuzemin.server.framework.async.processor.IAsyncProcessor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ThreadRunable implements Runnable {

    private IAsyncProcessor asyncProcessor;

    private AsyncMessage asyncMessage;

    private ServletRequestAttributes servletRequestAttributes;

    public ThreadRunable(IAsyncProcessor processor, AsyncMessage message,ServletRequestAttributes servletRequestAttributes){
        this.asyncProcessor = processor;
        this.asyncMessage = message;
        this.servletRequestAttributes = servletRequestAttributes;
    }

    @Override
    public void run() {
        try{
            if(null != servletRequestAttributes){
                RequestContextHolder.setRequestAttributes(servletRequestAttributes);
            }
            asyncProcessor.process(asyncMessage);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            RequestContextHolder.resetRequestAttributes();
        }
    }
}
