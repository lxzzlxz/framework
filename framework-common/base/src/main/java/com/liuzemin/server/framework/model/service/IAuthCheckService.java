package com.liuzemin.server.framework.model.service;

import java.util.Map;

/**
 * 登录校验
 * weihao 2018-07-05
 */
public interface IAuthCheckService {

    /**
     * 校验
     * @param sessionId
     * @return
     */
    Boolean check(String domain, String sessionId);

    /**
     * 登录
     * @param sessionId
     * @param obj
     * @return
     */
    Boolean login(String domain, String sessionId, String userId, String obj, int seconds);

    /**
     * 登录
     * @param sessionId
     * @param obj
     * @return
     */
    Boolean appLogin(String domain, String sessionId, String userId, String obj, int seconds);

    /**
     * 登出
     * @param sessionId
     * @return
     */
    Boolean logout(String domain, String sessionId);

    /**
     * 缓存数据
     * @param sessionId
     * @param obj
     * @return
     */
    Boolean setData(String domain, String sessionId, String obj);

    /**
     * 获取缓存数据
     * @param sessionId
     * @return
     */
    String getData(String domain, String sessionId);

    /**
     * 服务认证，设置服务令牌
     * @param serviceName
     * @param key
     * @return
     */
    Map<String,Object> setServiceKey(String serviceName, String key);

    /**
     * 删除服务令牌
     * @param serviceName
     * @return
     */
    Map<String,Object> delServiceKey(String serviceName);

    /**
     * 根据令牌获取服务名称
     * @param key
     * @return
     */
    String getService(String key);
}
