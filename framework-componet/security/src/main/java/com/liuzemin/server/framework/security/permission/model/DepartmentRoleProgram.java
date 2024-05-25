package com.liuzemin.server.framework.security.permission.model;

import com.liuzemin.server.framework.model.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;

public class DepartmentRoleProgram extends BaseModel {

    private static final long serialVersionUID = -8627262899783381731L;

    @ApiModelProperty(value="部门ID 必填", required = false)
    private Long departmentId;

    @ApiModelProperty(value="角色ID 必填", required = false)
    private Long roleId;

    @ApiModelProperty(value="数据范围ID 必填", required = false)
    private Long programId;

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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
