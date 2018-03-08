package com.huacainfo.ace.common.web.converter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.FileCopyUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.huacainfo.ace.common.model.UserProp;

public class MappingFastJsonHttpMessageConverter extends
		AbstractHttpMessageConverter<Object> {
	protected static SerializeConfig mapping = new SerializeConfig();
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private static String dateFormat;
	static {
		dateFormat = "yyyy-MM-dd HH:mm:ss";
		mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));
		mapping.put(java.sql.Date.class, new SimpleDateFormatSerializer(
				dateFormat));
	}
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
	private SerializerFeature[] serializerFeature;

	public SerializerFeature[] getSerializerFeature() {
		return serializerFeature;
	}

	public void setSerializerFeature(SerializerFeature[] serializerFeature) {
		this.serializerFeature = serializerFeature;

	}

	public MappingFastJsonHttpMessageConverter() {
		super(new MediaType("application", "json", DEFAULT_CHARSET));
	}

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return true;
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return true;
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		Object rst = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] bytes = new byte[1024];
		int i;
		while ((i = inputMessage.getBody().read(bytes)) != -1) {
			baos.write(bytes, 0, i);
		}
		String content = baos.toString().trim();
		logger.info("{}",content);
		if (content.startsWith("[")) {
			JSONArray item = JSON.parseArray(content);
			List list=new ArrayList();
			for(Object o:item){
				list.add(o);
			}
			return list;
		} else if (content.startsWith("{")) {
			rst = JSON.parseObject(content, clazz);
		} else {
			rst = content;
		}
		return rst;
	}

	@Override
	protected void writeInternal(Object o, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		if (o != null) {
			outputMessage.getHeaders().setContentType(
					this.getSupportedMediaTypes().get(0));
			String jsonString = JSON
					.toJSONString(o, mapping, serializerFeature);
			FileCopyUtils.copy(jsonString,
					new OutputStreamWriter(outputMessage.getBody(),
							DEFAULT_CHARSET));
		}
	}

	public static  void main(String args[]){
		Logger logger = LoggerFactory.getLogger(MappingFastJsonHttpMessageConverter.class);
		List list=new java.util.ArrayList<UserProp>();

		Class<?> clazz=java.util.List.class;

	}


}
