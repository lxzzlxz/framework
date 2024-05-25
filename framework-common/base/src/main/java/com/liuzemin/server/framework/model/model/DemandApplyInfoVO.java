package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
public class DemandApplyInfoVO {

    @ApiModelProperty("需求id")
    private Long demandId;

    private List<Long> demandIds;
    @ApiModelProperty("响应id")
    private Long applyId;

    @ApiModelProperty("税率")
    private Float taxRate;

    @ApiModelProperty("单价")
    private String price;

    @ApiModelProperty("裸税成交总价")
    private BigDecimal totalPrice;
    private String totalPriceString;

    @ApiModelProperty("含税成交总价")
    private BigDecimal taxTotalPrice;

    @ApiModelProperty("首次裸税成交总价")
    private BigDecimal firstTotalPrice;

    @ApiModelProperty("首次含税成交总价")
    private BigDecimal firstTaxTotalPrice;
    @ApiModelProperty("供应商id")
    private Long supplierId;
    @ApiModelProperty("供应商id")
    private List<Long> supplierIds;

}
