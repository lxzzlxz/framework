package com.liuzemin.server.framework.model.context;

import com.alibaba.fastjson.JSON;
import com.liuzemin.server.framework.model.service.IAuthCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class RequestContext {

    public static final Logger log = LoggerFactory.getLogger(RequestContext.class);

    private static ThreadLocal<Current> current = new ThreadLocal<Current>();

    private static IAuthCheckService authCheckService;

    public static void setCurrnet(Current cur) {
        current.set(cur);
    }

    public static Current getCurrent() {
        Current cur = current.get();
        log.info("current:" + (null != cur));
        if (null != cur && !cur.getGetData() && null != cur.getToken()) {
            if (null == authCheckService) {
                authCheckService = (IAuthCheckService) AppContext.getContext().getBean("authCheckService");
            }
            String json = authCheckService.getData(cur.getDomain(), cur.getToken());
            if (!StringUtils.isEmpty(json)) {
                Object parse = JSON.parse(json);
                Current cache = JSON.parseObject(parse.toString(), Current.class);
                cur.setUser(cache.getUser());
                cur.setPermissions(cache.getPermissions());
                cur.setSupplierId(cache.getSupplierId());
                cur.setSupplier(cache.getSupplier());
                cur.setEnterpriseIds(cache.getEnterpriseIds());
                cur.setEnterpriseName(cache.getEnterpriseName());
                cur.setProjectNameMap(cache.getProjectNameMap());
                cur.setGetData(true);
            }
        }
        return cur;
    }

    public static Current initCurrent() {
        current.remove();
        current.set(new Current());

        return current.get();
    }

    public static void remove() {
        current.remove();
    }

    public static ApplicationContext getApplicationContext() {

        return AppContext.getContext();
    }

    public static HttpServletRequest getHttpServletRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    public static WebApplicationContext getWebApplicationContext() {

        return ContextLoader.getCurrentWebApplicationContext();
    }

    public static ServletContext getServletContext() {
        WebApplicationContext wac = getWebApplicationContext();
        if (null == wac) {
            return null;
        }
        return wac.getServletContext();
    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        authCheckService = (IAuthCheckService) AppContext.getContext().getBean("authCheckService");
//    }
}
