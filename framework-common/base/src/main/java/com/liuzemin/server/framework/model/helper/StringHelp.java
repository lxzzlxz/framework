package com.liuzemin.server.framework.model.helper;

import org.springframework.util.StringUtils;

public class StringHelp {
    /**
     * 前二后四 用****替换
     *
     * @param name
     * @return
     */
    public static String hideString(String name) {
        if (StringUtils.isEmpty(name)) {
            return name;
        }
        StringBuilder stb = new StringBuilder(name);
        String str = "****";
        if (name.length() > 6) {
            name = stb.replace(2, name.length() - 4, str).toString();
        } else if (name.length() > 2) {
            name = stb.substring(0, 2) + str;
        }
        return name;
    }
}
