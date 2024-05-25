package com.liuzemin.server.framework.model.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class KeyValueModel implements Serializable {

    private static final long serialVersionUID = 4388037470428654627L;

    private String key;

    private String value;

    public KeyValueModel(){};

    public KeyValueModel( String key,String value){
        this.key=key;
        this.value=value;
    };


}
