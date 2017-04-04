package com.huacainfo.ace.common.web.converter;

import java.util.Date;

import com.huacainfo.ace.common.fastjson.LongDateFormatSerializer;

public class MappingFastJsonPortMessageConverter extends
		MappingFastJsonHttpMessageConverter {
	static {
		mapping.put(Date.class, new LongDateFormatSerializer());
		mapping.put(java.sql.Date.class, new LongDateFormatSerializer());
	}

}
