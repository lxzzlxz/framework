package com.liuzemin.server.framework.fdfs.client;

import com.liuzemin.server.framework.model.helper.RHelper;
import com.liuzemin.server.framework.model.model.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "system", fallback = SystemClientFallback.class)
public interface SystemClient {

    @RequestMapping(value = "/dictionary/v1/all_dict", method = RequestMethod.GET)
    R<Map<String, String>> getAllDictByCode();
}


@Component
class SystemClientFallback implements SystemClient {
    @Override
    public R<Map<String, String>> getAllDictByCode() {
        // TODO Auto-generated method stub
        return RHelper.getServiceError("system");
    }


}