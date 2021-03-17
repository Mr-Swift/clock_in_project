package com.njusc.npm.metadata.dao.interceptor.dialect;

/**
 * @author jinzf
 * @date 2013年9月24日
 * @description mysql方言实现
 * @version 1.0
 */
public class MySQLDialect implements IDBDialect {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jinzf.basic.dao.interceptor.IDBDialect#getPaginationSQL(java.lang.
	 * String, int, int)
	 */
	public String getPaginationSQL(String originalSql, int page, int rows) {
		StringBuilder sb = new StringBuilder(originalSql.trim().length() + 16);
		sb.append(originalSql).append(" limit ")
				.append(Math.max((page - 1) * rows, 0)).append(",")
				.append(rows);
		return sb.toString();
	}

}
