package com.liuzemin.server.framework.model.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class BaseService extends BaseModel {

    private static final long serialVersionUID = -8401358860964633859L;

    private String serviceName;

    private String contextPath;
 
}
