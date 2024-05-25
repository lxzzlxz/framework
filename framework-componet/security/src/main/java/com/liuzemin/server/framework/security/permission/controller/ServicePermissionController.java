package com.liuzemin.server.framework.security.permission.controller;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.model.annotation.Operation;
import com.liuzemin.server.framework.model.annotation.Resource;
import com.liuzemin.server.framework.model.model.ServicePermission;
import com.liuzemin.server.framework.security.permission.service.IServicePermissionService;
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
@RequestMapping("/servicePermission/v1")
@Resource(value = "servicePermission", desc="服务权限")
@Api(tags="服务权限管理")
public class ServicePermissionController {

    public static final Logger log = LoggerFactory.getLogger(ServicePermissionController.class);

    @Autowired
    private IServicePermissionService servicePermissionService;

    @RequestMapping(value = "/findList", method=RequestMethod.GET)
    @Operation(value = "find", desc = "查询")
    @ApiOperation("查询")
    public List<ServicePermission> findList(@ModelAttribute ServicePermission servicePermission){

        return servicePermissionService.findList(servicePermission);
    }

    @RequestMapping(value = "/findPagedList/{pageSize}/{curPage}", method=RequestMethod.GET)
    @Operation(value = "find", desc = "查询")
    @ApiOperation("分页查询")
    public Object findPagedList(@ModelAttribute ServicePermission servicePermission, @PathParam("")Page page){

        return servicePermissionService.findPagedList(servicePermission,page);
    }

    @RequestMapping(value = "/insertList", method=RequestMethod.POST)
    @Operation(value = "add", desc = "新增")
    @ApiOperation("新增")
    public Map<String, Object> insertList(@RequestBody List<ServicePermission> servicePermissions){

        return servicePermissionService.insertList(servicePermissions);
    }

    @RequestMapping(value = "/deleteList", method=RequestMethod.POST)
    @ApiOperation("删除")
    @Operation(value = "delete", desc = "删除")
    public Map<String, Object> deleteList(@RequestBody List<Long> ids){

        return servicePermissionService.deleteList(ids);
    }

    @RequestMapping(value="/save", method=RequestMethod.POST)
    @Operation(value = "update", desc = "修改")
    @ApiOperation("修改")
    public Map<String, Object> save(@RequestParam String serviceName, @RequestBody List<ServicePermission> servicePermissions){

        return servicePermissionService.save(serviceName, servicePermissions);
    }
}
