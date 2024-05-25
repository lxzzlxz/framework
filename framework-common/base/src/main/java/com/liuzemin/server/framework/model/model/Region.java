package com.liuzemin.server.framework.model.model;

import java.io.Serializable;
import java.util.List;

public class Region implements Serializable {

    private static final long serialVersionUID = -3340186424165377914L;

    private Long id;

    private String code;

    private String parentCode;

    private String name;

    private List<Region> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Region> getChildren() {
        return children;
    }

    public void setChildren(List<Region> children) {
        this.children = children;
    }
}
