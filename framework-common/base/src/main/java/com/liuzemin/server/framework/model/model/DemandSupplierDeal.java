package com.liuzemin.server.framework.model.model;

import com.liuzemin.server.framework.model.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class DemandSupplierDeal extends BaseModel {

    private List<Long> ids;
    @ApiModelProperty("需求id")
    private Long demandId;

    @ApiModelProperty("供应商id")
    private Long supplierId;

    @Excel(name = "项目名称", width = 20)
    @ApiModelProperty("项目名称")
    private String projectName;

    @Excel(name = "需求编码", width = 20)
    @ApiModelProperty(value = "需求编码")
    private String demandCode;

    @Excel(name = "本次成交金额", width = 20)
    @ApiModelProperty("含税成交总价")
    private String totalAmount;

    @ApiModelProperty("裸税交易金额")
    private String amountWithoutTax;

    @Excel(name = "成交时间", width = 20, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date demandEndTime;

    private String demandEndTimeStr;

    @ApiModelProperty("隶属单位")
    private Long projectUnit;

    @ApiModelProperty("集团公司id")
    private Long companyUnit;

    @ApiModelProperty(value = "发布开始时间")
    private String startDate;

    @ApiModelProperty(value = "发布结束时间")
    private String endDate;

    @ApiModelProperty("时间格式")
    private String dateFormatStr;
}

