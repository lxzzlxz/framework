package com.liuzemin.server.framework.model.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class R<T> implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;

    private String msg;

    private T datas;

}
