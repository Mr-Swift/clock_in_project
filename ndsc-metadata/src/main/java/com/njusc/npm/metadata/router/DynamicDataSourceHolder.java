package com.njusc.npm.metadata.router;

/**
 * @author jinzf
 *
 */
public class DynamicDataSourceHolder {

	private static final ThreadLocal<Object> currentHolder = new ThreadLocal<Object>();

	public static void setDataSourceKey(Object key) {
		currentHolder.set(key);
	}
	
	public static Object getCurrentKey() {
		return currentHolder.get();
	}

}
