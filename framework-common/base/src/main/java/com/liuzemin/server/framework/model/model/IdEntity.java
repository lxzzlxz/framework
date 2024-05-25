package com.liuzemin.server.framework.model.model;

public class IdEntity extends AbstractEntity{
	
	private static final long serialVersionUID = 1L;
	private Long id;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setId(Object id) {
		setId(id==null ? null:Long.valueOf((String) id));
	}

	 
}
