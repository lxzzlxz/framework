package com.liuzemin.server.framework.security.feign;

import com.liuzemin.server.framework.model.helper.ResultMapHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "sms", fallback = SmsClientFallback.class)
public interface SmsClient {

	@RequestMapping(value = "/sms/v1/send", method = RequestMethod.GET)
	Map<String, Object> send(@RequestParam("phone") String phone);

	@RequestMapping(value = "/sms/v1/validate", method = RequestMethod.GET)
	Map<String, Object> validate(@RequestParam("phone") String phone, @RequestParam("code") String code);

}

@Component
class SmsClientFallback implements SmsClient {
	public static final Logger log = LoggerFactory.getLogger(SmsClientFallback.class);

	@Override
	public Map<String, Object> validate(String phone, String code) {
		log.error("sms client validate code error!");
		return ResultMapHelper.getServiceErrorMap("sms");
	}

	@Override
	public Map<String, Object> send(String phone) {
		log.error("sms client validate code error!");
		return ResultMapHelper.getServiceErrorMap("sms");
	}
}
