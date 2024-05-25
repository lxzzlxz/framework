package com.liuzemin.server.framework.model.context;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.concurrent.Callable;

@Component
public class HystrixConcurrencyStrategyCustomize extends HystrixConcurrencyStrategy {

    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HystrixCustomizeCallable hystrixCustomizeCallable = new HystrixCustomizeCallable(attributes, callable);
        return hystrixCustomizeCallable;
    }

}

class HystrixCustomizeCallable<T> implements Callable<T>{

    private ServletRequestAttributes attributes;

    private Callable<T> callable;

    public HystrixCustomizeCallable(ServletRequestAttributes attributes, Callable<T> callable){
        this.attributes = attributes;
        this.callable = callable;
    }

    @Override
    public T call() throws Exception {
        try{
            if(null != this.attributes){
                RequestContextHolder.setRequestAttributes(this.attributes);
            }
            return this.callable.call();
        }finally {
            RequestContextHolder.resetRequestAttributes();
        }
    }
}
