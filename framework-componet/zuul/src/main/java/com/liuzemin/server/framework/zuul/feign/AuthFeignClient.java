package com.liuzemin.server.framework.zuul.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@FeignClient(value = "auth", fallback = AuthFallBack.class)
public interface AuthFeignClient {

    @RequestMapping(value = "/auth/v1/check", method = RequestMethod.GET)
    public Map<String, Object> check(@RequestParam("domain") String domain, @RequestParam("sessionId") String sessionId);

    @RequestMapping(value = "/auth/v1/setServiceKey", method = RequestMethod.GET)
    public Map<String, Object> setServiceKey(@RequestParam("serviceName") String sessionId, @RequestParam("sessionId") String serviceName);

    @RequestMapping(value = "/auth/v1/delServiceKey", method = RequestMethod.GET)
    public Map<String, Object> delServiceKey(@RequestParam("serviceName") String serviceName);
}

@Component
class AuthFallBack implements  AuthFeignClient{

    @Override
    public Map<String, Object> check(String domain, String sessionId) {
        Map<String,Object> map = new HashMap<String,Object>(16);
        map.put("code","1");
        map.put("msg", "auth service error!");
        return map;
    }

    @Override
    public Map<String, Object> setServiceKey(String sessionId, String serviceName) {
        Map<String,Object> map = new HashMap<String,Object>(16);
        map.put("code","1");
        map.put("msg", "auth service error!");
        return map;
    }

    @Override
    public Map<String, Object> delServiceKey(String serviceName) {
        Map<String,Object> map = new HashMap<String,Object>(16);
        map.put("code","1");
        map.put("msg", "auth service error!");
        return map;
    }
}
