package com.liuzemin.server.framework.security.auth.model;

import java.io.Serializable;

/**
 * 天眼查返回对象
 */
public class TianYanChaResult implements Serializable {

    private static final long serialVersionUID = 8036060190592551570L;

    private String error_code;
    private String reason;
    private TianYanChaBaseInfo result;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public TianYanChaBaseInfo getResult() {
        return result;
    }

    public void setResult(TianYanChaBaseInfo result) {
        this.result = result;
    }
}
