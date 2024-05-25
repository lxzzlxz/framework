package com.liuzemin.server.framework.model.model;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * (TplMemberPriceT)实体类
 *
 * @author feng yi
 * @since 2021-04-07 17:01:42
 */
@ApiModel("会员价格")
public class MemberPrice extends BaseModel {

    /**
     * 名称
     */
    @ApiModelProperty("编码")
    private String code;
    /**
     * 1年价格
     */
    @ApiModelProperty("1年价格")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal oneYearPrice;
    /**
     * 2年价格
     */
    @ApiModelProperty("2年价格")
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal twoYearPrice;
    /**
     * 类型（1，经营区域，2设备类别）
     */
    @ApiModelProperty("类型（1，经营区域，2设备类别，3年费上限，4，年费下限）")
    private Integer type;


    private String codeName;

    @ApiModelProperty("类型（1，基础服务费，2附加服务费）")
    private Integer feeType;

    /**
     * 名称
     */
    @ApiModelProperty("编码集合")
    private List<String> codes;

    @ApiModelProperty("会员权益id")
    private Long membershipRightsId;

    /**
     * 是否已经购买过
     */
    @ApiModelProperty("类型（true，已拥有，false未购买）")
    private boolean isOwned;

    /**
     * 子设备类别
     */
    @ApiModelProperty("子设备类别")
    private List<MerchanicalCategory> childrenCategories;


    public List<String> getCodes() {
        return codes;
    }

    public void setCodes(List<String> codes) {
        this.codes = codes;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getOneYearPrice() {
        return oneYearPrice;
    }

    public void setOneYearPrice(BigDecimal oneYearPrice) {
        this.oneYearPrice = oneYearPrice;
    }

    public BigDecimal getTwoYearPrice() {
        return twoYearPrice;
    }

    public void setTwoYearPrice(BigDecimal twoYearPrice) {
        this.twoYearPrice = twoYearPrice;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public Integer getFeeType() {
        return feeType;
    }

    public void setFeeType(Integer feeType) {
        this.feeType = feeType;
    }

    public Long getMembershipRightsId() {
        return membershipRightsId;
    }

    public void setMembershipRightsId(Long membershipRightsId) {
        this.membershipRightsId = membershipRightsId;
    }

    public boolean isOwned() {
        return isOwned;
    }

    public void setOwned(boolean owned) {
        isOwned = owned;
    }

    public List<MerchanicalCategory> getChildrenCategories() {
        return childrenCategories;
    }

    public void setChildrenCategories(List<MerchanicalCategory> childrenCategories) {
        this.childrenCategories = childrenCategories;
    }

    @Override
    public String toString() {
        return "MemberPrice{" +
                "code='" + code + '\'' +
                ", oneYearPrice=" + oneYearPrice +
                ", twoYearPrice=" + twoYearPrice +
                ", type=" + type +
                ", codeName='" + codeName + '\'' +
                ", feeType=" + feeType +
                ", codes=" + codes +
                ", membershipRightsId=" + membershipRightsId +
                ", isOwned=" + isOwned +
                ", childrenCategories=" + childrenCategories +
                '}';
    }
}
