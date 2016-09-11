package com.b5m.bailongma;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang.StringUtils;

public class SerializerUtils {
	private final static String OBJECT_TYPE_FALG = "@type";

	public static boolean isObject(String value) {
		if (value.indexOf(OBJECT_TYPE_FALG) > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Object> T Deserialization(String stringObj) {
		if (StringUtils.isBlank(stringObj)) {
			return null;
		}

		Object jsonObj = null;
		if (!isObject(stringObj)) {
			jsonObj = stringObj;
		} else {
			jsonObj = JSON.parse(stringObj);
		}

		if (jsonObj == null)
			return null;
		return (T) jsonObj;

	}

	public static <T extends Object> String Serialization(T obj) {
		if (obj == null) {
			return null;
		}
		String v = null;

		if (obj instanceof String) {
			v = (String) obj;
		} else {
			v = JSON.toJSONString(obj, SerializerFeature.WriteClassName);
		}
		return v;
	}
}
