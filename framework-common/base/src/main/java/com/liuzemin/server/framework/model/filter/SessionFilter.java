package com.liuzemin.server.framework.model.filter;

import com.liuzemin.server.framework.model.context.Current;
import com.liuzemin.server.framework.model.context.RequestContext;
import com.liuzemin.server.framework.model.helper.UUIDHelper;
import com.liuzemin.server.framework.model.interceptor.FeignRequestInterceptor;
import com.liuzemin.server.framework.model.service.IAuthCheckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;

@Component
@ServletComponentScan
@WebFilter(filterName = "sessionFilter", urlPatterns = "/*")
public class SessionFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(SessionFilter.class);

    public static final Logger accessLog = LoggerFactory.getLogger("access");

    public static final Logger serviceLog = LoggerFactory.getLogger("service");

    @Autowired
    private IAuthCheckService authCheckService;

    @Value("${spring.application.name}")
    private String serviceName;


    private Boolean isServiceNameInit = false;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("sessionFilter init... ...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //设置服务key
        if(!isServiceNameInit){
            setServiceKey();
            isServiceNameInit = true;
        }

        HttpServletRequest req = null;
        HttpServletResponse res = null;
        if(servletRequest instanceof  HttpServletRequest){
            req = (HttpServletRequest) servletRequest;
            res = (HttpServletResponse) servletResponse;
        }

        req.setCharacterEncoding("utf-8");
        res.setCharacterEncoding("utf-8");
        setRequestContext(req, res);

        String method = req.getMethod();
        String queryString = req.getQueryString();
        String uri = req.getRequestURI();
        String remoteIp = getRemoteAddr(req);
        String localIp = req.getLocalAddr();
        
        //access log
        String pvId = generatePVID(req,remoteIp,localIp);
        StringBuilder accessLogSB = new StringBuilder();
        accessLogSB.append(System.currentTimeMillis()).append("\t").append("ACCESS").append("\t").append(pvId).append("\t")
                .append(localIp).append("\t").append(remoteIp).append("\t").append(uri).append("\t").append(queryString).append("\t");
        RequestContext.getCurrent().setAccessLog(accessLogSB);

        //service log
        StringBuilder serviceLogSB = new StringBuilder();
        RequestContext.getCurrent().setServiceLog(serviceLogSB);

        StringBuilder returnLogSB = new StringBuilder();
        RequestContext.getCurrent().setReturnLog(returnLogSB);

        //调试用，framework
//        String fromService = req.getHeader("from-service");
//        if(true/*StringUtils.isEmpty(fromService)*/){
//            filterChain.doFilter(servletRequest,servletResponse);
//            //accessLog
//            accessLog();
//            //service log
//            serviceLog(res, uri, pvId);
//            //return log
//            returnLog(res, pvId);
//
//            RequestContext.setCurrnet(null);
//            return;
//        }

//        if(StringUtils.isEmpty(fromService) || StringUtils.isEmpty(authCheckService.getService(fromService))){
//            try{
//                res.getWriter().print(ResultMapHelper.getServicePermissionDenied());
//            }catch (Exception e){
//                log.error("return to client error|", e);
//            }finally {
//                //accessLog
//                accessLog();
//                //service log
//                serviceLog(res, uri, pvId);
//                //return log
//                returnLog(res, pvId);
//
//                RequestContext.setCurrnet(null);
//                return;
//            }
//        }

        try{

            filterChain.doFilter(servletRequest,servletResponse);
        }catch (Exception e){
            log.error("", e);
        }finally {
            //accessLog
            accessLog();
            //service log
            serviceLog(res, uri, pvId);
            //return log
            returnLog(res, pvId);

            RequestContext.remove();
            //RequestContext.removeCurrent();
            //res.sendRedirect("/static/login.html");
        }
    }

    private void setServiceKey() {
        String key = UUIDHelper.getUUID();
        FeignRequestInterceptor.setServerKey(key);
        authCheckService.delServiceKey(serviceName);
        authCheckService.setServiceKey(serviceName, key);
    }

    private void accessLog() {
        accessLog.info(RequestContext.getCurrent().getAccessLog().toString());
    }

    private void returnLog(HttpServletResponse res, String pvId) {
        accessLog.info(System.currentTimeMillis() + "\t" + "RETURN" + "\t" + pvId + "\t" + res.getStatus() + "\t" + RequestContext.getCurrent().getReturnLog());
    }

    private void serviceLog(HttpServletResponse res,String uri, String pvId) {
        serviceLog.info( System.currentTimeMillis() + "\t"+ pvId + "\t" + uri + "\t" + res.getStatus()+ "\t"+ RequestContext.getCurrent().getServiceLog().toString());
    }

    private void setRequestContext(HttpServletRequest req, HttpServletResponse res) {
        Current current = RequestContext.initCurrent();
        current.setResponse(res);
        current.setRequest(req);
        String sessionId = req.getHeader("SESSIONID");
        if(null != sessionId) {
	        String[] ary = sessionId.split("\\$");
	        current.setToken(ary[0]);
	        current.setDomain(ary[1]);
        }
    }

    private String generatePVID(HttpServletRequest req, String remoteIP,String localIp){
        try{
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
            char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        }catch(Exception e){
            log.error("",e);
        }
        return UUIDHelper.getUUID();
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

    @Override
    public void destroy() {

        log.info("sessionFilter destory... ...");
    }
}
