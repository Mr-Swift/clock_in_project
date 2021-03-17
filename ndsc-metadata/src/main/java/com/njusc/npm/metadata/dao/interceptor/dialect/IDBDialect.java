package com.njusc.npm.metadata.dao.interceptor.dialect;

/**
 * @author jinzf
 * @date 2013年9月24日
 * @description 数据库方言接口
 * @version 1.0
 */
public interface IDBDialect {
	public static enum Type {
		MYSQL
	}

	/**
	 * @description 详细说明
	 * @param originalSql
	 * @param rows
	 * @return
	 */
	String getPaginationSQL(final String originalSql, final int page,
			final int size);
}
