package com.liuzemin.server.framework.model.utils;

import com.liuzemin.server.framework.model.web.transfer.TransferObject;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class VOUtil {

	@SuppressWarnings("rawtypes")
	public static <G> void toVO(G entity, TransferObject distVO) {
		if (null != entity && null != distVO) {
			BeanUtils.copyProperties(entity, distVO);
		}

	}

	@SuppressWarnings("rawtypes")
	public static <T extends TransferObject, G> List<T> toListVO(List<G> list, Class<T> clazz) {
		List<T> distList = new ArrayList<T>();
		if (null != list && list.size() > 0) {
			for (G srcEntity : list) {
				T distVO = ReflectUtils.newInstance(clazz);
				toVO(srcEntity, distVO);
				distList.add(distVO);
			}
		}

		return distList;

	}
}
