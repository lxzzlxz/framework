package com.liuzemin.server.framework.model.context;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.liuzemin.server.framework.model.model.Supplier;
import com.liuzemin.server.framework.model.model.UserPriciple;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
@Data
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
}
