package com.liuzemin.server.framework.security.feign;

import com.liuzemin.server.framework.model.helper.RHelper;
import com.liuzemin.server.framework.model.model.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "demand", fallback = DemandAdminFallBack.class)
public interface DemandAdminFeignClient {
    @GetMapping(value = "/demandAdmin/v1/getAllAdminUserId")
    R<List<Long>> getAllAdminUserIds();
}

@Component
class DemandAdminFallBack implements DemandAdminFeignClient {

    @Override
    public R<List<Long>> getAllAdminUserIds() {
        return RHelper.getServiceErrorR("demand");
    }
}
