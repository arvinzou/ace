package com.huacainfo.ace.common.mybatis;

import java.io.InputStream;
import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.miemiedev.mybatis.paginator.support.PropertiesHelper;

@Intercepts({ @Signature(method = "handleResultSets", type = ResultSetHandler.class, args = { Statement.class }) })
public class ResultSetRowLimitInterceptor extends MybatisInterceptorAbstract {
	private final Logger logger = LoggerFactory
			.getLogger(ResultSetRowLimitInterceptor.class);
	private int rowsLimit = Integer.MAX_VALUE;

	public ResultSetRowLimitInterceptor() {

	}

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object result = null;
		MappedStatement mappedStatement = getMappedStatement(invocation);
		if ((!mappedStatement.getSqlCommandType().equals(SqlCommandType.SELECT))
				|| rowsLimit == Integer.MAX_VALUE) {
			result = invocation.proceed();
		} else {
			ObjectFactory objectFactory = getObjectFactory(invocation);
			LimitedResultHandler resultHandler = new LimitedResultHandler(
					objectFactory, rowsLimit);
			writeDeclaredField(invocation.getTarget(), "resultHandler",
					resultHandler);
			invocation.proceed();
			if (resultHandler.getExceedLimit()) {
				logger.error("执行SQL:{},返回记录数超过限制数:{}", mappedStatement.getId(),
						rowsLimit);
			}
			result = resultHandler.getResultList();
		}

		return result;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		init();
	}

	private MappedStatement getMappedStatement(Invocation invocation)
			throws IllegalAccessException {
		MappedStatement mappedStatement = (MappedStatement) readField(
				invocation.getTarget(), "mappedStatement");
		return mappedStatement;
	}

	private ObjectFactory getObjectFactory(Invocation invocation)
			throws IllegalAccessException {
		ObjectFactory objectFactory = (ObjectFactory) readField(
				invocation.getTarget(), "objectFactory");
		return objectFactory;
	}

	public int getRowsLimit() {
		return rowsLimit;
	}

	public void setRowsLimit(int rowsLimit) {
		this.rowsLimit = rowsLimit;
	}

	private void init() {
		Properties properties = new Properties();
		try {
			InputStream input = this.getClass().getResourceAsStream(
					"/config.properties");
			properties.load(input);
			PropertiesHelper propertiesHelper = new PropertiesHelper(properties);
			setRowsLimit(propertiesHelper.getInt("dao.mybatis.result.maxrows",
					Integer.MAX_VALUE));
		} catch (Exception e) {
			logger.warn("加载查询限制最大结果集配置失败，限制结果集拦截器失效", e);
		}
	}
}
