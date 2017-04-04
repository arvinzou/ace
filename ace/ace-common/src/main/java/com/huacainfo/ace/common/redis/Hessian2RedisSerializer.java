package com.huacainfo.ace.common.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

public class Hessian2RedisSerializer implements RedisSerializer<Object> {
	private Logger logger = LoggerFactory
			.getLogger(Hessian2RedisSerializer.class);
	static final byte[] EMPTY_ARRAY = new byte[0];

	static boolean isEmpty(byte[] data) {
		return (data == null || data.length == 0);
	}

	@Override
	public byte[] serialize(Object obj) throws SerializationException {
		byte[] bytes = EMPTY_ARRAY;
		if (obj != null) {
			ByteArrayOutputStream bo = null;
			Hessian2Output oo = null;
			try {
				bo = new ByteArrayOutputStream();
				oo = new Hessian2Output(bo);
				oo.writeObject(obj);
				oo.flush();
				bytes = bo.toByteArray();
			} catch (IOException ex) {
				logger.error("序列化对象出错", ex);
				throw new SerializationException(ex.getMessage());
			} finally {
				try {
					if (bo != null) {
						bo.close();
					}
					if (oo != null) {
						oo.close();
					}
				} catch (Exception ex) {

				}
			}
		}
		return bytes;
	}

	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {
		Object obj = null;
		if (!isEmpty(bytes)) {
			ByteArrayInputStream bi = null;
			bi = new ByteArrayInputStream(bytes);
			Hessian2Input hi = new Hessian2Input(bi);
			try {
				obj = hi.readObject();
			} catch (IOException ex) {
				logger.error("反序列化对象出错", ex);
				throw new SerializationException(ex.getMessage());
			} finally {
				try {
					if (bi != null) {
						bi.close();
					}
					if (hi != null) {
						hi.close();
					}
				} catch (Exception ex) {

				}
			}
		}
		return obj;
	}

}
