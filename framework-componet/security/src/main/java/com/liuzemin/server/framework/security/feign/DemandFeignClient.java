package com.liuzemin.server.framework.security.feign;

import com.liuzemin.server.framework.model.helper.RHelper;
import com.liuzemin.server.framework.model.helper.ResultMapHelper;
import com.liuzemin.server.framework.model.model.DemandCompanyAdmin;
import com.liuzemin.server.framework.model.model.Project;
import com.liuzemin.server.framework.model.model.ProjectInfo;
import com.liuzemin.server.framework.model.model.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "demand", fallback = DemandFallBack.class)
public interface DemandFeignClient {

    @RequestMapping(value = "/socialdemanders/v1/deleteByUserId", method = RequestMethod.DELETE)
    R<String> deleteSocialDemandersByUserId(@RequestParam("userId") Long userId);

    @RequestMapping(value = "/project/v1/projectFindList", method = RequestMethod.POST)
    R<List<Project>> findProjectList(@RequestBody Project project);

    @RequestMapping(value = "/demandCompanyAdmin/v1/findList", method = RequestMethod.POST)
    R<List<DemandCompanyAdmin>> findCompanyAdminList(@RequestBody DemandCompanyAdmin demandCompanyAdmin);

    @RequestMapping(value = "/demandInfo/v1/findProjectUnitById", method = RequestMethod.POST)
    Map<String, Object> findProjectInfoList(@RequestBody List<Long> ids);


    @RequestMapping(value = "/demandInfo/v1/findProjectUnitList", method = RequestMethod.POST)
    R<List<ProjectInfo>> findProjectUnitList(@RequestBody List<Long> ids);

}

@Component
class DemandFallBack implements DemandFeignClient {


    @Override
    public R<String> deleteSocialDemandersByUserId(Long userId) {
        return RHelper.getServiceErrorR("demand");
    }

    @Override
    public R<List<Project>> findProjectList(Project project) {
        return RHelper.getServiceErrorR("demand");
    }

    @Override
    public R<List<DemandCompanyAdmin>> findCompanyAdminList(DemandCompanyAdmin demandCompanyAdmin) {
        return RHelper.getServiceErrorR("demand");
    }

    @Override
    public Map<String, Object> findProjectInfoList(List<Long> ids) {
        return ResultMapHelper.getServiceErrorMap("demand");
    }

    @Override
    public R<List<ProjectInfo>> findProjectUnitList(List<Long> ids) {
        return RHelper.getServiceErrorR("demand");
    }


}
