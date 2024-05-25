package com.liuzemin.server.framework.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class PostFilter extends ZuulFilter {

    public static final Logger accessLog = LoggerFactory.getLogger("access");

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {

        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        StringBuilder sb = new StringBuilder();
        HttpServletRequest req = ctx.getRequest();
        HttpServletResponse res = ctx.getResponse();
        String pvId = (String) ctx.get("pvId");
        sb.append(System.currentTimeMillis()).append("\t").append("RETURN").append("\t").append(pvId).append("\t").append(res.getStatus());
        accessLog.info(sb.toString());

        ctx.setSendZuulResponse(true);// 对该请求进行路由
        ctx.setResponseStatusCode(200);
        ctx.set("pvId", pvId);
        return null;
    }
}
