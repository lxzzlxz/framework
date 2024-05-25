package com.liuzemin.server.framework.model.interceptor;

import com.liuzemin.server.framework.model.annotation.Operation;
import com.liuzemin.server.framework.model.annotation.Resource;
import com.liuzemin.server.framework.model.context.Current;
import com.liuzemin.server.framework.model.context.RequestContext;
import com.liuzemin.server.framework.model.helper.RHelper;
import com.liuzemin.server.framework.model.helper.ResultMapHelper;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@Component
public class SecurityInterceptor implements MethodInterceptor {

    private static final Logger log = LoggerFactory.getLogger(SecurityInterceptor.class);

    @Value("${spring.application.name}")
    private String scope;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Class<?> c = invocation.getMethod().getDeclaringClass();
        Resource resource = c.getAnnotation(Resource.class);
        Operation operation = invocation.getMethod().getAnnotation(Operation.class);
        Current current = RequestContext.getCurrent();
        if (null != resource && null != operation && null != current && !current.getPermissionChecked()) {
            String permission = scope + "$" + resource.value() + "$" + operation.value();
            if (null == current.getPermissions() || !current.getPermissions().contains(permission)) {
                Class returnType = invocation.getMethod().getReturnType();
                if ("java.util.Map".equals(returnType.getName())) {
                    return ResultMapHelper.getPermissionDenied();
                } else if ("com.liuzemin.server.framework.model.model.R".equals(returnType.getName())) {
                    return RHelper.getPermissionDenied();
                } else if ("java.util.List".equals(returnType.getName())) {
                    return Collections.EMPTY_LIST;
                } else if ("void".equals(returnType.getName())) {
                    ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                    HttpServletResponse response = servletRequestAttributes.getResponse();
                    ServletOutputStream outputStream = response.getOutputStream();
                    String res = "No Permission !";
                    byte[] b = res.getBytes();
                    outputStream.write(b);
                    outputStream.flush();
                    outputStream.close();
                }
                return null;
            }
            current.setPermissionChecked(true);
        }

        return invocation.proceed();
    }

}
