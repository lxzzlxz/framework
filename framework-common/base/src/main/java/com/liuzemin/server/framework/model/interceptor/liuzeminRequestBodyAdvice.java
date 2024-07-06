package com.liuzemin.server.framework.model.interceptor;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liuzemin.server.framework.model.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;

@ControllerAdvice
public class liuzeminRequestBodyAdvice implements RequestBodyAdvice {

	private static final Logger log = LoggerFactory.getLogger(liuzeminRequestBodyAdvice.class);

	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {

		return true;
	}

	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) throws IOException {

		return inputMessage;
	}

	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		if(null != body){
			ObjectMapper objectMapper =new ObjectMapper();
			try {
				RequestContext.getCurrent().getAccessLog().append(objectMapper.writeValueAsString(body));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}else{
			RequestContext.getCurrent().getAccessLog().append(JSON.toJSONString(""));
		}
		return body;
	}

	@Override
	public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
			Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {

		return body;
	}

}
