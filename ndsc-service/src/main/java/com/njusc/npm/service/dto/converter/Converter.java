/**
 * 
 */
package com.njusc.npm.service.dto.converter;

import java.io.Serializable;
import java.util.List;

/**
 * @author jinzf
 * @date Jun 12, 2014
 * @description 把目标T转换成结果R
 * @version 1.0
 */
public interface Converter {
	<R extends Serializable> R converter(Object t,Class<R> clzR) throws Exception;

	 public <R extends Serializable,T extends Object> List<R> converterList(List<T> ts,
	            Class<R> clzR) throws Exception;
}