package com.liuzemin.server.framework.security.permission.controller;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.model.annotation.Operation;
import com.liuzemin.server.framework.model.annotation.Resource;
import com.liuzemin.server.framework.model.helper.RHelper;
import com.liuzemin.server.framework.model.model.R;
import com.liuzemin.server.framework.model.model.UserRoleProgram;
import com.liuzemin.server.framework.security.permission.service.IUserRoleProgramService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/userRoleProgram/v1")
@Resource(value = "userRoleProgram", desc = "用户权限")
@Api(tags = "用户权限管理")
public class UserRoleProgramController {

    public static final Logger log = LoggerFactory.getLogger(UserRoleProgramController.class);

    @Autowired
    private IUserRoleProgramService userRoleProgramService;

    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    @Operation(value = "find", desc = "查询")
    @ApiOperation("查询")
    public R<List<UserRoleProgram>> findList(UserRoleProgram userRoleProgram) {
        return RHelper.getSuccessR(userRoleProgramService.findList(userRoleProgram));
    }


    @RequestMapping(value = "/findByUserIds", method = RequestMethod.POST)
    @ApiOperation("查询")
    public List<UserRoleProgram> findByUserIds(UserRoleProgram userRoleProgram) {

        return userRoleProgramService.findList(userRoleProgram);
    }

    @RequestMapping(value = "/findPagedList/{pageSize}/{curPage}", method = RequestMethod.GET)
    @Operation(value = "find", desc = "查询")
    @ApiOperation("分页查询")
    public PagedResult<UserRoleProgram> findPagedList(@ModelAttribute UserRoleProgram userRoleProgram, @PathParam("") Page page) {

        return userRoleProgramService.findPagedList(userRoleProgram, page);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @Operation(value = "save", desc = "更新")
    @ApiOperation("更新")
    public Map<String, Object> save(@RequestParam("") Long userId, @RequestBody List<UserRoleProgram> userRolePrograms) {

        return userRoleProgramService.save(userId, userRolePrograms);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @Operation(value = "save", desc = "更新")
    @ApiOperation("更新")
    public Map<String, Object> delete(@RequestBody UserRoleProgram userRoleProgram) {

        return userRoleProgramService.delete(userRoleProgram);
    }

    @RequestMapping(value = "/deleteByService", method = RequestMethod.POST)
    @ApiOperation("更新")
    public Map<String, Object> deleteByService(@RequestBody UserRoleProgram userRoleProgram) {

        return userRoleProgramService.delete(userRoleProgram);
    }

    @RequestMapping(value = "/deleteByUserId", method = RequestMethod.POST)
    @ApiOperation("更新")
    public R<String> deleteByUserId(@RequestBody UserRoleProgram userRoleProgram) {

        return userRoleProgramService.deleteByUserId(userRoleProgram);
    }

    @RequestMapping(value = "/updateAndInsert", method = RequestMethod.POST)
    @ApiOperation("新增用户权限，规则是存在更新，不存在新增")
    public Map<String, Object> updateAndInsert(@RequestBody UserRoleProgram userRoleProgram) {

        return userRoleProgramService.updateAndInsert(userRoleProgram);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ApiOperation("通过角色名称查询用户id")
    @Operation(value = "find", desc = "查询")
    public R<List<UserRoleProgram>> queryUserByRoleName(@RequestParam("roleName") String roleName) {
        //String roleName="局公司管理员";
        return RHelper.getSuccessR(userRoleProgramService.queryUserByRoleName(roleName));
    }

    @RequestMapping(value = "/dimension_value", method = RequestMethod.GET)
    @ApiOperation("通过用户id查询需求用户的数据范围")
    @Operation(value = "find", desc = "查询")
    public R<List<String>> queryDimensionValueByUserId(@RequestParam Long userId) {

        return RHelper.getSuccessR(userRoleProgramService.queryDimensionValueByUserId(userId));
    }

    @GetMapping(value = "/getAllDemandUserIdCompanyMap")
    @Operation(value = "getAllDemandUserIdCompanyMap", desc = "获取战略需求方用户Id和公司名称对应的Map")
    public R<Map<Long, String>> getAllDemandUserIdCompanyMap() {

        Map<Long, String> userIdCompinyMap = userRoleProgramService.getAllDemandUserIdCompanyMap();
        return RHelper.getSuccessR(userIdCompinyMap);
    }


    @Async
    @GetMapping(value = "/refresh")
    @Operation(value = "refresh", desc = "刷新用户最新权限")
    public Future<String> refresh(Long roleId) {
        userRoleProgramService.refresh(roleId);
        return new AsyncResult<>("");
    }

    @RequestMapping(value = "/deletRroleProgram", method = RequestMethod.POST)
    @ApiOperation("查询")
    public R<String> deletRroleProgram(@RequestBody UserRoleProgram userRoleProgram) {

        return userRoleProgramService.deletRroleProgram(userRoleProgram);
    }

}
