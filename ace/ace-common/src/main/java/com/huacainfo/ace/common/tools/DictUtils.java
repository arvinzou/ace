package com.huacainfo.ace.common.tools;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import com.huacainfo.ace.common.fastjson.DateJsonValueProcessor;

public class DictUtils {
	public static String toJsonString(Map<String, Object> valueObject,
			String tableNames) {
		String[] tables = tableNames.split(",");
		Set<String> tablesSet = new HashSet<String>();
		for (int i = 0; i < tables.length; i++) {
			tablesSet.add(tables[i]);
		}
		Set<String> ignorePropSet = new HashSet<String>();
		Set<String> keySet = valueObject.keySet();
		for (String key : keySet) {
			if (!tablesSet.contains(key)) {
				ignorePropSet.add(key);
			}
		}

		JsonConfig config = DictUtils.getJsonConfig(ignorePropSet);
		JSON json = JSONSerializer.toJSON(valueObject, config);
		return json.toString();
	}

	public static String toJsonString(Map<String, ?> valueObject) {
		JSON json = JSONSerializer.toJSON(valueObject);
		return json.toString();
	}

	public static String toJsonString(Map<String, ?> valueObject,
			String[] ignoreProps) {
		Set<String> ignorePropSet = new HashSet<String>();
		for (String key : ignoreProps) {
			ignorePropSet.add(key);
		}
		JsonConfig config = DictUtils.getJsonConfig(ignorePropSet);
		JSON json = JSONSerializer.toJSON(valueObject, config);
		return json.toString();
	}
	public static JsonConfig getJsonConfig(final Set<String> set) {
		JsonConfig jsonConfig = new JsonConfig();
		DateJsonValueProcessor dateProc = new DateJsonValueProcessor();
		jsonConfig.registerJsonValueProcessor(Date.class, dateProc);
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (set.contains(name)) {
					return true;
				} else {
					return false;
				}
			}

		});

		return jsonConfig;
	}
}
