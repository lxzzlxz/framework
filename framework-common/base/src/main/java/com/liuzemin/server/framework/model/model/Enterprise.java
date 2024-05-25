package com.liuzemin.server.framework.model.model;

import com.liuzemin.server.framework.model.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * (TplEnterpriseT)实体类
 *
 * @author feng yi
 * @since 2021-01-13 11:46:47
 */
@ApiModel("企业表")
@Data
public class Enterprise extends BaseModel {


    @ApiModelProperty("企业名称")
    @Excel(name = "企业名称", width = 20, needMerge = true)
    private String name;

    @ApiModelProperty("营业执照")
    private String businessLicense;

    @ApiModelProperty("申请资料")
    private String applicationInformation;

    @ApiModelProperty("法人姓名")
    @Excel(name = "法人姓名", width = 20, needMerge = true)
    private String legalPersonName;

    @ApiModelProperty("法人身份证号")
    private String legalPersonIdCardNo;

    @ApiModelProperty("法人身份证正面")
    private String frontOfCorporateIdCard;

    @ApiModelProperty("法人身份证反面")
    private String backOfCorporateIdCard;

    @ApiModelProperty("授权委托书")
    private String certificateOfAuthorization;

    @ApiModelProperty("推荐单位")
    private Long recommendedUnit;

    @ApiModelProperty("推荐单位")
    private List<Long> recommendedUnits;

    @ApiModelProperty("推荐单位名称")
    private String recommendedUnitName;

    @ApiModelProperty("企业类别：1战略需求方，2区域经销商,需求方（经销商发展）,4社会需求方 5招聘方，6劳务分包需求方")
    private Integer enterpriseCategory;

    @ApiModelProperty("企业类型：1央企，2省属国企、市属国企，3其它企业 4招聘方")
    private Integer enterpriseType;

    @ApiModelProperty("统一信用代码")
    private String uniformSocialCreditCode;

    @ApiModelProperty("注册资本")
    private BigDecimal registeredCapital;


    @ApiModelProperty("是否删除，1：否，2：是")
    private Integer deleteFlag;

    @ApiModelProperty("注册人id")
    private Long userId;

    @ApiModelProperty("注册人ids")
    private List<Long> userIds;

    @ApiModelProperty("审批人id")
    private Long approvalUserId;

    @ApiModelProperty("审批人姓名")
    private String approvalUserName;

    @ApiModelProperty("注册人姓名")
    private String userName;

    @ApiModelProperty("电子邮箱")
    private String mail;

    @ApiModelProperty("注册人身份证号")
    private String idCardNo;

    @ApiModelProperty("管理员电话")
    @Excel(name = "管理员电话", width = 20, needMerge = true)
    private String phone;

    @ApiModelProperty("审批状态（0待审核，1审核通过，2审核不通过,3运营部通过，4，市场部通过）")
    private Integer approvalStatus;
    @Excel(name = "审批状态", width = 20, needMerge = true,dateFormat = "yyyy-MM-dd HH:mm")
    private String approvalStatusStr;

    @ApiModelProperty("审批时间")
    @Excel(name = "审核通过时间", width = 20, needMerge = true,dateFormat = "yyyy-MM-dd HH:mm")
    private Date approvalDate;

    @ApiModelProperty("审批意见")
    private String auditOpinion;

    @ApiModelProperty("负责人审批状态（1负责人审核通过，2负责人审核不通过）")
    private Integer responsibleApprovalStatus;

    @ApiModelProperty("负责人审批时间")
    private Date responsibleApprovalDate;

    @ApiModelProperty("书记审批状态（1书记审核通过，2书记审核不通过）")
    private Integer secretaryApprovalStatus;

    @ApiModelProperty("书记审批时间")
    private Date secretaryApprovalDate;

    @ApiModelProperty("ids")
    private List<Long> ids;

    @ApiModelProperty("注册人电话")
    private String userPhone;

    @ApiModelProperty("附件")
    private String annex;

    @ApiModelProperty("子分公司名单")
    List<Subsidiary> subsidiaryList;

    @ApiModelProperty("入驻时间")
    private Date registerDate;

    private String registerDateStr;

    @ApiModelProperty("信用分数")
    private Float scoreDev;
    private Float scoreDevStart;
    private Float scoreDevEnd;
    private Float scoreIncr;

    @ApiModelProperty("入驻开始时间")
    private String registerStartDate;

    @ApiModelProperty("入驻结束时间")
    private String registerEndDate;

    @ApiModelProperty("入驻时间格式")
    private String registerDateFormatStr;

    @ApiModelProperty("成立日期")
    private Date dateOfEstablishment;

    @ApiModelProperty("人力资源许可证")
    private String humanResourcesLicense;

    @ApiModelProperty("经营场所")
    private String placeOfBusiness;

    @ApiModelProperty(value = "省编码")
    private String provinceCode;
    @ApiModelProperty(value = "省编码")
    private String provinceCodeName;

    @ApiModelProperty(value = "市编码")
    private String cityCodeName;
    private String cityCode;

    @ApiModelProperty(value = "区县编码")
    private String countyCode;
    private String countyCodeName;

    @ApiModelProperty("含税成交总价")
    private String totalAmount;

    @ApiModelProperty("时间类型(0日1月2年)")
    private String dateType;

    @ApiModelProperty("开始时间")
    private String startDate;

    @ApiModelProperty("结束时间")
    private String endDate;

    @ApiModelProperty("时间格式")
    private String dateFormatStr;

    @Excel(name = "提交审核时间", width = 20, needMerge = true,dateFormat = "yyyy-MM-dd HH:mm")
    private Date creationDate;
}

