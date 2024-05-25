package com.liuzemin.server.framework.security.role.controller;

import com.liuzemin.server.framework.model.annotation.Operation;
import com.liuzemin.server.framework.model.annotation.Resource;
import com.liuzemin.server.framework.security.role.model.RolePermission;
import com.liuzemin.server.framework.security.role.service.IRolePermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rolePermission/v1")
@Resource(value = "rolePermission", desc = "角色权限点")
@Api(tags = "角色权限点管理")
public class RolePermissionController {

    public static final Logger log = LoggerFactory.getLogger(RolePermission.class);

    @Autowired
    private IRolePermissionService rolePermissionService;

    @RequestMapping(value="/findList", method=RequestMethod.GET)
    @Operation(value = "find", desc = "查询")
    @ApiOperation("查询")
    public List<RolePermission> findList(@ModelAttribute RolePermission rolePermission){

        return rolePermissionService.findList(rolePermission);
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    @Operation(value = "save", desc = "更新")
    @ApiOperation("更新")
    public Map<String,Object> save(@RequestParam("roleId")Long roleId, @RequestBody List<RolePermission> rolePermissions){

        return rolePermissionService.save(roleId, rolePermissions);
    }
}
