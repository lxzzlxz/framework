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

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public Date getLastApproverDate() {
        return lastApproverDate;
    }

    public void setLastApproverDate(Date lastApproverDate) {
        this.lastApproverDate = lastApproverDate;
    }

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Long> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public Integer getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Integer reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public String getLogisticsDate() {
        return logisticsDate;
    }

    public void setLogisticsDate(String logisticsDate) {
        this.logisticsDate = logisticsDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getTurnDown() {
        return turnDown;
    }

    public void setTurnDown(Integer turnDown) {
        this.turnDown = turnDown;
    }

    public Long getReviewApprover() {
        return reviewApprover;
    }

    public void setReviewApprover(Long reviewApprover) {
        this.reviewApprover = reviewApprover;
    }

    private List<SupplierContact> supplierContactList;

    public List<String> getProvinceCodes() {
        return provinceCodes;
    }

    public void setProvinceCodes(List<String> provinceCodes) {
        this.provinceCodes = provinceCodes;
    }

    public List<Long> getFatherCategoryIds() {
        return fatherCategoryIds;
    }

    public void setFatherCategoryIds(List<Long> fatherCategoryIds) {
        this.fatherCategoryIds = fatherCategoryIds;
    }

    public String getSupplierLicense() {
        return supplierLicense;
    }

    public void setSupplierLicense(String supplierLicense) {
        this.supplierLicense = supplierLicense;
    }

    public String getDepositBank() {
        return depositBank;
    }

    public void setDepositBank(String depositBank) {
        this.depositBank = depositBank;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public List<Merchanical> getMerchanicalList() {
        return merchanicalList;
    }

    public void setMerchanicalList(List<Merchanical> merchanicalList) {
        this.merchanicalList = merchanicalList;
    }

    public List<MerchanicalCategoryCount> getCategoryCountList() {
        return categoryCountList;
    }

    public void setCategoryCountList(List<MerchanicalCategoryCount> categoryCountList) {
        this.categoryCountList = categoryCountList;
    }

    public List<MerchanicalCategoryCount> getFatherCategoryCountList() {
        return fatherCategoryCountList;
    }

    public void setFatherCategoryCountList(List<MerchanicalCategoryCount> fatherCategoryCountList) {
        this.fatherCategoryCountList = fatherCategoryCountList;
    }

    public Long getReviewFirstApprover() {
        return reviewFirstApprover;
    }

    public void setReviewFirstApprover(Long reviewFirstApprover) {
        this.reviewFirstApprover = reviewFirstApprover;
    }

    public Date getReviewFirstDate() {
        return reviewFirstDate;
    }

    public void setReviewFirstDate(Date reviewFirstDate) {
        this.reviewFirstDate = reviewFirstDate;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getBusinessLicenseImage() {
        return businessLicenseImage;
    }

    public void setBusinessLicenseImage(String businessLicenseImage) {
        this.businessLicenseImage = businessLicenseImage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getApprover() {
        return approver;
    }

    public void setApprover(Long approver) {
        this.approver = approver;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getMainBusiness() {
        return mainBusiness;
    }

    public void setMainBusiness(String mainBusiness) {
        this.mainBusiness = mainBusiness;
    }

    public Integer getTaxpayerType() {
        return taxpayerType;
    }

    public void setTaxpayerType(Integer taxpayerType) {
        this.taxpayerType = taxpayerType;
    }

    public String getCertImage() {
        return certImage;
    }

    public void setCertImage(String certImage) {
        this.certImage = certImage;
    }

    public String getBusinessProvince() {
        return businessProvince;
    }

    public void setBusinessProvince(String businessProvince) {
        this.businessProvince = businessProvince;
    }

    public String getContactIdcardNO() {
        return contactIdcardNO;
    }

    public void setContactIdcardNO(String contactIdcardNO) {
        this.contactIdcardNO = contactIdcardNO;
    }

    public String getIdCardImage() {
        return idCardImage;
    }

    public void setIdCardImage(String idCardImage) {
        this.idCardImage = idCardImage;
    }

    public String getIdCardFrontImage() {
        return idCardFrontImage;
    }

    public void setIdCardFrontImage(String idCardFrontImage) {
        this.idCardFrontImage = idCardFrontImage;
    }

    public String getIdCardSideImage() {
        return idCardSideImage;
    }

    public void setIdCardSideImage(String idCardSideImage) {
        this.idCardSideImage = idCardSideImage;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMerchanicalScale() {
        return merchanicalScale;
    }

    public void setMerchanicalScale(String merchanicalScale) {
        this.merchanicalScale = merchanicalScale;
    }

    public BigDecimal getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(BigDecimal registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public Integer getIsRent() {
        return isRent;
    }

    public Boolean getHideInfo() {
        return isHideInfo;
    }

    public void setHideInfo(Boolean hideInfo) {
        isHideInfo = hideInfo;
    }

    public void setIsRent(Integer isRent) {
        this.isRent = isRent;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public List<KeyValueModel> getQualificationList() {
        return qualificationList;
    }

    public void setQualificationList(List<KeyValueModel> qualificationList) {
        this.qualificationList = qualificationList;
    }

    public String getReviewReason() {
        return reviewReason;
    }

    public void setReviewReason(String reviewReason) {
        this.reviewReason = reviewReason;
    }

    public Float getScoreDev() {
        return scoreDev;
    }

    public void setScoreDev(Float scoreDev) {
        this.scoreDev = scoreDev;
    }

    public Float getScoreIncr() {
        return scoreIncr;
    }

    public void setScoreIncr(Float scoreIncr) {
        this.scoreIncr = scoreIncr;
    }

    public Float getScoreDevStart() {
        return scoreDevStart;
    }

    public void setScoreDevStart(Float scoreDevStart) {
        this.scoreDevStart = scoreDevStart;
    }

    public Float getScoreDevEnd() {
        return scoreDevEnd;
    }

    public void setScoreDevEnd(Float scoreDevEnd) {
        this.scoreDevEnd = scoreDevEnd;
    }

    public List<SupplierContact> getSupplierContactList() {
        return supplierContactList;
    }

    public void setSupplierContactList(List<SupplierContact> supplierContactList) {
        this.supplierContactList = supplierContactList;
    }

    public Long getFirstApprover() {
        return firstApprover;
    }

    public void setFirstApprover(Long firstApprover) {
        this.firstApprover = firstApprover;
    }

    public Date getFirstApproveDate() {
        return firstApproveDate;
    }

    public void setFirstApproveDate(Date firstApproveDate) {
        this.firstApproveDate = firstApproveDate;
    }

    public Integer getApproveDateFlag() {
        return approveDateFlag;
    }

    public void setApproveDateFlag(Integer approveDateFlag) {
        this.approveDateFlag = approveDateFlag;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getAuditReason() {
        return auditReason;
    }

    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Long getRecommendedUnit() {
        return recommendedUnit;
    }

    public void setRecommendedUnit(Long recommendedUnit) {
        this.recommendedUnit = recommendedUnit;
    }

    public Integer getIsUplodDevices() {
        return isUplodDevices;
    }

    public void setIsUplodDevices(Integer isUplodDevices) {
        this.isUplodDevices = isUplodDevices;
    }

    public String getDateType() {
        return dateType;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getDateFormatStr() {
        return dateFormatStr;
    }

    public void setDateFormatStr(String dateFormatStr) {
        this.dateFormatStr = dateFormatStr;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public Integer getLogisticsType() {
        return logisticsType;
    }

    public void setLogisticsType(Integer logisticsType) {
        this.logisticsType = logisticsType;
    }

    public Date getLogisticsTime() {
        return logisticsTime;
    }

    public void setLogisticsTime(Date logisticsTime) {
        this.logisticsTime = logisticsTime;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public List<Long> getRecommendedUnitList() {
        return recommendedUnitList;
    }

    public void setRecommendedUnitList(List<Long> recommendedUnitList) {
        this.recommendedUnitList = recommendedUnitList;
    }
}
