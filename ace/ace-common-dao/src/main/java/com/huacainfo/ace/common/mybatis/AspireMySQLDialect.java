package com.huacainfo.ace.common.mybatis;
/**
 *
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.mapping.MappedStatement;

import com.github.miemiedev.mybatis.paginator.dialect.MySQLDialect;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public class AspireMySQLDialect extends MySQLDialect {
	public static final Pattern PATTERN_SQL_BLANK = Pattern.compile("\\s+");
	public static final String BLANK = " ";
	public static final String SELECT = "select";

	public static final String FROM = "from";

	public static final String ORDER_BY = "order by";

	public AspireMySQLDialect(MappedStatement mappedStatement,
			Object parameterObject, PageBounds pageBounds) {
		super(mappedStatement, parameterObject, pageBounds);
	}

	protected String getLimitString(String sql, String offsetName, int offset,
			String limitName, int limit) {
		StringBuffer buffer = new StringBuffer(sql.length() + 20).append(sql);
		if (offset > 0) {
			buffer.append(" limit ?, ?");
			setPageParameter(offsetName, offset, Integer.class);
			setPageParameter(limitName, limit, Integer.class);
		} else {
			buffer.append(" limit ?");
			setPageParameter(limitName, limit, Integer.class);
		}
		return buffer.toString();
	}

	protected String getCountString(String targetSql) {
		targetSql = replaceSqlBlank(targetSql);
		String sql = targetSql.toLowerCase();
		StringBuilder sqlBuilder = new StringBuilder(targetSql);

		int orderByPos = 0;
		if ((orderByPos = sql.lastIndexOf(ORDER_BY)) != -1) {
			sqlBuilder.delete(orderByPos, sqlBuilder.length());
		}
		sqlBuilder.delete(0, sql.indexOf(FROM));
		sqlBuilder.insert(0, "select count(1) as _count  ");
		sql = sqlBuilder.toString();
		return sql;
	}

	protected String replaceSqlBlank(String originalSql) {
		Matcher matcher = PATTERN_SQL_BLANK.matcher(originalSql);
		return matcher.replaceAll(BLANK);
	}
}
