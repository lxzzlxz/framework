package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SupplierRegion implements Serializable {

    private static final long serialVersionUID = 3084177555300090319L;

    private Long id;

    private Long supplierId;

    @ApiModelProperty("省编码")
    private String provinceCode;

    @ApiModelProperty("省名称")
    private String province;

    @ApiModelProperty("会员权益id")
    private Long membershipRightsId;

    private List<Long> membershipRightsIds;

    private List<String> provinceCodes;

    private Date creationDate;

    private Long createdBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getMembershipRightsId() {
        return membershipRightsId;
    }

    public void setMembershipRightsId(Long membershipRightsId) {
        this.membershipRightsId = membershipRightsId;
    }

    public List<Long> getMembershipRightsIds() {
        return membershipRightsIds;
    }

    public void setMembershipRightsIds(List<Long> membershipRightsIds) {
        this.membershipRightsIds = membershipRightsIds;
    }

    public List<String> getProvinceCodes() {
        return provinceCodes;
    }

    public void setProvinceCodes(List<String> provinceCodes) {
        this.provinceCodes = provinceCodes;
    }
}
