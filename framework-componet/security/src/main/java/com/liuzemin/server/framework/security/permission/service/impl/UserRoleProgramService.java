package com.liuzemin.server.framework.security.permission.service.impl;

import com.liuzemin.server.framework.datasource.page.Page;
import com.liuzemin.server.framework.datasource.page.PagedResult;
import com.liuzemin.server.framework.model.constant.ResponseCode;
import com.liuzemin.server.framework.model.helper.RHelper;
import com.liuzemin.server.framework.model.helper.ResultMapHelper;
import com.liuzemin.server.framework.model.model.R;
import com.liuzemin.server.framework.model.model.UserRoleProgram;
import com.liuzemin.server.framework.security.feign.DemandAdminFeignClient;
import com.liuzemin.server.framework.security.permission.dao.IUserRoleProgramDao;
import com.liuzemin.server.framework.security.permission.service.IUserRoleProgramService;
import com.liuzemin.server.framework.security.program.dao.IProgramDao;
import com.liuzemin.server.framework.security.program.model.Program;
import com.liuzemin.server.framework.security.role.dao.IRoleDao;
import com.liuzemin.server.framework.security.role.model.Role;
import com.liuzemin.server.framework.security.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserRoleProgramService implements IUserRoleProgramService {

    public static final Logger log = LoggerFactory.getLogger(UserRoleProgramService.class);

    @Autowired
    private IUserRoleProgramDao userRoleProgramDao;
    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private IProgramDao programDao;

    @Autowired
    private IUserService userService;
    @Autowired
    private DemandAdminFeignClient demandAdminFeignClient;

    @Override
    public List<UserRoleProgram> findList(UserRoleProgram userRoleProgram) {

        return userRoleProgramDao.findList(userRoleProgram);
    }

    @Override
    public PagedResult<UserRoleProgram> findPagedList(UserRoleProgram userRoleProgram, Page page) {

        return userRoleProgramDao.findPagedList(userRoleProgram, page);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Map<String, Object> insertList(List<UserRoleProgram> userRolePrograms) {
        if (CollectionUtils.isEmpty(userRolePrograms)) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        for (UserRoleProgram userRoleProgram : userRolePrograms) {
            userRoleProgram.setCreateInfo();
        }
        int count = userRoleProgramDao.insertList(userRolePrograms);
        return ResultMapHelper.getResult(count);
    }

    @Override
    public Map<String, Object> delete(UserRoleProgram userRoleProgram) {
        if (null == userRoleProgram || (null == userRoleProgram.getId() && (null == userRoleProgram.getUserId() || null == userRoleProgram.getRoleId() || null == userRoleProgram.getProgramId()))) {
            return ResultMapHelper.getParameterErrorMap();
        }
        int count = userRoleProgramDao.delete(userRoleProgram);
        return ResultMapHelper.getResult(count);
    }

    @Override
    public Map<String, Object> deleteList(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        userRoleProgramDao.deleteList(ids);
        return ResultMapHelper.getSuccessMap();
    }

    @Override
    public Map<String, Object> save(Long userId, List<UserRoleProgram> userRolePrograms) {
        //1.校验
        if (null == userId) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, ResponseCode.PARAMETER_ERROR);
        }
        //2,查询原始数据
        UserRoleProgram queryParam = new UserRoleProgram();
        queryParam.setUserId(userId);
        List<UserRoleProgram> originList = userRoleProgramDao.findList(queryParam);

        //3.
        List<UserRoleProgram> toInsert = new ArrayList<>();
        List<Long> toDelete = new ArrayList<>();

        List<Long> originIds = new ArrayList<>();
        List<String> originUserIds = new ArrayList<>();
        List<String> newUserIds = new ArrayList<>();

        //如果原始数据是空，新增新数据，返回；
        if (CollectionUtils.isEmpty(originList)) {
            userRoleProgramDao.insertList(userRolePrograms);
            return ResultMapHelper.getSuccessMap();
        } else {
            for (UserRoleProgram userRoleProgram : originList) {
                originUserIds.add(userRoleProgram.getRoleId() + "$" + userRoleProgram.getProgramId());
                originIds.add(userRoleProgram.getId());
            }
        }

        //如果新数据是空，删除原始数据，返回；
        if (CollectionUtils.isEmpty(userRolePrograms)) {
            userRoleProgramDao.deleteList(originIds);
            return ResultMapHelper.getSuccessMap();
        } else {
            for (UserRoleProgram userRoleProgram : userRolePrograms) {
                String key = userRoleProgram.getRoleId() + "$" + userRoleProgram.getProgramId();
                newUserIds.add(key);
                if (originUserIds.contains(key)) {
                    continue;
                }
                toInsert.add(userRoleProgram);
            }
        }

        for (UserRoleProgram userRoleProgram : originList) {
            String key = userRoleProgram.getRoleId() + "$" + userRoleProgram.getProgramId();
            if (newUserIds.contains(key)) {
                continue;
            }
            toDelete.add(userRoleProgram.getId());
        }

        //保存数据
        if (!CollectionUtils.isEmpty(toInsert)) {
            userRoleProgramDao.insertList(toInsert);
        }

        if (!CollectionUtils.isEmpty(toDelete)) {
            userRoleProgramDao.deleteList(toDelete);
        }

        return ResultMapHelper.getSuccessMap();
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Map<String, Object> updateAndInsert(UserRoleProgram userRoleProgram) {

        if (null != userRoleProgram && !StringUtils.isEmpty(userRoleProgram.getRoleName())) {
            Role roleQuery = new Role();
            roleQuery.setRoleName(userRoleProgram.getRoleName());
            List<Role> list = roleDao.findList(roleQuery);
            userRoleProgram.setRoleId(list.get(0).getId());
        }
        if (null == userRoleProgram || null == userRoleProgram.getProgramId()
                || null == userRoleProgram.getRoleId() || null == userRoleProgram.getUserId()) {
            return ResultMapHelper.getResultMap(ResponseCode.CUSTOM_CODE, "参数错误");
        }
        List<UserRoleProgram> originList = findList(userRoleProgram);

        if (CollectionUtils.isEmpty(originList)) {
            originList.add(userRoleProgram);
            if (null == userRoleProgram.getStatus() || 1 == userRoleProgram.getStatus()) {
                Map<String, Object> insertResult = insertList(originList);
                userService.loginOutUserByUpdate(userRoleProgram.getUserId());
                return insertResult;
            }
        } else {
            if (null != userRoleProgram.getStatus() && 2 == userRoleProgram.getStatus()) {
                List<Long> ids = new ArrayList<>();
                originList.forEach(userRole -> {
                    if (userRoleProgram.getRoleId().compareTo(userRole.getRoleId()) == 0 && userRoleProgram.getUserId().compareTo(userRole.getUserId()) == 0) {
                        ids.add(userRole.getId());
                    }
                });
                Map<String, Object> deleteResult = deleteList(ids);
                userService.loginOutUserByUpdate(userRoleProgram.getUserId());
                return deleteResult;
            }
        }
        return ResultMapHelper.getSuccessMap();
    }

    @Override
    public List<UserRoleProgram> queryUserByRoleName(String roleName) {

        return userRoleProgramDao.queryUserByRoleName(roleName);
    }

    @Override
    public List<String> queryDimensionValueByUserId(Long userId) {

        return userRoleProgramDao.queryDimensionValueByUserId(userId);
    }

    @Override
    public Map<Long, String> getAllDemandUserIdCompanyMap() {
        //
        R<List<Long>> adminUserIdsResult = demandAdminFeignClient.getAllAdminUserIds();
        List<UserRoleProgram> userRolePrograms = userRoleProgramDao.findList(null);
        List<Program> programs = programDao.findList(null);
        Map<Long, String> programIdNameMap = programs.stream().collect(Collectors.toMap(Program::getId, Program::getProgramName, (oldvalue, newvalue) -> newvalue));
        Map<Long, Long> userIdProgramIdMap = new HashMap<>(16);
        for (UserRoleProgram userRoleProgram : userRolePrograms) {
            Long userId = userRoleProgram.getUserId();
            Long programId = userRoleProgram.getProgramId();
            if (userId != null && programId != null) {
                userIdProgramIdMap.put(userId, programId);
            }
        }
        Map<Long, String> userIdProgramName = new HashMap<>(16);
        if (Boolean.TRUE.equals(RHelper.isSuccessR(adminUserIdsResult))) {
            List<Long> adminUserIds = adminUserIdsResult.getDatas();
            for (Long adminUserId : adminUserIds) {
                Long programId = userIdProgramIdMap.get(adminUserId);
                String programName = programIdNameMap.get(programId);
                userIdProgramName.put(programId, programName);
            }
        }
        return userIdProgramName;
    }

    @Override
    public R<String> deleteByUserId(UserRoleProgram userRoleProgram) {
        int count = userRoleProgramDao.delete(userRoleProgram);
        return RHelper.getResult(count);
    }

    @Override
    public void refresh(Long roleId) {
        UserRoleProgram userRoleProgram = new UserRoleProgram();
        userRoleProgram.setRoleId(roleId);
        List<UserRoleProgram> list = userRoleProgramDao.findList(userRoleProgram);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        List<Long> userIds = list.stream().map(UserRoleProgram::getUserId).collect(Collectors.toList());
        userService.loginOutUserIds(userIds);

    }

    @Override
    public R<String> deletRroleProgram(UserRoleProgram userRoleProgram) {
        List<UserRoleProgram> list = userRoleProgramDao.findList(userRoleProgram);
        if (CollectionUtils.isEmpty(list)) {
            return RHelper.getErrorR("权限信息不存在");
        }
        Long userId = userRoleProgram.getUserId();
        if (null == userId) {
            return RHelper.getErrorR("用户id不能为空");
        }
        UserRoleProgram u = new UserRoleProgram();
        u.setUserId(userId);
        u.setRoleId(2L);
        Integer delete = userRoleProgramDao.delete(u);
        return RHelper.getResult(delete);

    }

}
