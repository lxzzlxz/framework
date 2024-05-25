package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * (TplDiscountT)实体类
 *
 * @author feng yi
 * @since 2021-04-07 10:43:22
 */
@ApiModel("折扣")
public class Discount extends BaseModel {
    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    private Date startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    private Date endTime;
    /**
     * 折扣
     */
    @ApiModelProperty("折扣")
    private BigDecimal discount;
    /**
     * 类型（0,当前，1记录）
     */
    @ApiModelProperty("类型（0,当前，1记录）")
    private Integer type;

    /**
     * 创建时间
     */

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", discount=" + discount +
                ", type=" + type +
                '}';
    }
}
