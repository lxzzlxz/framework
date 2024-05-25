package com.liuzemin.server.framework.model.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
 
public class JsonUtil {
	
	public static final Logger log = LoggerFactory.getLogger(String.class);
	
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	public static String bean2Json(Object obj) throws IOException {
		StringWriter sw = new StringWriter();
		JsonGenerator gen = new JsonFactory().createGenerator(sw);
		mapper.writeValue(gen, obj);
		gen.close();
		return sw.toString();
	}

	/**
	 * @param jsonStr
	 * @param objClass
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
		try {
			return mapper.readValue(jsonStr, objClass);
		} catch (Exception e) {
			log.error(ExceptionUtil.getStackTrace(e));
			return null;
		}
	}
	/**
	 * json转map
	 * @param jsonStr
	 * @param mapClass
	 * @param keyClass
	 * @param valueClass
	 * @return
	 */
	public static <K,V>HashMap<K,V> json2Map(String jsonStr, Class<K> keyClass, Class<V> valueClass){
		JavaType mapType = getMapType(HashMap.class,keyClass,valueClass);
		try {
			return (HashMap<K, V>) mapper.readValue(jsonStr, mapType);
		} catch (IOException e) {
			log.error(ExceptionUtil.getStackTrace(e));
			return null;
		}
	}
	public static <K,V>HashMap<String,V> json2HashMap(String jsonStr, Class<V> valueClass){
		return json2Map(jsonStr,String.class,valueClass);
	}
	/**
	 * json转list
	 * @param jsonStr
	 * @param listClass
	 * @param elementClasses
	 * @return
	 */
	public static <V>List<V> json2List(String jsonStr,Class<?> listClass, Class<V> elementClasses){
		JavaType mapType = getCollectionType(listClass,elementClasses);
		try {
			return (List<V>) mapper.readValue(jsonStr, mapType);
		} catch (IOException e) {
			log.error(ExceptionUtil.getStackTrace(e));
			return null;
		}
	}
	/**
	 *  获取list的类型
	 * @param collectionClass
	 * @param elementClasses
	 * @return
	 */
	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {   
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);   
	}
	/**
	 * 获取map的类型
	 * @param mapClass
	 * @param keyClass
	 * @param valueClass
	 * @return
	 */
	public static JavaType getMapType(Class<?> mapClass, Class<?> keyClass, Class<?> valueClass) {   
		return mapper.getTypeFactory().constructParametricType(mapClass,keyClass,valueClass);
	}
}
