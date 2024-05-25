package com.liuzemin.server.framework.model.helper;

import java.util.UUID;

public class UUIDHelper {

    public static String getUUID(){

        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
