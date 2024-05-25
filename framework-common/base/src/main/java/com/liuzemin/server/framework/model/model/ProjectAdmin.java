package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProjectAdmin extends BaseModel {


    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目id")
    private Long projectId;

    @ApiModelProperty(value = "管理員id")
    private Long userId;
    @ApiModelProperty(value = "管理員id集合")
    private List<Long> userIds;
    @ApiModelProperty(value = "项目部id集合")
    private List<Long> projectIds;

    @ApiModelProperty(value = "备注")
    private String remarks;


}
