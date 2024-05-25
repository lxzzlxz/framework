package com.liuzemin.server.framework.model.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * (CrecOrderDetailT)实体类
 *
 * @author feng yi
 * @since 2021-04-07 10:15:04
 */
@ApiModel("订单详情")
public class OrderDetail extends BaseModel {
    /**
     * 订单id
     */
    @ApiModelProperty("订单id")
    private Long orderId;

    @ApiModelProperty("服务类型（1，人才招聘，2，设备租赁会员费，3，设备租赁基础会员费）")
    private Integer serviceType;

    @ApiModelProperty("类别（1,区域，2设备，3基础会员费，4,商品）")
    private Integer category;
    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("编码")
    private String code;
    @ApiModelProperty("订单集合")
    private List<Long> orderIds;
    /**
     * 单价
     */
    @ApiModelProperty("单价")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal unitPrice;
    @ApiModelProperty("购买数量")
    private  Integer purchaseQuantity;
    @ApiModelProperty("购买总可用总数")
    private Integer availableTimes;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public List<Long> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<Long> orderIds) {
        this.orderIds = orderIds;
    }

    public OrderDetail() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(Integer purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public Integer getAvailableTimes() {
        return availableTimes;
    }

    public void setAvailableTimes(Integer availableTimes) {
        this.availableTimes = availableTimes;
    }

    public OrderDetail(Long orderId, Integer serviceType, Integer category, String name, String code, BigDecimal unitPrice) {
        this.orderId = orderId;
        this.serviceType = serviceType;
        this.category = category;
        this.name = name;
        this.code = code;
        this.unitPrice = unitPrice;
    }
}
