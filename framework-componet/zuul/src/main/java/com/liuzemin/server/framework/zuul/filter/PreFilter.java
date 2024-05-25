package com.liuzemin.server.framework.zuul.filter;

import com.alibaba.fastjson.JSON;
import com.liuzemin.server.framework.zuul.feign.AuthFeignClient;
import com.liuzemin.server.framework.zuul.init.ZuulInit;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class PreFilter extends ZuulFilter {

    public static final Logger accessLog = LoggerFactory.getLogger("access");

    public static final Logger log = LoggerFactory.getLogger(PreFilter.class);

    @Autowired
    private AuthFeignClient authFeignClient;

    @Autowired
    private Environment environment;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    /**
     * session 标识
     */
    @Value("${session.domain}")
    private String authDomain;

    /**
     * web session超时时间
     */
    @Value("${session.expire}")
    private Integer expireSeconds;

    /**
     * app session超时时间
     */
    @Value("${session.app-expire}")
    private Integer appExpireSeconds;

    /**
     * service 名称
     */
    @Value("${spring.application.name}")
    private String serviceName;

    /**
     * service 标识
     */
    private static String serviceKey;

    /**
     * 服务信息是否已初始化
     */
    private Boolean isServiceNameInit = false;

    @Override
    public String filterType() {

        return "pre";
    }

    @Override
    public int filterOrder() {

        return 1;
    }

    @Override
    public boolean shouldFilter() {

        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //如果服务信息，没有初始化，初始化服务信息，用于服务之间权限控制
        if (!isServiceNameInit) {
            isServiceNameInit = true;
            setServiceKey();
        }

        RequestContext ctx = RequestContext.getCurrentContext();
        StringBuilder sb = new StringBuilder();
        HttpServletRequest req = ctx.getRequest();
        HttpServletResponse res = ctx.getResponse();
        //web 跨域 上线注释
        res.addHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        res.addHeader("Access-Control-Allow-Credentials", "true");
        try {
            String uri = req.getRequestURI();
            String remoteIp = getRemoteAddr(req);
            String localIp = req.getLocalAddr();
            String pvId = generatePVID(req, remoteIp, localIp);


            // access log
            sb.append(System.currentTimeMillis()).append("\t").append("ACCESS").append("\t").append(pvId).append("\t")
                    .append(localIp).append("\t").append(remoteIp).append("\t").append(uri).append("\t");
            accessLog.info(sb.toString());

            String sessionId = req.getRequestedSessionId();
            if (null == sessionId) {
                //重新生成session
                sessionId = req.getSession(true).getId();
            }
            Boolean notAllowed = notAllowed(uri);
            if (notAllowed) {
                ctx.set("pvId", pvId);
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(200);
                return null;
            }

            Boolean isIgnore = isIgore(uri);
            if (isIgnore) {
                setRequest(ctx, sessionId);
                ctx.set("pvId", pvId);
                return null;
            }

            //ctx.getResponse().addHeader("set-cookie","JSESSIONID="+sessionId);
            Map<String, Object> map = authFeignClient.check(authDomain, sessionId);
            if (null != map && "0".equals(map.get("code"))) {
                setRequest(ctx, sessionId);
                ctx.set("pvId", pvId);
                return null;
            }

            Map<String, Object> result = new HashMap<>(16);
            result.put("code", "1");
            result.put("msg", "请登录");

            ctx.set("pvId", pvId);
            ctx.getResponse().setContentType("application/json;charset=utf-8");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(200);
            ctx.setResponseBody(JSON.toJSONString(result));// 返回错误内容
            return null;
        } catch (NoSuchAlgorithmException e) {
            log.error("generatePVID error", e);
        }
        return null;
    }

    private Boolean notAllowed(String uri) {
        String s = uri.replaceAll(contextPath, "");
        for (String reg : ZuulInit.getNotAllowedUrl()) {
            if (s.matches(reg)) {
                return true;
            }
        }
        return false;
    }

    private Boolean isIgore(String uri) {
        String s = uri.replaceAll(contextPath, "");
        for (String reg : ZuulInit.getIgnoreUrl()) {
            if (s.matches(reg)) {
                return true;
            }
        }
        return false;
    }

    private void setRequest(RequestContext ctx, String sessionId) {
        ctx.addZuulRequestHeader("SESSIONID", sessionId + "$" + authDomain);
        ctx.setSendZuulResponse(true);// 对该请求进行路由
        ctx.setResponseStatusCode(200);
    }

    private void setServiceKey() {
        if (!isServiceNameInit) {
            String key = UUID.randomUUID().toString().replaceAll("-", "");
            serviceKey = key;
            Map<String, Object> map = authFeignClient.delServiceKey(serviceName);
            map = authFeignClient.setServiceKey(serviceName, serviceKey);
            log.info(map.toString());
            isServiceNameInit = true;
        }
    }

    private String generatePVID(HttpServletRequest req, String remoteIP, String localIp) throws NoSuchAlgorithmException {
        String now = String.valueOf(System.currentTimeMillis());
        String uri = req.getRequestURI();

        // md5 加密
        byte[] btInput = (remoteIP + now + uri + localIp).getBytes();
        MessageDigest mdInst = MessageDigest.getInstance("MD5");
        mdInst.update(btInput);
        byte[] md = mdInst.digest();

        // 把密文转换成十六进制的字符串形式
        int j = md.length;
        char str[] = new char[j * 2];
        int k = 0;
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    }

    private String getRemoteAddr(HttpServletRequest req) {
        String remoteIP = req.getRemoteAddr();

        Object xff = req.getHeader("X-Forwarded-For");
        if (xff instanceof String) {
            String strXff = (String) xff;
            String tip = strXff.split(",")[0];
            if (tip.length() > 0) {
                remoteIP = tip;
            }
        }

        return remoteIP;
    }

    public static String getServiceKey() {
        return serviceKey;
    }
}
