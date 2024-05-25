package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SupplierVO extends Supplier {

    private static final long serialVersionUID = 6420153258219719553L;

    @ApiModelProperty("设备名称id，查询条件")
    private Long categoryId;

    @ApiModelProperty("设备类型id，查询条件")
    private Long fatherCategoryId;

    @ApiModelProperty("审核人")
    private String reviewer;

    @ApiModelProperty("初审人")
    private String firstReviewer;

    @ApiModelProperty("推荐单位名称")
    private String recommendedUnitName;

    @ApiModelProperty("法人授权书")
    private String legalPersonAuthorization;

    private Integer deleteFlag;

    private Integer isVIP;
    //for query
    @ApiModelProperty("注册资本范围，1：暂无,2：等于0,3：0<x<100,4：100≤x<500,5:500≤x<1000，6:1000≤x")
    private Integer registeredCapitalRange;


}
