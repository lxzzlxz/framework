package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class UserRoleProgram extends BaseModel {

    private static final long serialVersionUID = -7691157659279568839L;

    @ApiModelProperty(value = "用户ID 必填", required = false)
    private Long userId;

    private List<Long> userIds;

    @ApiModelProperty(value = "角色ID 必填", required = false)
    private Long roleId;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "数据范围ID 必填", required = false)
    private Long programId;


    @ApiModelProperty(value = "状态，1：启用，2：锁定 必填", required = false)
    private Integer status;

    //for query
    @ApiModelProperty(value = "维度值")
    private String dimensionValue;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDimensionValue() {
        return dimensionValue;
    }

    public void setDimensionValue(String dimensionValue) {
        this.dimensionValue = dimensionValue;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}

