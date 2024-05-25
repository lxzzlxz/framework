package com.liuzemin.server.framework.model.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class SupplierContact extends BaseModel {

    private static final long serialVersionUID = -3765688375211741333L;

    @ApiModelProperty("供应商id， 必填")
    private Long supplierId;

    @ApiModelProperty("联系人电话 必填")
    private String phone;

    @ApiModelProperty("联系人姓名 必填")
    private String contact;

    private List<Long> supplierIds;

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<Long> getSupplierIds() {
        return supplierIds;
    }

    public void setSupplierIds(List<Long> supplierIds) {
        this.supplierIds = supplierIds;
    }
}
