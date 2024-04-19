package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 视图对象(无实体表)
 */
@Data
public class MerchanicalCategoryCount {

    @ApiModelProperty(value="供应商ID")
    private Long supplierId;

    @ApiModelProperty(value="设备名称ID，条件查询是，null和-1都是所有")
    private Long categoryId;

    @ApiModelProperty(value="设备名称")
    private String category;

    @ApiModelProperty(value="设备类型ID")
    private Long fatherCategoryId;

    @ApiModelProperty(value="设备类型")
    private String fatherCategory;

    @ApiModelProperty(value="设备数量")
    private Integer count;

}
