package com.liuzemin.server.framework.security.feign;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@FeignClient(value = "system", fallback = UserAuthenticateClientFallback.class)
public interface UserAuthenticateClient {

    @RequestMapping(value = "/userAuthenticate/v1/authenticate", method= RequestMethod.GET)
    public Map<String, Object> authenticate(@RequestParam("realname") String realname, @RequestParam("idCardNO") String idCardNO);

}

@Component
class UserAuthenticateClientFallback implements UserAuthenticateClient{

    public static final  Logger logger = LoggerFactory.getLogger(UserAuthenticateClientFallback.class);


    @Override
    public Map<String, Object> authenticate(String realname, String idCardNO) {
        logger.error("security UserAuthenticateClient authenticate error");
        return Collections.emptyMap();
    }
}
