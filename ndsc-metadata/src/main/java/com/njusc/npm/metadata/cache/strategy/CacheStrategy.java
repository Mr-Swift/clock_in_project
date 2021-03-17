///**
// * CacheStrategy.java
// */
//package com.njusc.wqlwc.metadata.cache.strategy;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @author jinzf
// * @date May 13, 2015
// * @description TODO
// * @version 1.0
// */
//public interface CacheStrategy {
//	/**
//	 * key自增+1,且永不过期
//	 * @param k  key,默认key对应的初始值为0
//	 * */
//	public boolean incr(String k);
//
//	/**
//	 * key自增+1,并根据需要是否需要设定失效时间， 1天
//	 * @param k  key,默认key对应的初始值为0
//	 * @param timeout 是否设置默认过期时间,true为设置默认时间，默认时间 1天
//	 * */
//	public boolean incr(String k, boolean timeout);
//
//	/**
//	 * key自增+1,并设定失效时间
//	 * @param k  key,默认key对应的初始值为0
//	 * @param timeout  设定key失效时间 ,秒
//	 * */
//	public boolean incr(String k, long timeout);
//
//	/**
//	 * 在原有key上增加v,且永不过期
//	 * @param k  key,默认key对应的初始值为0
//	 * @param v  增加值
//	 * */
//	public boolean incrBy(String k, long v);
//
//	/**
//	 * 在原有key上增加v,并根据需要是否需要设定失效时间， 1天
//	 * @param k  key,默认key对应的初始值为0
//	 * @param v  增加值,
//	 * @param timeout 是否设置默认过期时间,true为设置默认时间，默认时间 1天
//	 * */
//	public boolean incrBy(String k, long v, boolean timeout);
//
//	/**
//	 * 在原有key上增加v,并设定失效时间
//	 * @param k  key,默认key对应的初始值为0
//	 * @param v  增加值
//	 * @param timeout  设定key失效时间,秒
//	 * */
//	public boolean incrBy(String k, long v, long timeout);
//
//	/**
//	 * 根据指定key获取数据值
//	 * @param k key,存储键
//	 * @param clazz 存储对象的类型
//	 * @return 根据类型返回对象，可以使Integer,Long,也可以使UserModel
//	 * */
//	public Object get(String k, Class<?> clazz);
//
//	/**
//	 * 根据指定key删除对应数据
//	 * @param k key,存储键
//	 * @return 返回值
//	 * */
//	public boolean remove(String k);
//
//	/**
//	 * 根据指定key删除对应数据，删除时返回删除的值
//	 * @param k key，存储键
//	 * @param clazz 存储对象的类型
//	 * @return 根据类型返回对象，可以使Integer,Long,也可以使UserModel
//	 * */
//	public Object remove(String k, Class<?> clazz);
//	/**
//	 *
//	 * */
//    public boolean add(String k, Object v);
//    public boolean add(String k, Object v, Long sec, TimeUnit tu);
//
//
//
//}
