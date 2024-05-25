package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SupplierCategory implements Serializable {

    private static final long serialVersionUID = -7841167264582995920L;

    private Long id;

    private Long supplierId;

    @ApiModelProperty("设备类型ID")
    private Long fatherCategoryId;

    @ApiModelProperty("设备类型名称")
    private String fatherCategory;

    @ApiModelProperty("会员权益id")
    private Long membershipRightsId;


    private List<Long> membershipRightsIds;

    private Long createdBy;

    private Date creationDate;

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getMembershipRightsId() {
        return membershipRightsId;
    }

    public void setMembershipRightsId(Long membershipRightsId) {
        this.membershipRightsId = membershipRightsId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getFatherCategoryId() {
        return fatherCategoryId;
    }

    public void setFatherCategoryId(Long fatherCategoryId) {
        this.fatherCategoryId = fatherCategoryId;
    }

    public String getFatherCategory() {
        return fatherCategory;
    }

    public void setFatherCategory(String fatherCategory) {
        this.fatherCategory = fatherCategory;
    }

    public List<Long> getMembershipRightsIds() {
        return membershipRightsIds;
    }

    public void setMembershipRightsIds(List<Long> membershipRightsIds) {
        this.membershipRightsIds = membershipRightsIds;
    }



}
