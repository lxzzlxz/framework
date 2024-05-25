package com.liuzemin.server.framework.security.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("用户统计返回对象")
public class UserTypeStatisticsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("时间")
    private String dateTime;
    @ApiModelProperty("注册用户总数")
    private String regUserNumber;
    @ApiModelProperty("未认证供应商")
    private String notAuthSupplier;
    @ApiModelProperty("供应商")
    private String supplier;
    @ApiModelProperty("战略需求方")
    private String strategicDemander;

    @ApiModelProperty("未认证社会需求方")
    private String notAuthSocialDemander;
    @ApiModelProperty("社会需求方")
    private String socialDemander;

    @ApiModelProperty("数量")
    private Integer number;
    @ApiModelProperty("用户类型")
    private Integer userType;
    @ApiModelProperty("时间格式")
    private String dateFormatStr;
    @ApiModelProperty("开始时间")
    private String startDate;
    @ApiModelProperty("结束时间")
    private String endDate;
    @ApiModelProperty("时间类型")
    private String dateType;

    @ApiModelProperty("已提交认证战略需求方")
    private String strategicDemanderNum;

    @ApiModelProperty("未认证社会需求方")
    private String notAuthsocialDemanderNum;

    @ApiModelProperty("已提交社会需求方")
    private String submitSocialDemanderNum;

    @ApiModelProperty("未提交战略需求方")
    private String notSubmitStrategicDemander;

    @ApiModelProperty("已提交二级经销商")
    private String submitedSecondaryDealer;

    @ApiModelProperty("区域经销商")
    private String regionalDistributor;

    @ApiModelProperty("股份公司")
    private String stockCompany;
    @ApiModelProperty("平台管理员")
    private String platformAdmin;






}
