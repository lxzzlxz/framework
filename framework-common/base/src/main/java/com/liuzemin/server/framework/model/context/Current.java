package com.liuzemin.server.framework.model.context;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.liuzemin.server.framework.model.model.Supplier;
import com.liuzemin.server.framework.model.model.UserPriciple;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Current implements Serializable {

    private static final long serialVersionUID = 1L;

    private UserPriciple user;

    private Set<String> permissions;

    private Long supplierId;

    private Supplier supplier;

    private List<Long> enterpriseIds;

    private String enterpriseName;

    private Map<Long, String> projectNameMap;


    @JSONField(serialize = false)
    @JsonIgnore
    private Boolean isPermissionChecked = false;

    @JSONField(serialize = false)
    @JsonIgnore
    private String token;

    @JSONField(serialize = false)
    @JsonIgnore
    private String domain;

    @JSONField(serialize = false)
    @JsonIgnore
    private StringBuilder serviceLog = new StringBuilder();

    @JSONField(serialize = false)
    @JsonIgnore
    private StringBuilder accessLog = new StringBuilder();

    @JSONField(serialize = false)
    @JsonIgnore
    private StringBuilder returnLog = new StringBuilder();

    @JSONField(serialize = false)
    @JsonIgnore
    private HttpServletRequest request;

    @JSONField(serialize = false)
    @JsonIgnore
    private HttpServletResponse response;

    @JSONField(serialize = false)
    @JsonIgnore
    private Boolean getData = false;

    @JsonIgnore
    public HttpServletRequest getRequest() {
        return request;
    }

    @JsonIgnore
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @JsonIgnore
    public HttpServletResponse getResponse() {
        return response;
    }

    @JsonIgnore
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @JsonIgnore
    public StringBuilder getServiceLog() {
        return serviceLog;
    }

    @JsonIgnore
    public void setServiceLog(StringBuilder serviceLog) {
        this.serviceLog = serviceLog;
    }

    public UserPriciple getUser() {
        return user;
    }

    public void setUser(UserPriciple user) {
        this.user = user;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getPermissionChecked() {
        return isPermissionChecked;
    }

    public void setPermissionChecked(Boolean permissionChecked) {
        isPermissionChecked = permissionChecked;
    }

    public StringBuilder getAccessLog() {
        return accessLog;
    }

    public void setAccessLog(StringBuilder accessLog) {
        this.accessLog = accessLog;
    }

    public StringBuilder getReturnLog() {
        return returnLog;
    }

    public void setReturnLog(StringBuilder returnLog) {
        this.returnLog = returnLog;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Boolean getGetData() {
        return getData;
    }

    public void setGetData(Boolean getData) {
        this.getData = getData;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
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

    public Map<Long, String> getProjectNameMap() {
        return projectNameMap;
    }

    public void setProjectNameMap(Map<Long, String> projectNameMap) {
        this.projectNameMap = projectNameMap;
    }
}
