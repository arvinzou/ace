package com.huacainfo.ace.common.fastjson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

public class LongDateFormatSerializer implements ObjectSerializer {

	public void write(JSONSerializer serializer, Object object,
			Object fieldName, Type fieldType, int features) throws IOException {
		if (object == null) {
			serializer.writeNull();
			return;
		}

		Date date = (Date) object;
		serializer.write(date.getTime());
	}

}
