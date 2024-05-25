package com.liuzemin.server.framework.security.feign;

import com.liuzemin.server.framework.model.helper.RHelper;
import com.liuzemin.server.framework.model.model.KeyValueModel;
import com.liuzemin.server.framework.model.model.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "system", fallback = SystemMessageClientFallback.class)
public interface SystemClient {

    @RequestMapping(value = "/dictionary/v1/all_dict", method = RequestMethod.GET)
    R<Map<String, String>> getAllDictByCode();

    @RequestMapping(value = "/enterprise/deleteByUserId", method = RequestMethod.DELETE)
    R<String> deleteByUserId(@RequestParam(value = "userId") Long userId);

    @RequestMapping(value = "/sensitiveWord/check", method = RequestMethod.POST)
    R<String> check(@RequestBody List<KeyValueModel> modelList);


}


@Component
class SystemMessageClientFallback implements SystemClient {
    public static final Logger log = LoggerFactory.getLogger(SystemMessageClientFallback.class);

    @Override
    public R<Map<String, String>> getAllDictByCode() {
        // TODO Auto-generated method stub
        return RHelper.getServiceError("system");
    }

    @Override
    public R<String> deleteByUserId(Long userId) {
        return RHelper.getServiceError("system");
    }

    @Override
    public R<String> check(List<KeyValueModel> modelList) {
        return RHelper.getServiceError("system");
    }

}
