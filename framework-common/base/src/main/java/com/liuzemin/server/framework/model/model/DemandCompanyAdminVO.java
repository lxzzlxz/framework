package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel("子分公司管理员返回对象")
public class DemandCompanyAdminVO implements Serializable{ 
	 
	private static final long serialVersionUID = 3560938996541853140L;

	private Long id;
	
	@ApiModelProperty("子分公司名")
	private String userName;
	
	@ApiModelProperty("用户Id")
	private Long userId;
	
	@ApiModelProperty("公司名称")
	private String fatherProjectUnitName;
	
	@ApiModelProperty("公司名称id")
	private Long fatherProjectUnit;
	private List<Long> fatherProjectUnits;

	@ApiModelProperty("隶属单位id")
	private Long projectUnit;
	private List<Long> projectUnits;

	@ApiModelProperty("隶属单位")
	private String projectUnitName;
	
	@ApiModelProperty("联系电话")
	private String phone;
	
	@ApiModelProperty("邮箱")
	private String mail;

	@ApiModelProperty("邮箱")
	private String creationDate;

	@ApiModelProperty("含税成交总价")
	private BigDecimal totalAmount;

	@ApiModelProperty(value = "发布开始时间")
	private String startDate;

	@ApiModelProperty(value = "发布结束时间")
	private String endDate;

	@ApiModelProperty("时间格式")
	private String demandFormatStr;
}
