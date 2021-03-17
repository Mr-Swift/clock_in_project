//package com.njusc.wqlwc.metadata.cache.strategy;
//
//import com.alibaba.fastjson.JSON;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import redis.clients.jedis.ShardedJedis;
//import redis.clients.jedis.ShardedJedisSentinelPool;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author jinzf
// * @date May 14, 2015
// * @description redis缓存实现
// * @version 1.0
// */
//
//public class RedisClientImpl implements RedisClient {
//	protected Logger logger = LoggerFactory.getLogger(RedisClientImpl.class);
//    public final long defaultSec = TimeUnit.DAYS.toSeconds(1);
//
//    private ShardedJedisSentinelPool shardedJedisSentinelPool;
//
//    public RedisClientImpl(ShardedJedisSentinelPool shardedJedisSentinelPool) {
//        this.shardedJedisSentinelPool = shardedJedisSentinelPool;
//    }
//
//    private void returnBrokenResource(ShardedJedis shardedJedis) {
//        try {
//        	shardedJedisSentinelPool.returnBrokenResource(shardedJedis);
//        } catch (Exception e) {
//            logger.error("returnBrokenResource error.", e);
//        }
//    }
//    private void returnResource(ShardedJedis shardedJedis) {
//        try {
//            shardedJedisSentinelPool.returnResource(shardedJedis);
//        } catch (Exception e) {
//            logger.error("returnResource error.", e);
//        }
//    }
//    /**
//     * 验证key的字符串格式
//     * @param k  key
//     * @return true：格式正确     false：格式不正确
//     * */
//    public boolean getRegKey(String k){
//    	if (StringUtils.isEmpty(k)){
//			return false;
//		}
//    	return true;
//    }
//	@Override
//	public boolean exits(String k) {
//		if(!getRegKey(k)){
//			return false;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//         	return shardedJedis.exists(k);
//        } catch (Exception ex) {
//            logger.error("exits key[{}]  error.",k, ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//		return false;
//	}
//
//	@Override
//	public long del(String k) {
//		if(!getRegKey(k)){
//			return 0;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//         	return shardedJedis.del(k);
//        } catch (Exception ex) {
//            logger.error("del key[{}]  error.",k, ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//		return 0;
//	}
//
//	@Override
//	public String type(String k) {
//		if(!getRegKey(k)){
//			return "none";
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//         	return shardedJedis.type(k);
//        } catch (Exception ex) {
//        	logger.error("type key[{}]  error.",k, ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//		return "none";
//	}
//
//	@Override
//	public long expire(String k,int seconds) {
//		if(!getRegKey(k)){
//			return 0;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//         	return shardedJedis.expire(k, seconds);
//        } catch (Exception ex) {
//            logger.error("expire key[{}] seconds[{}]  error.",k,seconds, ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//		return 0;
//	}
//
//	@Override
//	public long ttl(String k) {
//		if(!getRegKey(k)){
//			return -2;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//         	return shardedJedis.ttl(k);
//        } catch (Exception ex) {
//        	logger.error("ttl key[{}]  error.",k, ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//		return -2;
//	}
//
//	@Override
//	public int set(String k,String v,int seconds) {
//		if(!getRegKey(k)){
//			return 0;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            shardedJedis.set(k, v);
//            if(seconds>=0){
//            	shardedJedis.expire(k, seconds);
//            }
//         	return 1;
//        } catch (Exception ex) {
//        	logger.error("set key[{}] value[{}] seconds[{}] error.",k,v,seconds, ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//		return 0;
//	}
//
//	@Override
//	public long setnx(String k,String v) {
//		if(!getRegKey(k)){
//			return -1;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//         	return shardedJedis.setnx(k, v);
//        } catch (Exception ex) {
//        	logger.error("setnx key[{}] value[{}] error.",k,v, ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//		return -1;
//	}
//
//	@Override
//	public String get(String k) {
//		if(!getRegKey(k)){
//			return null;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//         	return shardedJedis.get(k);
//        } catch (Exception ex) {
//        	logger.error("get key[{}] error.",k, ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//		return null;
//	}
//
//	@Override
//	public String getset(String k, String v) {
//		if(!getRegKey(k)){
//			return null;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//         	return shardedJedis.getSet(k, v);
//        } catch (Exception ex) {
//        	logger.error("getset key[{}] value[{}] error.",k,v, ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return null;
//	}
//
//	@Override
//	public String incr(String k) {
//		if(!getRegKey(k)){
//			return null;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            long l = shardedJedis.incr(k);
//         	return l+"";
//        } catch (Exception ex) {
//        	logger.error("incr key[{}] error.",k, ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return null;
//	}
//
//	@Override
//	public String incrby(String k,int v) {
//		if(!getRegKey(k)){
//			return null;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            long l = shardedJedis.incrBy(k, v);
//         	return l+"";
//        } catch (Exception ex) {
//        	logger.error("incrby key[{}] value[{}] error.",k,v, ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return null;
//	}
//
//	@Override
//	public String decr(String k) {
//		if(!getRegKey(k)){
//			return null;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            long l = shardedJedis.decr(k);
//         	return l+"";
//        } catch (Exception ex) {
//        	logger.error("decr key[{}] error.",k, ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return null;
//	}
//
//	@Override
//	public long sethset(String k,String f,String v,int seconds) {
//		if(!getRegKey(k)){
//			return 0;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            shardedJedis.hset(k, f, v);
//            if(seconds>=0){
//            	shardedJedis.expire(k, seconds);
//            }
//         	return 1;
//        } catch (Exception ex) {
//        	logger.error("hset key[{}] field[{}] value[{}] seconds[{}] error.",k,f,v,seconds, ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return 0;
//	}
//	@Override
//	public String gethset(String k,String f) {
//		if(!getRegKey(k)){
//			return null;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.hget(k, f);
//        } catch (Exception ex) {
//        	logger.error("hget key[{}] field[{}] error.",k,f, ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return null;
//	}
//	@Override
//	public List<String> gethmset(String k,String... f) {
//		if(!getRegKey(k)){
//			return null;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.hmget(k, f);
//        } catch (Exception ex) {
//        	logger.error("hmget key[{}] field[{}] error.",k,f, ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return null;
//	}
//
//	@Override
//	public String sethmset(String k,Map<String,String> f) {
//		if(!getRegKey(k)){
//			return null;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.hmset(k, f);
//        } catch (Exception ex) {
//        	logger.error("hmset key[{}] field value[{}] error.",k,f, ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return null;
//	}
//
//	@Override
//	public String hincrby(String k,String f,long v) {
//		if(!getRegKey(k)){
//			return null;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            long l = shardedJedis.hincrBy(k, f, v);
//            return l+"";
//        } catch (Exception ex) {
//        	logger.error("hincrby key[{}] field[{}] value[{}] error.",k,f,v, ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return null;
//	}
//
//	@Override
//	public boolean hexists(String k,String f) {
//		if(!getRegKey(k)){
//			return false;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.hexists(k, f);
//        } catch (Exception ex) {
//        	logger.error("hexists key[{}] field[{}] error.",k,f,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return false;
//	}
//
//	@Override
//	public long hdel(String k,String... f) {
//		if(!getRegKey(k)){
//			return -1;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.hdel(k, f);
//        } catch (Exception ex) {
//        	logger.error("hdel key[{}] field[{}] error.",k,f,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return -1;
//	}
//
//	@Override
//	public long hlen(String k) {
//		if(!getRegKey(k)){
//			return -1;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.hlen(k);
//        } catch (Exception ex) {
//        	logger.error("hlen key[{}] error.",k,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return -1;
//	}
//
//	@Override
//	public Set<String> hkeys(String k) {
//		if(!getRegKey(k)){
//			return null;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.hkeys(k);
//        } catch (Exception ex) {
//        	logger.error("hkeys key[{}] error.",k,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return null;
//	}
//
//	@Override
//	public List<String> hvals(String k) {
//		if(!getRegKey(k)){
//			return null;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.hvals(k);
//        } catch (Exception ex) {
//        	logger.error("hvals key[{}] error.",k,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return null;
//	}
//
//	@Override
//	public Map<String,String> hgetAll(String k) {
//		if(!getRegKey(k)){
//			return null;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.hgetAll(k);
//        } catch (Exception ex) {
//        	logger.error("hgetAll key[{}] error.",k,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return null;
//	}
//
//	@Override
//	public long lpush(String k,String... v) {
//		if(!getRegKey(k)){
//			return -1;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.lpush(k, v);
//        } catch (Exception ex) {
//        	logger.error("lpush key[{}] value[{}] error.",k,v,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return -1;
//	}
//
//	@Override
//	public long rpush(String k,String... v) {
//		if(!getRegKey(k)){
//			return -1;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.rpush(k, v);
//        } catch (Exception ex) {
//        	logger.error("rpush key[{}] value[{}] error.",k,v,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return -1;
//	}
//
//	@Override
//	public long llen(String k) {
//		if(!getRegKey(k)){
//			return -1;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.llen(k);
//        } catch (Exception ex) {
//        	logger.error("llen key[{}] error.",k,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return -1;
//	}
//
//	@Override
//	public List<String> lrange(String k,long start,long end) {
//		if(!getRegKey(k)){
//			return null;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.lrange(k, start, end);
//        } catch (Exception ex) {
//        	logger.error("lrange key[{}] start[{}] end[{}] error.",k,start,end,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return null;
//	}
//
//	@Override
//	public String ltrim(String k,long start,long end) {
//		if(!getRegKey(k)){
//			return null;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.ltrim(k, start, end);
//        } catch (Exception ex) {
//        	logger.error("ltrim key[{}] start[{}] end[{}] error.",k,start,end,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return null;
//	}
//
//	@Override
//	public String lset(String k,long index,String v) {
//		if(!getRegKey(k)){
//			return null;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.lset(k, index, v);
//        } catch (Exception ex) {
//        	logger.error("lset key[{}] index[{}] value[{}] error.",k,index,v,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return null;
//	}
//
//	@Override
//	public long lrem(String k,long count,String v) {
//		if(!getRegKey(k)){
//			return -1;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.lrem(k, count, v);
//        } catch (Exception ex) {
//        	logger.error("lrem key[{}] count[{}] value[{}] error.",k,count,v,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return -1;
//	}
//
//	@Override
//	public String lpop(String k) {
//		if(!getRegKey(k)){
//			return null;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.lpop(k);
//        } catch (Exception ex) {
//        	logger.error("lpop key[{}] error.",k,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return null;
//	}
//
//	@Override
//	public String rpop(String k) {
//		if(!getRegKey(k)){
//			return null;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.rpop(k);
//        } catch (Exception ex) {
//        	logger.error("rpop key[{}] error.",k,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return null;
//	}
//
//	@Override
//	public long sadd(String k,String... v) {
//		if(!getRegKey(k)){
//			return -1;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.sadd(k, v);
//        } catch (Exception ex) {
//        	logger.error("sadd key[{}] value[{}] error.",k,v,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return -1;
//	}
//
//	@Override
//	public long srem(String k,String... v) {
//		if(!getRegKey(k)){
//			return -1;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.srem(k, v);
//        } catch (Exception ex) {
//        	logger.error("srem key[{}] value[{}] error.",k,v,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return -1;
//	}
//
//	@Override
//	public String spop(String k) {
//		if(!getRegKey(k)){
//			return null;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.spop(k);
//        } catch (Exception ex) {
//        	logger.error("spop key[{}] error.",k,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return null;
//	}
//
//	@Override
//	public String srandmember(String k) {
//		if(!getRegKey(k)){
//			return null;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.srandmember(k);
//        } catch (Exception ex) {
//        	logger.error("srandmember key[{}] error.",k,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return null;
//	}
//
//	@Override
//	public long scard(String k) {
//		if(!getRegKey(k)){
//			return -1;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.scard(k);
//        } catch (Exception ex) {
//        	logger.error("scard key[{}] error.",k,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return -1;
//	}
//
//	@Override
//	public boolean sismember(String k,String v) {
//		if(!getRegKey(k)){
//			return false;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.sismember(k, v);
//        } catch (Exception ex) {
//        	logger.error("sismember key[{}] value[{}] error.",k,v,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return false;
//	}
//
//	@Override
//	public Set<String> smembers(String k) {
//		if(!getRegKey(k)){
//			return null;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.smembers(k);
//        } catch (Exception ex) {
//        	logger.error("smembers key[{}] error.",k,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return null;
//	}
//
//	@Override
//	public long zadd(String k,double score,String member) {
//		if(!getRegKey(k)){
//			return -1;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.zadd(k, score, member);
//        } catch (Exception ex) {
//        	logger.error("zadd key[{}] score[{}] member[{}] error.",k,score,member,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return -1;
//	}
//
//	@Override
//	public long zadd(String k,Map<String,Double>scoreMembers) {
//		if(!getRegKey(k)){
//			return -1;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.zadd(k, scoreMembers);
//        } catch (Exception ex) {
//        	logger.error("zadd key[{}] scoreMembers[{}] error.",k,scoreMembers,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return -1;
//	}
//
//	@Override
//	public long zrem(String k,String... members) {
//		if(!getRegKey(k)){
//			return -1;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.zrem(k, members);
//        } catch (Exception ex) {
//        	logger.error("zrem key[{}] members[{}] error.",k,members,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return -1;
//	}
//
//	@Override
//	public double zincrby(String k,double score,String member) {
//		if(!getRegKey(k)){
//			return -1;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.zincrby(k, score, member);
//        } catch (Exception ex) {
//        	logger.error("zincrby key[{}] score[{}] member[{}] error.",k,score,member,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return -1;
//	}
//
//	@Override
//	public long zrank(String k,String member) {
//		if(!getRegKey(k)){
//			return -1;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.zrank(k, member);
//        } catch (Exception ex) {
//        	logger.error("zrank key[{}] member[{}] error.",k,member,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return -1;
//	}
//
//	@Override
//	public long zrevrank(String k,String member) {
//		if(!getRegKey(k)){
//			return -1;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.zrevrank(k, member);
//        } catch (Exception ex) {
//        	logger.error("zrevrank key[{}] member[{}] error.",k,member,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return -1;
//	}
//
//	@Override
//	public Set<String> zrange(String k,long start,long end) {
//		if(!getRegKey(k)){
//			return null;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.zrange(k, start, end);
//        } catch (Exception ex) {
//        	logger.error("zrange key[{}] start[{}] end[{}] error.",k,start,end,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return null;
//	}
//
//	@Override
//	public Set<String> zrevrange(String k,long start,long end) {
//		if(!getRegKey(k)){
//			return null;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.zrevrange(k, start, end);
//        } catch (Exception ex) {
//        	logger.error("zrevrange key[{}] start[{}] end[{}] error.",k,start,end,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return null;
//	}
//
//	@Override
//	public Set<String> zrangeByScore(String k,double start,double end) {
//		if(!getRegKey(k)){
//			return null;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.zrangeByScore(k, start, end);
//        } catch (Exception ex) {
//        	logger.error("zrangeByScore key[{}] start[{}] end[{}] error.",k,start,end,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return null;
//	}
//
//	@Override
//	public long zcount(String k,double min,double max) {
//		if(!getRegKey(k)){
//			return -1;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.zcount(k, min, max);
//        } catch (Exception ex) {
//        	logger.error("zcount key[{}] min[{}] max[{}] error.",k,min,max,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return -1;
//	}
//
//	@Override
//	public long zcard(String k) {
//		if(!getRegKey(k)){
//			return -1;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.zcard(k);
//        } catch (Exception ex) {
//        	logger.error("zcard key[{}] error.",k,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return -1;
//	}
//
//	@Override
//	public String zscore(String k,String member) {
//		if(!getRegKey(k)){
//			return null;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.zscore(k,member)+"";
//        } catch (Exception ex) {
//        	logger.error("zscore key[{}] member[{}] error.",k,member,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return null;
//	}
//
//	@Override
//	public long zremrangeByRank(String k,long start,long end) {
//		if(!getRegKey(k)){
//			return -1;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.zremrangeByRank(k, start, end);
//        } catch (Exception ex) {
//        	logger.error("zremrangeByRank key[{}] start[{}] end[{}] error.",k,start,end,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return -1;
//	}
//
//	@Override
//	public long zremrangeByScore(String k,double start,double end) {
//		if(!getRegKey(k)){
//			return -1;
//		}
//		ShardedJedis shardedJedis = null;
//        try {
//            shardedJedis = shardedJedisSentinelPool.getResource();
//            return shardedJedis.zremrangeByScore(k, start, end);
//        } catch (Exception ex) {
//        	logger.error("zremrangeByScore key[{}] start[{}] end[{}] error.",k,start,end,ex);
//            returnBrokenResource(shardedJedis);
//        } finally {
//            returnResource(shardedJedis);
//        }
//        return -1;
//	}
//
//	 /**
//     * 根据key 获取对象
//     * @param key
//     * @return
//     */
//	@Override
//    public  <T> T getObject(String k,Class<T> clazz){
//    	if(!getRegKey(k)){
//			return null;
//		}
//		ShardedJedis shardedJedis = null;
//
//        try {
//        	shardedJedis = shardedJedisSentinelPool.getResource();
//            String value = shardedJedis.get(k);
//            return JSON.parseObject(value, clazz);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }finally{
//        	shardedJedisSentinelPool.returnResource(shardedJedis);
//        }
//    }
//}
