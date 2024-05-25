package com.liuzemin.server.framework.model.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * (CrecMembershipRights)实体类
 *
 * @author feng yi
 * @since 2021-04-07 10:14:59
 */
@ApiModel("会员权益")
public class MembershipRights extends BaseModel {

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;
    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    private Date startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    private Date endTime;
    /**
     * 购买期限
     */
    @ApiModelProperty("购买期限")
    private Integer purchaseTerm;
    /**
     * 购买期限单位（1年）
     */
    @ApiModelProperty("购买期限单位（1年）")
    private Integer purchaseTermUnit;
    /**
     * 企业id
     */
    @ApiModelProperty("企业id")
    private Long enterpriseId;

    @ApiModelProperty("企业名称")
    private String enterpriseName;
    /**
     * 累计缴费金额
     */
    @ApiModelProperty("累计缴费金额")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal cumulativeAmount;
    /**
     * 缴费金额上限
     */
    @ApiModelProperty("缴费金额上限")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal maximumAmount;

    @ApiModelProperty("间隔天数")
    private Long days;
    /**
     * 状态（0已失效,1生效,2待生效）
     */
    @ApiModelProperty("状态（0已失效,1生效,2待生效）")
    private Integer status;

    @ApiModelProperty("状态名称（0已失效,1生效,2待生效）")
    private String statusName;

    @ApiModelProperty("所属企业类型（1.供应商，2.需求方， 7.区域经销商，8.经销商推荐，11.项目部，12.中介公司，13.个人，14.注册用户'）")
    private Integer enterpriseType;

    @ApiModelProperty("所属企业类型（1.供应商，2.需求方， 7.区域经销商，8.经销商推荐，11.项目部，12.中介公司，13.个人，14.注册用户'）")
    private String enterpriseTypeName;

    @ApiModelProperty("权益编号")
    private String rightsCode;

    @ApiModelProperty("权益类别 1.租赁VIP，2人才招聘")
    private Integer rightsType;

    @ApiModelProperty("权益类别 1.租赁VIP，2人才招聘")
    private String rightsTypeName;

    @ApiModelProperty("商品id")
    private Long productId;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("商品类型（1招聘方卡，2求职方卡）")
    private Integer productType;
    @ApiModelProperty("商品类型（1招聘方卡，2求职方卡）")
    private String productTypeName;

    @ApiModelProperty("购买次数")
    private Integer purchaseNumber;

    @ApiModelProperty("可用次数")
    private Integer remainingNumber;

    private List<Long> ids;

    private List<Long> enterpriseIds;
    @ApiModelProperty("时间类型")
    private String dateType;
    @ApiModelProperty("时间格式")
    private String dateFormatStr;

    public String getDateType() {
        return dateType;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPurchaseTerm() {
        return purchaseTerm;
    }

    public void setPurchaseTerm(Integer purchaseTerm) {
        this.purchaseTerm = purchaseTerm;
    }

    public Integer getPurchaseTermUnit() {
        return purchaseTermUnit;
    }

    public void setPurchaseTermUnit(Integer purchaseTermUnit) {
        this.purchaseTermUnit = purchaseTermUnit;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public BigDecimal getCumulativeAmount() {
        return cumulativeAmount;
    }

    public void setCumulativeAmount(BigDecimal cumulativeAmount) {
        this.cumulativeAmount = cumulativeAmount;
    }

    public BigDecimal getMaximumAmount() {
        return maximumAmount;
    }

    public void setMaximumAmount(BigDecimal maximumAmount) {
        this.maximumAmount = maximumAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getDays() {
        return days;
    }

    public void setDays(Long days) {
        this.days = days;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public List<Long> getEnterpriseIds() {
        return enterpriseIds;
    }

    public void setEnterpriseIds(List<Long> enterpriseIds) {
        this.enterpriseIds = enterpriseIds;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Integer getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(Integer enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public String getRightsCode() {
        return rightsCode;
    }

    public void setRightsCode(String rightsCode) {
        this.rightsCode = rightsCode;
    }

    public Integer getRightsType() {
        return rightsType;
    }

    public void setRightsType(Integer rightsType) {
        this.rightsType = rightsType;
    }

    public Integer getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(Integer purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    public Integer getRemainingNumber() {
        return remainingNumber;
    }

    public void setRemainingNumber(Integer remainingNumber) {
        this.remainingNumber = remainingNumber;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getEnterpriseTypeName() {
        return enterpriseTypeName;
    }

    public void setEnterpriseTypeName(String enterpriseTypeName) {
        this.enterpriseTypeName = enterpriseTypeName;
    }

    public String getRightsTypeName() {
        return rightsTypeName;
    }

    public void setRightsTypeName(String rightsTypeName) {
        this.rightsTypeName = rightsTypeName;
    }
}
