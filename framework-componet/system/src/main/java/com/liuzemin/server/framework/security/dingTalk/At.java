package com.liuzemin.server.framework.security.dingTalk;


import cn.hutool.json.JSONArray;
import lombok.Data;

@Data
public class At {
    /**
     * 手机号，可以是多个
     */
    private JSONArray atMobiles;

    public At() {
    }

    public At(JSONArray jsonArrayMobile) {
        atMobiles = jsonArrayMobile;
    }

}