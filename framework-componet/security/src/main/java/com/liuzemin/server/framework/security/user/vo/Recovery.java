package com.liuzemin.server.framework.security.user.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.liuzemin.server.framework.model.model.BaseModel;
import com.liuzemin.server.framework.model.model.KeyValueModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Recovery extends BaseModel {
    private static final long serialVersionUID = 6420153258219719553L;

    @ApiModelProperty("设备名称id，查询条件")
    private Long categoryId;

    @ApiModelProperty("设备类型id，查询条件")

    private Long fatherCategoryId;

    @ApiModelProperty("审核人")
    private String reviewer;

    @ApiModelProperty("推荐单位id")
    private Long recommendedUnit;

    @ApiModelProperty("推荐单位id")
    private List<Long> recommendedUnitList;

    @ApiModelProperty("推荐单位名称")
    private String recommendedUnitName;
    @NotBlank(message = "法人授权书不可为空")
    @ApiModelProperty("法人授权书")
    private String legalPersonAuthorization;

    @ApiModelProperty("是否删除，1：否，2：是")
    private Integer deleteFlag;

    @ApiModelProperty("注册资本范围，1：暂无,2：等于0,3：0<x<100,4：100≤x<500,5:500≤x<1000，6:1000≤x")
    private Integer registeredCapitalRange;

    @ApiModelProperty("注册时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String registerDate;

    @ApiModelProperty(value = "一级单位名称")
    private String firstLevelUnitName;

    @ApiModelProperty(value = "一级单位")
    private Long firstLevelUnit;

    @ApiModelProperty("累计缴费金额")
    private String totalAmount;

    @ApiModelProperty("成交总价")
    private  String totalPrice;

    @ApiModelProperty("时间类型（0日1月2年）")
    private String registerDateType;

    @ApiModelProperty(value = "注册开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String startRegisterDate;

    @ApiModelProperty(value = "注册结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endRegisterDate;

    @ApiModelProperty("时间格式")
    private String RegisterFormatStr;

    @ApiModelProperty("时间类型（0日1月2年）")
    private String demandDateType;

    @ApiModelProperty(value = "成交开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String startDemandDate;

    @ApiModelProperty(value = "成交结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endDemandDate;

    @ApiModelProperty("时间格式")
    private String demandFormatStr;
    @ApiModelProperty(value = "公司名称 必填", required = false)
    private String name;
    @ApiModelProperty(value = "法人姓名", required = false)
    private String legalPerson;
    @ApiModelProperty(value = "纳税人类型，1：小规模纳税人，2：一般纳税人")
    private Integer taxpayerType;
    @ApiModelProperty(value = "管理员姓 必填", required = false)
    private String firstName;
    @ApiModelProperty(value = "管理员名 必填", required = false)
    private String secondName;
    @ApiModelProperty(value = "开户银行")
    private String depositBank;
    @ApiModelProperty(value = "基本户账号")
    private String bankAccount;
    @ApiModelProperty(value = "简介 必填", required = false)
    private String description;
    @ApiModelProperty(value = "图标 暂无", required = false)
    private String headImage;
    @ApiModelProperty(value = "统一社会信用代码/营业执照编号 必填", required = false)
    private String businessLicense;
    @ApiModelProperty(value = "营业执照 必填", required = false)
    private String businessLicenseImage;
    @ApiModelProperty(value = "地址 必填", required = false)
    private String address;
    @ApiModelProperty(value = "经度", required = false)
    private String longitude;
    @ApiModelProperty(value = "纬度", required = false)
    private String latitude;
    @ApiModelProperty(value = "省编码 必填", required = false)
    private String provinceCode;
    private String province;
    @ApiModelProperty(value = "市编码 必填", required = false)
    private String cityCode;
    private String city;
    @ApiModelProperty(value = "县区编码 必填", required = false)
    private String countyCode;
    private String county;
    @ApiModelProperty(value = "资信证明")
    private String certImage;
    @ApiModelProperty(value = "企业开户许可证")
    private String recoveryLicense;
    @ApiModelProperty(value = "状态，1：启用，2：锁定，后台管理使用", required = false)
    private Integer status;
    @ApiModelProperty(value = "审批状态：1：待审批，2：审批通过，3：审批不通过")
    private Integer approveStatus;
    @ApiModelProperty(value = "认证通过时间", required = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date approveDate;
    @ApiModelProperty(value = "审核人 必填", required = false)
    private Long approver;
    @ApiModelProperty(value = "审核时间", required = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date firstApproveDate;
    @ApiModelProperty(value = "主营业务", required = false)
    private String mainBusiness;
    @ApiModelProperty(value = "设备规模", required = false)
    private String merchanicalScale;
    @ApiModelProperty(value = "所在省")
    private String businessProvince;
    @ApiModelProperty(value = "注册资本,单位：（万元）")
    private Double registeredCapital;
    @ApiModelProperty(value = "信用评分")
    private Float scoreDev;
    private Float scoreIncr;
    @ApiModelProperty("公司资质，多个资质用“,”分割")
    private List<KeyValueModel> qualificationList;
    private String qualification;
    @ApiModelProperty(value = "管理员电话 必填", required = false)
    private String phone;
    @ApiModelProperty("用户id")
    private Long userId;
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationDate;
    @ApiModelProperty("创建人")
    private Long createdBy;
    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateDate;
    @ApiModelProperty("更新人")
    private Long lastUpdatedBy;
    @ApiModelProperty(value = "管理员身份证号 必填")
    private String contactIdcardNO;
    @ApiModelProperty(value = "管理员身份证合照")
    private String idCardImage;
    @ApiModelProperty(value = "法人身份证正面 必填")
    private String idCardFrontImage;
    @ApiModelProperty(value = "法人身份反面 必填")
    private String idCardSideImage;
    @ApiModelProperty("时间类型（1日，2月，3年）")
    private String dateType;
    @ApiModelProperty("时间格式")
    private String dateFormatStr;
    private Float scoreDevStart;
    private Float scoreDevEnd;
    private List<Long> ids;
    @ApiModelProperty("开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String startDate;
    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endDate;
    private List<Long> recoveryIds;
    private List<String> provinceCodes;
    @ApiModelProperty("是否隐藏回收商部分信息")
    private Boolean hideInfo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date auditDate;
    private String auditReason;
    @ApiModelProperty("是否发送短信：1.是 2.否")
    private Integer sendSms;
}
