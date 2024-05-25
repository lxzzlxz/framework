package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 招聘费用配置表(RecruitmentFeeConfigT)实体类
 *
 * @author feng yi
 * @since 2023-06-27 11:35:54
 */
@ApiModel("招聘费用配置表")
public class RecruitmentFeeConfig extends BaseModel {
    private static final long serialVersionUID = 393978115476527767L;


    @ApiModelProperty("用户类型")
    private Integer userType;

    @ApiModelProperty("费用类型（1招聘2求职）")
    private Integer feeType;

    @ApiModelProperty("是否免费（1是，2否）")
    private Integer free;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("描述")
    private String description;


    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getFeeType() {
        return feeType;
    }

    public void setFeeType(Integer feeType) {
        this.feeType = feeType;
    }

    public Integer getFree() {
        return free;
    }

    public void setFree(Integer free) {
        this.free = free;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
