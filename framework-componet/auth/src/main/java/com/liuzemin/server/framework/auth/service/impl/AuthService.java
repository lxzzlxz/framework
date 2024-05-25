package com.liuzemin.server.framework.auth.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.liuzemin.server.framework.auth.service.IAuthService;

@Service
public class AuthService implements IAuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private static final String SESSION_PREFIX = "legao_session_";

    private static final String USER_ID_PREFIX = "legao_userid_";

    private static final String SESSION_DATA_PREFIX = "legao_session_data_";

    private static final String SESSION_EXPIRE_PREFIX = "legao_expire_";

    public static final String SERVICE_PREFIX = "legao_service_";

    public static final String SERVICE_TOKEN_PREFIX = "legao_service_token_";

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Map<String, Object> check(String domain, String sessionId) {
        Map<String,Object> map = new HashMap<>(16);
        String key = SESSION_PREFIX + domain + "_" + sessionId;
        String dataKey = SESSION_DATA_PREFIX + domain + "_"+ sessionId;
        String userId = (String) redisTemplate.opsForValue().get(key);
        String userKey = USER_ID_PREFIX+domain+"_"+userId;
        String expireKey = SESSION_EXPIRE_PREFIX + domain + "_"+ sessionId;
        Integer expire = (Integer) redisTemplate.opsForValue().get(expireKey);
        if(!StringUtils.isEmpty(userId) && null != expire){
            map.put("code","0");
            map.put("msg","ok");
            redisTemplate.expire(key, expire,TimeUnit.SECONDS);
            redisTemplate.expire(userKey, expire, TimeUnit.SECONDS);
            redisTemplate.expire(dataKey,expire, TimeUnit.SECONDS);
            redisTemplate.expire(expireKey, expire, TimeUnit.SECONDS);
            return map;
        }
        map.put("code","1");
        map.put("msg","fail");
        return map;
    }

    @Override
    public Map<String, Object> login(String domain, String sessionId, String userId,String body, int second) {
        Map<String,Object> map = new HashMap<>(16);
        String sessionKey = SESSION_PREFIX+domain+"_"+sessionId;
        String userKey = USER_ID_PREFIX+domain+"_"+userId;
        String dataKey = SESSION_DATA_PREFIX + domain + "_"+ sessionId;
        String expireKey = SESSION_EXPIRE_PREFIX + domain + "_"+ sessionId;

        redisTemplate.opsForValue().set(sessionKey,userId,second,TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(userKey,sessionId,second,TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(dataKey,body,second,TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(expireKey,second,second,TimeUnit.SECONDS);
        map.put("code","0");
        map.put("msg","ok");
        return map;
    }

    @Override
    public Map<String, Object> appLogin(String domain, String sessionId, String userId,String body, int second) {
        Map<String,Object> map = new HashMap<>(16);
        String sessionKey = SESSION_PREFIX+domain+"_"+sessionId;
        String userKey = USER_ID_PREFIX+domain+"_"+userId;
        String dataKey = SESSION_DATA_PREFIX + domain + "_"+ sessionId;
        String expireKey = SESSION_EXPIRE_PREFIX + domain + "_"+ sessionId;

        String originSessionId = (String) redisTemplate.opsForValue().get(userKey);
        if(!StringUtils.isEmpty(originSessionId)){
            redisTemplate.delete(userKey);
            redisTemplate.delete(SESSION_PREFIX+domain+"_"+originSessionId);
            redisTemplate.delete(SESSION_DATA_PREFIX + domain + "_"+ originSessionId);
            redisTemplate.delete(SESSION_EXPIRE_PREFIX + domain + "_"+ originSessionId);
        }

        redisTemplate.opsForValue().set(sessionKey,userId,second,TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(userKey,sessionId,second,TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(dataKey,body,second,TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(expireKey,second,second,TimeUnit.SECONDS);
        map.put("code","0");
        map.put("msg","ok");
        return map;
    }

    @Override
    public Map<String, Object> logOut(String domain, String sessionId) {
        Map<String,Object> map = new HashMap<>(16);
        String sessionKey = SESSION_PREFIX+domain+"_"+sessionId;
        String dataKey = SESSION_DATA_PREFIX+domain+"_"+sessionId;
        String userId = (String) redisTemplate.opsForValue().get(sessionKey);
        String userKey = USER_ID_PREFIX+domain+"_"+userId;
        String expireKey = SESSION_EXPIRE_PREFIX + domain + "_"+ sessionId;

        redisTemplate.delete(sessionKey);
        redisTemplate.delete(userKey);
        redisTemplate.delete(dataKey);
        redisTemplate.delete(expireKey);

        map.put("code","0");
        map.put("msg","ok");
        return map;
    }

    @Override
    public Map<String, Object> setData(String domain, String sessionId, String obj) {
        Map<String,Object> map = new HashMap<>(16);

        String sessionKey = SESSION_PREFIX+domain+"_"+sessionId;
        String dataKey = SESSION_DATA_PREFIX+domain+"_"+sessionId;
        String userId = (String) redisTemplate.opsForValue().get(sessionKey);
        String userKey = USER_ID_PREFIX+domain+"_"+userId;
        String expireKey = SESSION_EXPIRE_PREFIX + domain + "_"+ sessionId;

        Integer expire = (Integer) redisTemplate.opsForValue().get(expireKey);

        redisTemplate.opsForValue().set(dataKey, obj, expire,TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(sessionKey, userId, expire,TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(userKey, sessionId, expire,TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(expireKey,expire,expire,TimeUnit.SECONDS);

        map.put("code","0");
        map.put("msg","ok");
        return map;
    }

    @Override
    public String getData(String domain, String sessionId) {
        String dataKey = SESSION_DATA_PREFIX+domain+"_"+sessionId;
        Object obj = redisTemplate.opsForValue().get(dataKey);
        if(null == obj){
            return null;
        }
        return (String)obj;
    }

    @Override
    public Map<String, Object> setServiceKey(String serviceName, String key) {
        String serviceKey = SERVICE_PREFIX+"crec_"+serviceName;
        String serviceTokenKey = SERVICE_TOKEN_PREFIX + "crec_" + key;
        redisTemplate.opsForValue().set(serviceKey, key);
        redisTemplate.opsForValue().set(serviceTokenKey, serviceName);
        Map<String,Object> map = new HashMap<>(16);
        map.put("code","0");
        map.put("msg","ok");
        return map;
    }

    @Override
    public Map<String, Object> delServiceKey(String serviceName) {
        String serviceKey = SERVICE_PREFIX+"crec_"+serviceName;
        String key = (String) redisTemplate.opsForValue().get(serviceKey);
        String serviceTokenKey = SERVICE_TOKEN_PREFIX  + "crec_" + key;
        redisTemplate.delete(serviceTokenKey);
        Map<String,Object> map = new HashMap<>(16);
        map.put("code","0");
        map.put("msg","ok");
        return map;
    }

    @Override
    public String getService(String key) {
        String serviceTokenKey = SERVICE_TOKEN_PREFIX  + "crec_" + key;
        String serviceName = (String) redisTemplate.opsForValue().get(serviceTokenKey);
        return serviceName;
    }

}
