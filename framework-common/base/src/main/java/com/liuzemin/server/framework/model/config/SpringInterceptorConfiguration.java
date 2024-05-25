package com.liuzemin.server.framework.model.config;

import com.liuzemin.server.framework.model.filter.JsonMethodReturnValueHandler;
import com.liuzemin.server.framework.model.interceptor.RepeatSubmitInterceptor;
import com.liuzemin.server.framework.model.interceptor.SecurityInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@Configuration
public class SpringInterceptorConfiguration implements WebMvcConfigurer {

	private final static Logger log = LoggerFactory.getLogger(SpringInterceptorConfiguration.class);
	@Autowired
	private JsonMethodReturnValueHandler jsonHandlerMethodArgumentResolver;	@Autowired
	private RepeatSubmitInterceptor repeatSubmitInterceptor;
	@Deprecated
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(repeatSubmitInterceptor);
		if (log.isInfoEnabled()) {
			log.info("add log interceptor... ...");
		}
		//registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/**");
	}
	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
		handlers.add(jsonHandlerMethodArgumentResolver);
		WebMvcConfigurer.super.addReturnValueHandlers(handlers);
	}
	@Bean
	public Advisor securityAdvisor(SecurityInterceptor securityInterceptor){
		AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
		pc.setExpression("execution( * com.liuzemin.server..controller.*Controller.*(..))");
		return new DefaultPointcutAdvisor(pc, securityInterceptor);
	}
}
