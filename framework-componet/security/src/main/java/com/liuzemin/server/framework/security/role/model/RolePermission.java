package com.liuzemin.server.framework.security.role.model;

import com.liuzemin.server.framework.model.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;

public class RolePermission extends BaseModel {

    private static final long serialVersionUID = 2649667606868267636L;

    @ApiModelProperty(value="角色名称ID 必填", required = false)
    private Long roleId;

    @ApiModelProperty(value="权限点ID 必填", required = false)
    private Long permissionId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }
}
