package com.liuzemin.server.framework.security.feign;

import com.liuzemin.server.framework.model.helper.RHelper;
import com.liuzemin.server.framework.model.model.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(value = "demand", fallback = SocialDemandFallBack.class)
public interface SocialDemandFeignClient {

    @GetMapping(value = "/socialdemandadmin/v1/getAllSocialDemandUserIdCompanyMap")
    R<Map<Long, String>> getAllSocialDemandUserIdCompanyMap();


}

@Component
class SocialDemandFallBack implements SocialDemandFeignClient {

    @Override
    public R<Map<Long, String>> getAllSocialDemandUserIdCompanyMap() {
        return RHelper.getServiceErrorR("demand");
    }
}
