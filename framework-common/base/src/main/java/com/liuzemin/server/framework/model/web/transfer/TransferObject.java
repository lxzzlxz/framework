
package com.liuzemin.server.framework.model.web.transfer;

import com.liuzemin.server.framework.model.model.BaseModel;
import com.liuzemin.server.framework.model.utils.ReflectUtils;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;


public class TransferObject<T extends BaseModel> implements Serializable {

	private static final long serialVersionUID = 6414339399817474755L;

	public void toObject(T obj) {
		BeanUtils.copyProperties(this, obj);

	};

	public T toObject(Class<T> clazz) {
		T entity = ReflectUtils.newInstance(clazz);
		if (null != entity) {
			toObject(entity);
		}
		return entity;
	};

}
