package com.liuzemin.server.framework.security.permission.controller;

import com.liuzemin.server.framework.model.annotation.Operation;
import com.liuzemin.server.framework.model.annotation.Resource;
import com.liuzemin.server.framework.model.model.Permission;
import com.liuzemin.server.framework.security.permission.service.IPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/permission/v1")
@Resource(value="permission", desc="权限管理")
@Api(tags="权限点管理")
public class PermissionController {

    public static final Logger log = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    @Operation(value = "find", desc = "查询")
    @ApiOperation("查询列表")
    public List<Permission> findList(@ModelAttribute Permission permission){

        return permissionService.findList(permission);
    }

    @RequestMapping(value = "/findTree", method = RequestMethod.GET)
    @Operation(value = "find", desc = "查询")
    @ApiOperation("查询权限点数结构")
    public List<Map<String, Object>> findTree(@ModelAttribute Permission permission){

        return permissionService.findTree(permission);
    }

    @RequestMapping(value = "/insertList", method = RequestMethod.POST)
    @Operation(value = "add", desc = "新增")
    @ApiOperation("批量新增")
    public Map<String, Object> insertList(@RequestBody List<Permission> permissions){

        return permissionService.insertList(permissions);
    }

    @RequestMapping(value = "/deleteList", method = RequestMethod.POST)
    @Operation(value = "delete", desc = "删除")
    @ApiOperation("批量删除")
    public Map<String, Object> deleteList(@RequestBody List<Long> ids){

        return permissionService.deleteList(ids);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation("更新")
    public Map<String,Object> save(@RequestParam("scope")String scope, @RequestBody List<Permission> permissions){

        return permissionService.save(scope, permissions);
    }
}
