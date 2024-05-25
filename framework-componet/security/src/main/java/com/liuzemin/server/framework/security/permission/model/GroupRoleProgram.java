package com.liuzemin.server.framework.security.permission.model;

import com.liuzemin.server.framework.model.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;

public class GroupRoleProgram extends BaseModel {

    private static final long serialVersionUID = 5445895509714179055L;

    @ApiModelProperty(value="群组ID 必填", required = false)
    private Long groupId;

    @ApiModelProperty(value="角色ID 必填", required = false)
    private Long roleId;

    @ApiModelProperty(value="数据范围ID 必填", required = false)
    private Long programId;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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
}
