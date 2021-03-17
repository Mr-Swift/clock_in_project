/**
 * FtpPathEnum.java
 */
package com.njusc.npm.utils.enumeration;

/**
 * @author jinzf
 * @date Oct 13, 2014
 * @description TODO
 * @version 1.0
 */
public enum FtpPathEnum {
	Temp("/md5/"), BatchExcel("/batch/");
	public String Path;

	private FtpPathEnum(String path) {
		this.Path = path;
	}
}
