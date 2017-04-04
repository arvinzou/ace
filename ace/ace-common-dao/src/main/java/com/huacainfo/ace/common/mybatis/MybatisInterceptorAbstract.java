package com.huacainfo.ace.common.mybatis;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.apache.ibatis.plugin.Interceptor;

public abstract class MybatisInterceptorAbstract implements Interceptor {
	protected static final String FIELD_DELEGATE = "delegate";
	protected static final String FIELD_MAPPEDSTATEMENT = "mappedStatement";

	protected void writeDeclaredField(Object target, String fieldName,
			Object value) throws IllegalAccessException {
		if (target == null) {
			throw new IllegalArgumentException("target object must not be null");
		}
		Class<?> cls = target.getClass();
		Field field = getField(cls, fieldName);
		if (field == null) {
			throw new IllegalArgumentException("Cannot locate declared field "
					+ cls.getName() + "." + fieldName);
		}
		field.set(target, value);
	}

	protected Object readField(Object target, String fieldName)
			throws IllegalAccessException {
		if (target == null) {
			throw new IllegalArgumentException("target object must not be null");
		}
		Class<?> cls = target.getClass();
		Field field = getField(cls, fieldName);
		if (field == null) {
			throw new IllegalArgumentException("Cannot locate field "
					+ fieldName + " on " + cls);
		}
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		return field.get(target);
	}

	protected static Field getField(final Class<?> cls, String fieldName) {
		for (Class<?> acls = cls; acls != null; acls = acls.getSuperclass()) {
			try {
				Field field = acls.getDeclaredField(fieldName);
				if (!Modifier.isPublic(field.getModifiers())) {
					field.setAccessible(true);
					return field;
				}
			} catch (NoSuchFieldException ex) {
				// ignore
			}
		}
		return null;
	}
}
