package com.liuzemin.server.framework.security.permission.controller;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.model.annotation.Resource;
import com.liuzemin.server.framework.model.model.BaseService;
import com.liuzemin.server.framework.security.permission.service.IBaseServiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/baseService/v1")
@Resource(value="baseService", desc="服务管理")
@Api(tags="服务权限管理")
public class BaseServiceController {

    public static final Logger log = LoggerFactory.getLogger(BaseServiceController.class);

    @Autowired
    private IBaseServiceService baseServiceService;

    @RequestMapping(value = "/findList", method=RequestMethod.GET)
    @ApiOperation("查询")
    public List<BaseService> findList(@ModelAttribute BaseService baseService){

        return baseServiceService.findList(baseService);
    }

    @RequestMapping(value = "/findPagedList/{pageSize}/{curPage}", method=RequestMethod.GET)
    @ApiOperation("分页查询")
    public Object findPagedList(@ModelAttribute BaseService baseService, @PathParam("")Page page){

        return baseServiceService.findPagedList(baseService,page);
    }

    @RequestMapping(value = "/insertList", method=RequestMethod.POST)
    @ApiOperation("批量新增")
    public Map<String, Object> insertList(@RequestBody List<BaseService> baseServices){

        return baseServiceService.insertList(baseServices);
    }

    @RequestMapping(value = "/deleteList", method=RequestMethod.POST)
    @ApiOperation("批量删除")
    public Map<String, Object> deleteList(@RequestBody List<Long> ids){

        return baseServiceService.deleteList(ids);
    }
}
