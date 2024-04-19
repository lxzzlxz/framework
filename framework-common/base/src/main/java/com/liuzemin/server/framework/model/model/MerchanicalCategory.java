package com.liuzemin.server.framework.model.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MerchanicalCategory implements Serializable {

	private static final long serialVersionUID = 6938689646296588386L;

	private Long id;

	private String name;

	private Long parentId;

	private String code;

	private String specificationUnit;

	private Integer sort;

	private List<MerchanicalCategory> children;
}
