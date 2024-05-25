package com.liuzemin.server.framework.zuul.interceptor;

import com.liuzemin.server.framework.zuul.filter.PreFilter;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FeignRequestInterceptor implements RequestInterceptor {

    public static final Logger log = LoggerFactory.getLogger(FeignRequestInterceptor.class);

    @Override
    public void apply(RequestTemplate requestTemplate) {

        requestTemplate.header("from-service", PreFilter.getServiceKey());
    }
}
