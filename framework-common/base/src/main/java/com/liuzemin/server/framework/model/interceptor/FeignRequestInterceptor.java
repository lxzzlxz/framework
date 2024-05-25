package com.liuzemin.server.framework.model.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class FeignRequestInterceptor implements RequestInterceptor {

    @Value("${spring.application.name}")
    private String serviceName;

    private static String serverKey;

    @Override
    public void apply(RequestTemplate requestTemplate) {
       requestTemplate.header("from-service", serverKey);
       ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
       if(null != attributes){
           HttpServletRequest request = attributes.getRequest();
            String sessionId = request.getHeader("SESSIONID");
            if(!StringUtils.isEmpty(sessionId)){
                requestTemplate.header("SESSIONID", sessionId);
            }
       }
    }
    public static void setServerKey(String key){
        FeignRequestInterceptor.serverKey = key;
    }
}
