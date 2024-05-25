package com.liuzemin.server.framework.async.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AsyncContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AsyncContext.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        return AsyncContext.applicationContext;
    }
}
