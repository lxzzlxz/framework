package com.liuzemin.server.framework.zuul.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ZuulInit implements InitializingBean {

    public static final Logger log = LoggerFactory.getLogger(ZuulInit.class);

    @Autowired
    private Environment environment;

    private static String[] ignoreUrl;

    private static String[] notAllowedUrl;

    @Override
    public void afterPropertiesSet() throws Exception {
        initIgoreUrls();

        initNotAllowdUrls();
    }

    private void initIgoreUrls() {
        String ignoreCheckUrls = environment.getProperty("custom.ignore-check");
        if(!StringUtils.isEmpty(ignoreCheckUrls)){
            String[] ary = ignoreCheckUrls.split(",");
            ignoreUrl = new String[ary.length];
            for(int i=0;i<ary.length;i++){
                ignoreUrl[i] = ary[i].trim();
            }
        }else{
            ignoreUrl = new String[]{};
        }
    }

    private void initNotAllowdUrls() {
        String ignoreCheckUrls = environment.getProperty("custom.not-allowed");
        if(!StringUtils.isEmpty(ignoreCheckUrls)){
            String[] ary = ignoreCheckUrls.split(",");
            notAllowedUrl = new String[ary.length];
            for(int i=0;i<ary.length;i++){
                notAllowedUrl[i] = ary[i].trim();
            }
        }else{
            notAllowedUrl = new String[]{};
        }
    }

    public static String[] getIgnoreUrl(){

        return ignoreUrl;
    }

    public static String[] getNotAllowedUrl(){

        return notAllowedUrl;
    }
}
