package com.liuzemin.server.framework.security.auth.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 企业基本信息（含企业联系方式）
 */
@ApiModel("企业基本信息含(企业联系方式）")
public class TianYanChaBaseInfo implements Serializable {

    private static final Long serialVersionUID = 6143340113086485594L;
    @ApiModelProperty(value = "企业名", notes = "varchar(255)")
    private String name;
    @ApiModelProperty(value = "统一社会信用代码", notes = "varchar(255)")
    private String creditCode;

    @ApiModelProperty(value = "法人", notes = "varchar(120)")
    private String legalPersonName;

    @ApiModelProperty(value = "注册资本", notes = "varchar(50)，null处理为0")
    private String regCapital;

    @ApiModelProperty(value = "注册地址", notes = "varchar(255)")
    private String regLocation;

    @Override
    public String toString() {
        return "TianYanChaBaseInfo{" +
                "name='" + name + '\'' +
                ", creditCode='" + creditCode + '\'' +
                ", legalPersonName='" + legalPersonName + '\'' +
                ", regCapital='" + regCapital + '\'' +
                ", regLocation='" + regLocation + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    public String getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(String regCapital) {
        this.regCapital = regCapital;
    }

    public String getRegLocation() {
        return regLocation;
    }

    public void setRegLocation(String regLocation) {
        this.regLocation = regLocation;
    }

    @ApiModelProperty(value = "省份简称", notes = "varchar(31)")
    private String base;
    @ApiModelProperty(value = "市", notes = "varchar(20)")
    private String city;
    @ApiModelProperty(value = "区", notes = "varchar(20)")
    private String district;
    @ApiModelProperty(value = "曾用名", notes = "varchar(255)")
    private String historyNames;
    @ApiModelProperty(value = "注销日期")
    private String cancelDate;
    @ApiModelProperty(value = "企业状态", notes = "存续,varchar(31)")
    private String regStatus;
    @ApiModelProperty(value = "人员规模", notes = "5000-9999人,varchar(200)")
    private String staffNumRange;
    @ApiModelProperty(value = "曾用名")
    private List<String> historyNameList;
    @ApiModelProperty(value = "股票号", notes = "varchar(20)")
    private String bondNum;
    @ApiModelProperty(value = "行业", notes = "varchar(255)")
    private String industry;
    @ApiModelProperty(value = "股票名", notes = "int(1)")
    private String bondName;
    @ApiModelProperty(value = "吊销日期", notes = "时间戳")
    private Long revokeDate;
    @ApiModelProperty(value = "法人类型，1 人 2 公司", notes = "varchar(20)")
    private Integer type;
    @ApiModelProperty(value = "更新时间", notes = "时间戳")
    private Long updateTimes;
    @ApiModelProperty(value = "吊销原因", notes = "varchar(500)")
    private String revokeReason;
    @ApiModelProperty(value = "注册号", notes = "varchar(31)")
    private String regNumber;
    @ApiModelProperty(value = "英文名", notes = "varchar(255)")
    private String property3;
    @ApiModelProperty(value = "股票曾用名", notes = "varchar(100)")
    private String usedBondName;
    @ApiModelProperty(value = "核准时间", notes = "时间戳")
    private Long approvedTime;
    @ApiModelProperty(value = "经营开始时间", notes = "时间戳")
    private Long fromTime;
    @ApiModelProperty(value = "参保人数", notes = "varchar(50)")
    private Integer socialStaffNum;
    @ApiModelProperty(value = "实收资本币种 人民币 美元 欧元 等", notes = "varchar(10)")
    private String actualCapitalCurrency;
    @ApiModelProperty(value = "简称", notes = "varchar(255)")
    private String alias;
    @ApiModelProperty(value = "企业类型", notes = "varchar(127)")
    private String companyOrgType;
    @ApiModelProperty(value = "企业id", notes = "int(20)")
    private Long id;
    @ApiModelProperty(value = "注销原因", notes = "varchar(500)")
    private String cancelReason;
    @ApiModelProperty(value = "组织机构代码", notes = "varchar(31)")
    private String orgNumber;
    @ApiModelProperty(value = "邮箱", notes = "varchar(50)")
    private String email;
    @ApiModelProperty(value = "经营结束时间", notes = "时间戳")
    private String toTime;
    @ApiModelProperty(value = "实收注册资金", notes = "varchar(50)")
    private String actualCapital;
    @ApiModelProperty(value = "成立日期", notes = "时间戳")
    private Long estiblishTime;
    @ApiModelProperty(value = "登记机关", notes = "varchar(255)")
    private String regInstitute;
    @ApiModelProperty(value = "经营范围", notes = "varchar(4091)")
    private String businessScope;
    @ApiModelProperty(value = "纳税人识别号", notes = "varchar(255)")
    private String taxNumber;
    @ApiModelProperty(value = "注册资本币种 人民币 美元 欧元 等", notes = "varchar(10)")
    private String regCapitalCurrency;
    @ApiModelProperty(value = "企业标签", notes = "varchar(255)")
    private String tags;
    @ApiModelProperty(value = "网址", notes = "text")
    private String websiteList;
    @ApiModelProperty(value = "企业联系方式", notes = "varchar(255)")
    private String phoneNumber;
    @ApiModelProperty(value = "股票类型", notes = "varchar(31)")
    private String bondType;
    @ApiModelProperty(value = "企业评分", notes = "万分制")
    private Integer percentileScore;
    @ApiModelProperty(value = "是否是小微企业 0不是 1是", notes = "int(1)")
    private Integer isMicroEnt;



}
