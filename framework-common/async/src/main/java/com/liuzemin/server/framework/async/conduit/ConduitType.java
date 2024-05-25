package com.liuzemin.server.framework.async.conduit;

public enum ConduitType {

    thread(1), rabbitmq(2), kafka(3);

    private Integer value;

    private ConduitType(Integer value){
        this.value = value;
    }

    public Integer getValue(){
        return this.value;
    }
}
