package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 最终评价
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SupplierEval extends BaseModel {

    private static final long serialVersionUID = -2708361336325164889L;

    @ApiModelProperty(value = "需求id")
    private Long demandId;

    @ApiModelProperty(value = "供应商id")
    private Long supplierId;

    @ApiModelProperty(value = "供应商id")
    private List<Long> supplierIds;

    @ApiModelProperty(value = "响应id")
    private Long applyId;

    @ApiModelProperty(value = "交易id")
    private Long orderId;

    @ApiModelProperty(value = "交易id")
    private List<Long> orderIds;

    @ApiModelProperty(value = "项目名称")
    private String projectName;

    @ApiModelProperty(value = "项目单位")
    private String projectUnit;

    @ApiModelProperty(value = "评论详情")
    private String comment;

    @ApiModelProperty(value = "评分")
    private Integer score;

    @ApiModelProperty(value = "类型，1：供应商，2：设备")
    private Integer category;

    @ApiModelProperty("供应商名称")
    private String supplierName;

    @ApiModelProperty("信用等级")
    private Float scoreDev;

    @ApiModelProperty("需求id")
    private List<Long> demandIds;
}
