package com.huacainfo.ace.common.mybatis;

import java.util.Properties;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

/**
 * 数据库操作性能拦截器,记录SQL执行耗时
 * 
 * @Intercepts定义Signature数组,因此可以拦截多个,但是只能拦截类型为： Executor ParameterHandler
 *                                              StatementHandler
 *                                              ResultSetHandler
 * */
@Intercepts(value = {
		@Signature(type = Executor.class, method = "update", args = {
				MappedStatement.class, Object.class }),
		@Signature(type = Executor.class, method = "query", args = {
				MappedStatement.class, Object.class, RowBounds.class,
				ResultHandler.class, CacheKey.class, BoundSql.class }),
		@Signature(type = Executor.class, method = "query", args = {
				MappedStatement.class, Object.class, RowBounds.class,
				ResultHandler.class }) })
public class SQLTimerInterceptor implements Interceptor {

	private final Logger logger = LoggerFactory
			.getLogger(SQLTimerInterceptor.class);

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		MappedStatement mappedStatement = getMappedStatement(invocation);
		Object result = null;
		if (mappedStatement != null) {
			StopWatch clock = new StopWatch();
			clock.start(); // 计时开始
			/** 执行方法 */
			result = invocation.proceed();
			clock.stop();
			long exaustTime = clock.getLastTaskTimeMillis();
			if (exaustTime < 50) {
				logger.debug("执行SQL:{},执行耗时:{}", mappedStatement.getId(),
						exaustTime);
			} else if (exaustTime < 100) {
				logger.info("执行SQL:{},执行耗时:{}", mappedStatement.getId(),
						exaustTime);
			} else if (exaustTime < 500) {
				logger.warn("执行SQL:{},执行耗时:{}", mappedStatement.getId(),
						exaustTime);
			} else {
				logger.error("执行SQL:{},执行耗时:{}", mappedStatement.getId(),
						exaustTime);
			}

		} else {
			result = invocation.proceed();
		}
		return result;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}

	private MappedStatement getMappedStatement(Invocation invocation) {
		MappedStatement target = null;
		Object[] args = invocation.getArgs();
		for (int i = 0; i < args.length; i++) {
			if (args[i] != null && args[i] instanceof MappedStatement) {
				target = (MappedStatement) args[i];
				break;
			}
		}
		return target;
	}

}
