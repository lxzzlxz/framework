package com.liuzemin.server.framework.security.feign;

import com.liuzemin.server.framework.model.helper.RHelper;
import com.liuzemin.server.framework.model.model.R;
import com.liuzemin.server.framework.model.model.Supplier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "supplier", fallback = SupplierFallBack.class)
public interface SupplierFeignClient {

    @RequestMapping(value = "/supplier/v1/findListByNoPerminsion", method = RequestMethod.GET)
    R<List<Supplier>> findList(@RequestParam("userId") Long userId, @RequestParam("approveStatus") Integer approveStatus, @RequestParam("status") Integer status);

    @RequestMapping(value = "/supplier/v1/findSupplierList", method = RequestMethod.GET)
    R<List<Supplier>> findSupplierList(@RequestBody Supplier supplier);

    @RequestMapping(value = "/supplier/v1/deleteSupplierByUserId", method = RequestMethod.DELETE)
    R<String> deleteSupplierByUserId(@RequestParam("userId") Long userId);

    @RequestMapping(value = "/supplier/v1/getAll", method = RequestMethod.GET)
    R<List<Supplier>> getAll();


}

@Component
class SupplierFallBack implements SupplierFeignClient {

    @Override
    public R<List<Supplier>> findList(Long userId, Integer approveStatus, Integer status) {
        // TODO Auto-generated method stub
        return RHelper.getServiceError("supplier");
    }

    @Override
    public R<List<Supplier>> findSupplierList(Supplier supplier) {
        return RHelper.getServiceError("supplier");
    }

    @Override
    public R<String> deleteSupplierByUserId(Long id) {
        return RHelper.getServiceError("supplier");
    }

    @Override
    public R<List<Supplier>> getAll() {
        return RHelper.getServiceError("supplier");
    }
}
