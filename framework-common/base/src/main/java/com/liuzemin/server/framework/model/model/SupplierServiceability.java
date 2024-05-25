package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class SupplierServiceability extends BaseModel {

    private static final long serialVersionUID = -8999407227913080754L;

    @ApiModelProperty("供应商id")
    private Long supplierId;

    @ApiModelProperty("会员权益id")
    private Long membershipRightsId;


    @ApiModelProperty("租赁业务经营区域")
    private List<SupplierRegion> provinceList;

    @ApiModelProperty("租赁业务经营设备类别")
    private List<SupplierCategory> fatherCategoryList;


    @ApiModelProperty("会员权益ids")
    private List<Long> membershipRightsIds;

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public List<SupplierCategory> getFatherCategoryList() {
        return fatherCategoryList;
    }

    public void setFatherCategoryList(List<SupplierCategory> fatherCategoryList) {
        this.fatherCategoryList = fatherCategoryList;
    }

    public List<SupplierRegion> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<SupplierRegion> provinceList) {
        this.provinceList = provinceList;
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
}
