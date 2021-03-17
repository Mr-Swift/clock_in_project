///**
// * RedisCacheStrategy.java
// */
//package com.njusc.wqlwc.metadata.cache.strategy;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.serializer.JSONSerializer;
//import com.alibaba.fastjson.serializer.SerializeWriter;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.dao.DataAccessException;
//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @author jinzf
// * @date May 14, 2015
// * @description TODO
// * @version 1.0
// */
//public class RedisCacheStrategy implements CacheStrategy {
//
//    protected RedisTemplate<String, String> redisClient;
//    public final long defaultSec = TimeUnit.DAYS.toSeconds(1);
//
//    public RedisCacheStrategy(RedisTemplate<String, String> redisClient) {
//        this.redisClient = redisClient;
//    }
//
//    @Override
//    public boolean add(final String k, final Object v) {
//        if (StringUtils.isEmpty(k) || v == null) {
//            return false;
//        }
//        this.redisClient.execute(new RedisCallback<Object>() {
//            @Override
//            public Object doInRedis(RedisConnection connection)
//                    throws DataAccessException {
//                SerializeWriter out = new SerializeWriter();
//                JSONSerializer serializer = new JSONSerializer(out);
//                serializer.write(v);
//                connection.set(k.getBytes(), out.toBytes("utf-8"));
//                connection.expire(k.getBytes(), defaultSec);
//                return true;
//            }
//        });
//        return true;
//    }
//
//    @Override
//    public boolean add(final String k, final Object v, final Long t,
//            final TimeUnit tu) {
//        if (StringUtils.isEmpty(k) || v == null) {
//            return false;
//        }
//        this.redisClient.execute(new RedisCallback<Object>() {
//            @Override
//            public Object doInRedis(RedisConnection connection)
//                    throws DataAccessException {
//                SerializeWriter out = new SerializeWriter();
//                JSONSerializer serializer = new JSONSerializer(out);
//                serializer.write(v);
//                connection.set(k.getBytes(), out.toBytes("utf-8"));
//                connection.expire(k.getBytes(), tu.toSeconds(t));
//                return true;
//            }
//        });
//        return true;
//    }
//
//    @Override
//    public Object remove(final String k, final Class<?> clazz) {
//        if (StringUtils.isEmpty(k)) {
//            return null;
//        }
//        Object o = this.redisClient.execute(new RedisCallback<Object>() {
//            @Override
//            public Object doInRedis(RedisConnection connection)
//                    throws DataAccessException {
//                byte[] out = connection.get(k.getBytes());
//                if (out == null) {
//                    return null;
//                }
//                return JSONObject.parseObject(out, clazz);
//            }
//        });
//        this.redisClient.execute(new RedisCallback<Long>() {
//            @Override
//            public Long doInRedis(RedisConnection connection)
//                    throws DataAccessException {
//                long v =connection.del(k.getBytes());
//                connection.expire(k.getBytes(), 0);
//                return v;
//            }
//        });
//        return o;
//    }
//
//    @Override
//    public Object get(final String k, final Class<?> clazz) {
//        if (StringUtils.isEmpty(k)) {
//            return null;
//        }
//        Object o = this.redisClient.execute(new RedisCallback<Object>() {
//            @Override
//            public Object doInRedis(RedisConnection connection)
//                    throws DataAccessException {
//                byte[] out = connection.get(k.getBytes());
//                if (out == null) {
//                    return null;
//                }
//                return JSON.parseObject(out, clazz);
//            }
//        });
//        return o;
//    }
//
//    @Override
//    public boolean remove(final String k) {
//        if (StringUtils.isEmpty(k)) {
//            return false;
//        }
//        Long v = this.redisClient.execute(new RedisCallback<Long>() {
//            @Override
//            public Long doInRedis(RedisConnection connection)
//                    throws DataAccessException {
//                long v =connection.del(k.getBytes());
//                connection.expire(k.getBytes(), 0);
//                return v;
//            }
//        });
//        if(v!=null && v.intValue()==1) {
//            return true;
//        }
//        return false;
//    }
//
//	@Override
//	public boolean incr(final String k) {
//		if (StringUtils.isEmpty(k)){
//			return false;
//		}
////      Long value = this.redisClient.execute(new RedisCallback<Long>() {
//        this.redisClient.execute(new RedisCallback<Long>() {
//            @Override
//            public Long doInRedis(RedisConnection connection)
//                    throws DataAccessException {
//            	long v = connection.incr(k.getBytes());
//                return v;
//            }
//        });
//        return true;
//	}
//
//	@Override
//	public boolean incr(String k, boolean timeout) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean incr(String k, long timeout) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean incrBy(String k, long v) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean incrBy(String k, long v, boolean timeout) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean incrBy(String k, long v, long timeout) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//}
