package com.liuzemin.server.framework.async.processor;


import com.liuzemin.server.framework.async.conduit.ConduitType;
import com.liuzemin.server.framework.async.context.AsyncContext;
import com.liuzemin.server.framework.async.model.AsyncMessage;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

@Component
public class AsyncFramework implements InitializingBean {

    private static Map<String,IAsyncProcessor> processorMap = new HashMap<>(16);

    @Value("${async.default.conduitType}")
    private String conduitType;

    private static Integer defaultConduitType;

    @Override
    public void afterPropertiesSet() throws Exception {

        //异步处理器
        //initProcessorMap();

        initDefaultConduitType();
    }

    private void initDefaultConduitType() {
        AsyncFramework.defaultConduitType = Integer.valueOf(conduitType);
        if(null == AsyncFramework.defaultConduitType){
            AsyncFramework.defaultConduitType = 1;
        }
    }

    private void initProcessorMap() {
        ApplicationContext applicationContext = AsyncContext.getApplicationContext();
        String[] beanNames = applicationContext.getBeanNamesForType(IAsyncProcessor.class);
        if(null != beanNames && beanNames.length > 0){
            for(String beanName : beanNames){
                Object o = applicationContext.getBean(beanName);
                if(null != o && o instanceof  IAsyncProcessor){
                    AsyncFramework.processorMap.put(beanName, (IAsyncProcessor) o);
                }
            }
        }
    }

    public static IAsyncProcessor getProcessor(AsyncMessage asyncMessage){

        return AsyncFramework.processorMap.get(asyncMessage.getProcessName());
    }

    public static Integer getDefaultConduitType(){

        return AsyncFramework.defaultConduitType;
    }
}
