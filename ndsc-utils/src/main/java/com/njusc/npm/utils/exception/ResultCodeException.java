/**
 * ResultCodeException.java
 */
package com.njusc.npm.utils.exception;

import com.njusc.npm.utils.enumeration.ResultCodeEnum;

/**
 * @author jinzf
 * @date May 9, 2015
 * @description @See ResultCodeEnum
 * @version 1.0
 */
public class ResultCodeException extends Throwable {

    public final ResultCodeEnum r;
    private Object data;

    public ResultCodeException(ResultCodeEnum r) {
        this(r.name(), r);
    }
    
    public ResultCodeException(ResultCodeEnum r, Object data) {
        this(r.name(), r);
    	this.data = data;
    }

    public ResultCodeException(String msg, ResultCodeEnum r) {
        super(msg);
        this.r = r;
    }

    public ResultCodeException(Throwable ex, ResultCodeEnum r) {
        this(ex, r.name(), r);
    }

    public ResultCodeException(Throwable ex, String msg, ResultCodeEnum r) {
        super(msg, ex);
        this.r = r;
    }
    
	public Object getData() {
		return data;
	}
    
}
