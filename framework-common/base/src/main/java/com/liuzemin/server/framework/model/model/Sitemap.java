package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
@Data
@EqualsAndHashCode(callSuper=false)
public class Sitemap extends BaseModel {

    private static final long serialVersionUID = 6393290234824254967L;

    @ApiModelProperty(value="父ID 必填")
    private Long parentId;

    @ApiModelProperty(value="名称 必填")
    private String name;

    @ApiModelProperty(value="点击url type=2时必填")
    private String url;

    @ApiModelProperty(value="类型，0:子系统，1：目录，2：页面 必填")
    private Integer type;

    @ApiModelProperty(value="权限")
    private String permission;

    @ApiModelProperty(value="排序 必填")
    private Integer sort;

    @ApiModelProperty(value="图标")
    private String icon;

    @ApiModelProperty(value="应用范围")
    private String scope;

    @ApiModelProperty(value="状态，1：启用，2：锁定 必填")
    private String status;

    private List<Sitemap> children;

}
