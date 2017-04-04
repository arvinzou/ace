package com.huacainfo.ace.common.mybatis;

import org.apache.ibatis.mapping.MappedStatement;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

public class AspireOracleDialect extends AspireMySQLDialect {

	public AspireOracleDialect(MappedStatement mappedStatement,
			Object parameterObject, PageBounds pageBounds) {
		super(mappedStatement, parameterObject, pageBounds);
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

	protected String getLimitString(String sql, String offsetName, int offset,
			String limitName, int limit) {
		sql = replaceSqlBlank(sql);
		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(" for update")) {
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}

		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		if (offset > 0) {
			pagingSelect
					.append("select * from ( select row_.*, rownum rownum_ from ( ");
		} else {
			pagingSelect.append("select * from ( ");
		}
		pagingSelect.append(sql);
		if (offset > 0) {
			pagingSelect.append(" ) row_ ) where rownum_ <= ? and rownum_ > ?");
			setPageParameter("__offsetEnd", offset + limit, Integer.class);
			setPageParameter(offsetName, offset, Integer.class);
		} else {
			pagingSelect.append(" ) where rownum <= ?");
			setPageParameter(limitName, limit, Integer.class);
		}

		if (isForUpdate) {
			pagingSelect.append(" for update");
		}

		return pagingSelect.toString();
	}

}
