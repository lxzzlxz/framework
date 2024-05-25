package com.liuzemin.server.framework.model.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class DemandCompanyAdmin extends BaseModel {

    private static final long serialVersionUID = -4265246785530485450L;

    private Long userId;

    private Long fatherProjectUnit;
    private List<Long> fatherProjectUnits;

    private Long projectUnit;

    private List<Long> projectUnits;

    private String userName;

    private Integer deleteFlag;

    private String mail;

    private String phone;


}
