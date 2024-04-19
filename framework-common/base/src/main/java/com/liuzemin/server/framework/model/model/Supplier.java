package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Supplier extends BaseModel {

    private static final long serialVersionUID = 2638037595203951703L;

    @ApiModelProperty(value = "公司名称 必填", required = false)
    private String name;

    @ApiModelProperty(value = "法人姓名", required = false)
    private String legalPerson;

    @ApiModelProperty(value = "经营范围是否包含租赁业务，1：是，2：否")
    private Integer isRent;

    @ApiModelProperty(value = "纳税人类型，1：小规模纳税人，2：一般纳税人")
    private Integer taxpayerType;

    @ApiModelProperty(value = "开户银行")
    private String depositBank;

    @ApiModelProperty(value = "基本户账号")
    private String bankAccount;

    @ApiModelProperty(value = "简介 必填", required = false)
    private String description;

    @ApiModelProperty(value = "图标 暂无", required = false)
    private String headImage;

    @ApiModelProperty(value = "统一社会信用代码 必填", required = false)
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
    private String supplierLicense;

    @ApiModelProperty(value = "状态，1：启用，2：锁定，后台管理使用", required = false)
    private Integer status;

    @ApiModelProperty(value = "认证通过时间", required = false)
    private Date approveDate;

    @ApiModelProperty(value = "审批状态：1：待审批，2：审批通过，3：审批不通过 ", required = false)
    private Integer approveStatus;

    @ApiModelProperty(value = "复审状态：1：待审批，2：审批通过，3：审批不通过  4：初审通过", required = false)
    private Integer reviewStatus;

    @ApiModelProperty(value = "复审人 必填", required = false)
    private Long reviewApprover;

    @ApiModelProperty("复审Id")
    private Long reviewId;

    @ApiModelProperty(value = "是否驳回：1.是 2.否")
    private Integer turnDown;

    @ApiModelProperty(value = "复审通过时间", required = false)
    private Date reviewDate;

    @ApiModelProperty(value = "上次通过时间")
    private Date lastApproverDate;

    @ApiModelProperty(value = "复审初审人")
    private Long reviewFirstApprover;

    @ApiModelProperty(value = "复审初审时间")
    private Date reviewFirstDate;

    @ApiModelProperty(value = "复审意见")
    private String reviewReason;

    @ApiModelProperty(value = "供应商id")
    private Long supplierId;

    @ApiModelProperty(value = "审核人 必填", required = false)
    private Long approver;

    @ApiModelProperty(value = "初审人", required = false)
    private Long firstApprover;

    @ApiModelProperty(value = "初审时间", required = false)
    private Date firstApproveDate;

    @ApiModelProperty(value = "主营业务", required = false)
    private String mainBusiness;

    @ApiModelProperty(value = "设备规模", required = false)
    private String merchanicalScale;

    @ApiModelProperty(value = "所在省")
    private String businessProvince;

    @ApiModelProperty(value = "管理员姓 必填", required = false)
    private String firstName;

    @ApiModelProperty(value = "管理员名 必填", required = false)
    private String secondName;

    @ApiModelProperty(value = "管理员身份证号 必填")
    private String contactIdcardNO;

    @ApiModelProperty(value = "管理员身份证合照")
    private String idCardImage;

    @ApiModelProperty(value = "法人身份证正面 必填")
    private String idCardFrontImage;

    @ApiModelProperty(value = "法人身份反面 必填")
    private String idCardSideImage;

    @ApiModelProperty(value = "管理员电话 必填", required = false)
    private String phone;

    @ApiModelProperty(value = "用户ID", required = false)
    private Long userId;

    @ApiModelProperty(value = "用户ID", required = false)
    private List<Long> userIds;

    @ApiModelProperty(value = "注册资本,单位：（万元）")
    private BigDecimal registeredCapital;

    @ApiModelProperty(value = "信用评分")
    private Float scoreDev;

    @ApiModelProperty(value = "推荐单位")
    private Long recommendedUnit;

    private List<Long> recommendedUnitList;

    @ApiModelProperty("是否上传设备，0：否，1：是")
    private Integer isUplodDevices;

    private Float scoreDevStart;

    private Float scoreDevEnd;

    private Float scoreIncr;
    private Integer deleteFlag;
    private List<String> provinceCodes;

    private List<Long> fatherCategoryIds;

    public List<Long> getSupplierIds() {
        return supplierIds;
    }

    public void setSupplierIds(List<Long> supplierIds) {
        this.supplierIds = supplierIds;
    }

    private List<Long> supplierIds;

    private List<Long> categoryIds;

    @ApiModelProperty("供应商设备详情集合")
    private List<Merchanical> merchanicalList;

    @ApiModelProperty("供应商设备数量集合 eg：履带式挖掘机-->2")
    private List<MerchanicalCategoryCount> categoryCountList;

    private List<MerchanicalCategoryCount> fatherCategoryCountList;

    private List<Long> ids;

    private Date auditDate;

    private String auditReason;

    @ApiModelProperty("是否隐藏供应商部分信息")
    private Boolean isHideInfo;

    @ApiModelProperty("审核时间标记（1、最近3天，2、最近一周，3、最近一月）")
    private Integer approveDateFlag;

    @ApiModelProperty("时间类型（1月2年）")
    private String dateType;

    @ApiModelProperty("开始时间")
    private String startDate;

    @ApiModelProperty("时间名称")
    private String dateTime;

    @ApiModelProperty("结束时间")
    private String endDate;

    @ApiModelProperty("时间格式")
    private String dateFormatStr;

    @ApiModelProperty("数量")
    private Integer num;


    @ApiModelProperty("公司资质，多个资质用“,”分割")
    private List<KeyValueModel> qualificationList;

    @ApiModelProperty("响应需求最终成交价")
    private String finalPrice;

    private String qualification;

    @ApiModelProperty("是否是物流商 1.是 2.否")
    private Integer logisticsType;

    @ApiModelProperty("物流准入时间")
    private Date logisticsTime;

    @ApiModelProperty("物流准入时间")
    private String logisticsDate;

    @ApiModelProperty("联系人姓名")
    private String realName;
}
