package com.liuzemin.server.framework.model.model;

import com.liuzemin.server.framework.model.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EnterpriseExportExcleVO extends BaseModel {

    @Excel(name = "公司名称", width = 20, needMerge = true)
    @ApiModelProperty("企业名称")
    private String name;

    @Excel(name = "管理员电话", width = 20, needMerge = true)
    @ApiModelProperty("管理员电话")
    private String phone;

    @ApiModelProperty("入驻时间")
    private Date registerDate;

    @Excel(name = "入驻时间", width = 20, needMerge = true)
    @ApiModelProperty("入驻时间")
    private String registerDateStr;

    @Excel(name = "总计成交金额（元）", width = 20, needMerge = true)
    @ApiModelProperty("含税成交总价")
    private String totalAmount;

    @Excel(name = "成交需求信息")
    private List<DemandSupplierDeal> demandSupplierDeals;

    @ApiModelProperty("注册人id")
    private Long userId;

}
