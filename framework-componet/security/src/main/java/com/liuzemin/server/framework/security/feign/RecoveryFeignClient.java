package com.liuzemin.server.framework.security.feign;

import com.liuzemin.server.framework.model.helper.RHelper;
import com.liuzemin.server.framework.model.model.R;
import com.liuzemin.server.framework.security.user.vo.Recovery;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "recovery", fallback = RecoveryFallBack.class)
public interface RecoveryFeignClient {
    @RequestMapping(value = "/recovery/v1/findRecoveryList", method = RequestMethod.GET)
    R<List<Recovery>> findRecoveryList(@RequestBody Recovery recovery);
}

@Component
class RecoveryFallBack implements RecoveryFeignClient {
    @Override
    public R<List<Recovery>> findRecoveryList(Recovery recovery) {
        return RHelper.getServiceError("recovery");
    }

}
