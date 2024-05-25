package com.liuzemin.server.framework.security.role.model;

import com.liuzemin.server.framework.model.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
@Data
public class Role extends BaseModel {


    private static final long serialVersionUID = 3438308627261542309L;

    @ApiModelProperty(value="角色名称 必填", required = false)
    private String roleName;

    @ApiModelProperty(value="角色状态，1：启用，2：锁定 必填", required = false)
    private Integer status;

    private String scope;

    private List<Long> roleIds;
}
