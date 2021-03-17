//package com.njusc.wqlwc.metadata.cache.strategy;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//
///**
// * @author jinzf
// * @date May 13, 2015
// * @description redis缓存接口
// * @version 1.0
// */
//public interface RedisClient {
//
//	/**key************************************************/
//	/**
//	 * 检测key是否存在
//	 * @param k  key
//	 * @return true：存在  false：不存在、或key格式错误
//	 * */
//	public boolean exits(String k);
//
//	/**
//	 * 删除指定key
//	 * @param k  key
//	 * @return 大于0：已删除数目  0：不存在、或key格式错误
//	 * */
//	public long del(String k);
//
//	/**
//	 * 删除指定key
//	 * @param k  key
//	 * @return 返回 none： 表示 key不存在、或key格式错误    否则：string字符类型，list 链表类型set 无序集合类型...
//	 * */
//	public String type(String k);
//
//	/**
//	 * 指定key多少秒后失效
//	 * @param k  key
//	 * @param seconds 秒
//	 * @return 返回 0： 表示 key不存在、或key格式错误
//	 * */
//	public long expire(String k, int seconds);
//
//	/**
//     * 获取key的剩余过期秒数
//     * @param k key
//     * @return -2:key不存在、或key格式错误    -1：未设置过期时间
//     */
//    public long ttl(String k);
//
//	/**int string*********************************************/
//    /**
//     * 设置 key对应 string 类型的值，
//     * @param k key
//     * @param v value值
//     * @param seconds 大于等于0：多少秒内有效； 其余数值标识永久有效,如果设置为0，则key立即失效，即ttl该key时返回-2
//     * @return 0:key格式错误或程序出错    1:设置成功
//     */
//    public int set(String k, String v, int seconds);
//
//    /**
//     * 设置 key对应 string值,如果key不存在则设置新值，如果key存在则不设置，
//     * @param k key
//     * @param v value值
//     * @return -1:key格式错误或程序出错    0:key存在不设置新值，1：key不存在但设置了新值
//     */
//    public long setnx(String k, String v);
//
//    /**
//     * 根据key获取string值，
//     * @param k key
//     * @return null:key格式错误或程序出错 、key不存在，其他：value值
//     */
//    public String get(String k);
//
//    /**
//     * 先获取 key的值，再设置 key的值，无论key是否存在，都为key添加新值value
//     * @param k key
//     * @param v value值
//     * @return null:key格式错误或程序出错 、设置前key不存在，其他：旧value值
//     */
//    public String getset(String k, String v);
//
//    /**
//     * 设置key自增，key不存在时则设置key为1
//     * @param k key
//     * @return null:key格式错误或程序出错  否则：获取执行后的返回值，即+1后的值
//     */
//    public String incr(String k);
//
//    /**
//     * 设置key对应的value每次增加（减少）v值，key不存在时，则认为原先key为0
//     * @param k key
//     * @param v value值
//     * @return null:key格式错误或程序出错  否则：获取执行后的返回值，即+v后的值
//     */
//    public String incrby(String k, int v);
//
//    /**
//     * 设置key自减，key不存在时则设置key为-1
//     * @param k key
//     * @return null:key格式错误或程序出错  否则：获取执行后的返回值，及-1后的值
//     */
//    public String decr(String k);
//
//	/**hash***********************************************/
//    /**
//     * 设置 key对应 hash field数据的值，其中 seconds>=0需慎用，当seconds>=0时，会给该key下的所有hash field设置相同的失效时间
//     * @param k key
//     * @param f field
//     * @param v value值
//     * @param seconds 大于等于0：多少秒内有效； 其余数值标识永久有效,如果设置为0，则key立即失效，即ttl该key时返回-2
//     * @return 0:key格式错误或程序出错    1:设置成功
//     */
//    public long sethset(String k, String f, String v, int seconds);
//
//    /**
//     * 获取 key指定 hash field数据的值，
//     * @param k key
//     * @param f field
//     * @return 0:key格式错误或程序出错    其余成功获取值
//     */
//    public String gethset(String k, String f);
//
//    /**
//     * 获取 某一key指定的 hash field数据的值集合，
//     * @param k key
//     * @param f field,多个field参数
//     * @return null:key格式错误或程序出错    返回数组则获取对应field的数据值，组成集合，例如[ASDADASD4, ASDADASD5, null, null, null]，找不到对应field则值为null
//     */
//    public List<String> gethmset(String k, String... f);
//
//    /**
//     * 获取 某一key指定的 hash field数据的值集合，
//     * @param k key
//     * @param f map集合，由field，value组成,只要有一个value为null，则该操作不执行
//     * @return null:key格式错误或程序出错  ，ok：执行成功
//     */
//    public String sethmset(String k, Map<String, String> f);
//
//    /**
//     * 为某一key指定的 hash field增加v值，
//     * @param k key
//     * @param f field
//     * @param v value增加值
//     * @return null:key格式错误或程序出错  ，其他返回增加后的hash field对应的值
//     */
//    public String hincrby(String k, String f, long v);
//
//    /**
//     * 查找key指定的 hash field是否存在
//     * @param k key
//     * @param f field
//     * @return true：存在  false：不存在、key格式错误或程序出错
//     */
//    public boolean hexists(String k, String f);
//
//    /**
//     * 删除key的hash field，当key中所有的hash field全部删除掉后，key也一并移除
//     * @param k key
//     * @param f field，多个field参数
//     * @return -1：key格式错误或程序出错   0：删除0个数据    >0：删除field个数
//     */
//    public long hdel(String k, String... f);
//
//    /**
//     * key中hash field 数量
//     * @param k key
//     * @return -1：key格式错误或程序出错   0：key不存在    >0：field 数量
//     */
//    public long hlen(String k);
//
//    /**
//     * 获取key中所有hash field
//     * @param k key
//     * @return null：key格式错误或程序出错     否则获取所有的field，如果key不存在，则返回的set不为空，但数量为0
//     */
//    public Set<String> hkeys(String k);
//
//    /**
//     * 获取key中所有value
//     * @param k key
//     * @return null：key格式错误或程序出错     否则获取所有的value，如果key不存在，则返回的list不为空，但数量为0
//     */
//    public List<String> hvals(String k);
//
//    /**
//     * 获取key中所有hash field和value键值对
//     * @param k key
//     * @return null：key格式错误或程序出错     否则获取所有的filed和value，如果key不存在，则返回的map不为空，但数量为0
//     */
//    public Map<String,String> hgetAll(String k);
//	/**list***********************************************/
//    /**
//     * 在 key 对应 list 的头部添加字符串元素，
//     * @param k key
//     * @param v value，多个field参数
//     * @return -1：key格式错误或程序出错或value数据类型与存在的key对应的value值不一致   返回>1数字表示 数据放入list后，list的size
//     */
//    public long lpush(String k, String... v);
//
//    /**
//     * 在 key 对应 list 的尾部添加字符串元素，
//     * @param k key
//     * @param v value，多个field参数
//     * @return -1：key格式错误或程序出错或value数据类型与存在的key对应的value值不一致   返回>1数字表示 数据放入list后，list的size
//     */
//    public long rpush(String k, String... v);
//
//    /**
//     * 在 key 对应 list的长度
//     * @param k key
//     * @return -1：key格式错误或程序出错    其他返回list长度   key不存在则返回0
//     */
//    public long llen(String k);
//
//    /**
//     * 从头部开始 返回指定区间内的元素,下标从 0 开始，负值表示从后面计算，-1表示倒数第一个元素，key不存在返回空列表，下标范围不在区间内，也返回空列表
//     * @param k key
//     * @param start 开始下标,包含
//     * @param end 结束下标，包含
//     * @return null：key格式错误或程序出错    否则返回列表
//     */
//    public List<String> lrange(String k, long start, long end);
//
//    /**
//     * 从头部开始 截取 list指定区间内元素,并删除缓存中未截取到的元素，仅保留截取的元素,key存在但截取不到值时则会删除key
//     * @param k key
//     * @param start 开始下标,包含
//     * @param end 结束下标，包含
//     * @return null：key格式错误或程序出错     OK：截取成功并删除其余元素，仅保留截取到的元素
//     */
//    public String ltrim(String k, long start, long end);
//
//    /**
//     * 设置key指定下标的元素值
//     * @param k key
//     * @param index 开始下标,包含
//     * @param v 结束下标，包含
//     * @return null：key格式错误或程序出错、或key不存在时、或超出key下标时     OK：设置成功
//     */
//    public String lset(String k, long index, String v);
//
//    /**
//     * 正数从头部，负数从尾部开始，从count开始，删除与value相等的元素
//     * @param k key
//     * @param count 开始下标,包含
//     * @param v 结束下标，包含
//     * @return -1：key格式错误或程序出错、     N：删除0条， 删除N条，key不存在或超出key下标返回0
//     */
//    public long lrem(String k, long count, String v);
//
//    /**
//     * 从list的头部删除并返回删除元素。每次删除一个
//     * 如果key不存在或key对应list不存在或者是空，返回null，
//     * 如果key 对应值不是list 错误并返回null
//     * @param k key
//     * @return null：key格式错误或程序出错、key不存在或key对应list不存在或者是空     其他：返回删除值
//     */
//    public String lpop(String k);
//
//    /**
//     * 从list的尾部删除并返回删除元素。每次删除一个
//     * 如果key不存在或key对应list不存在或者是空，返回null，
//     * 如果key 对应值不是list 错误并返回null
//     * @param k key
//     * @return null：key格式错误或程序出错、key不存在或key对应list不存在或者是空     其他：返回删除值
//     */
//    public String rpop(String k);
//
//	/**set************************************************/
//
//    /**
//     * 添加一个成员v到key对应的set集合中，如果成员v在set集合中已存在，则该v不重复插入,但不影响其余成员的添加
//     * @param k key
//     * @param v 添加一个成员v到key对应的set集合中
//     * @return -1：key格式错误或程序出错、     0、1...N：本次实际成功添加到set集合中的数量
//     */
//    public long sadd(String k, String... v);
//
//    /**
//     * 从key对应的set集合中，移除一个成员v
//     * @param k key
//     * @param v 从key对应的set集合中，移除一个成员v
//     * @return -1：key格式错误或程序出错、     1...N：本次实际移除set集合中的数量 。    0：表示移除的数据为0，可能是key本身不存在、也可能是key对应的set集合中的v不存在
//     */
//    public long srem(String k, String... v);
//
//    /**
//     * 随机删除key对应set集合中的一个元素，并返回被删除 的值
//     * @param k key
//     * @return null：key格式错误或程序出错、key不存在或者key对应的集合为空     其他: 返回移除的值
//     */
//    public String spop(String k);
//
//    /**
//     * 随机取key对应set集合中的一个元素，并返回，不从set集合中移除元素
//     * @param k key
//     * @return null：key格式错误或程序出错、key不存在或者key对应的集合为空     其他: 返回随机值
//     */
//    public String srandmember(String k);
//
//    /**
//     * 返回set集合的元素个数，set为空或key不存在，则返回0
//     * @param k key
//     * @return -1：key格式错误或程序出错  0:key不存在或者key对应的集合为空     其他: set集合的元素个数
//     */
//    public long scard(String k);
//
//    /**
//     * 判断成员v是否存在set集合中
//     * @param k key
//     * @param v 成员v
//     * @return false：key格式错误或程序出错、可能是key本身不存在、也可能是key对应的set集合中的v不存在  true：存在
//     */
//    public boolean sismember(String k, String v);
//
//    /**
//     * 返回set集合中的所有元素，无序的
//     * @param k key
//     * @return null：key格式错误或程序出错         其他: set集合的无序元素, key不存在或者key对应的集合为空时，返回的set集合，元素个数为0
//     */
//    public Set<String> smembers(String k);
//
//	/**sortedset******************************************/
//
//    /**
//     * 添加有序元素到set集合中
//     * @param k key
//     * @param score  分数
//     * @param member 成员，相当于小key
//     * @return -1：key格式错误或程序出错          1: 成功添加元素的数量,如果元素已存在，那么只修改元素的值，不算添加的数量，则返回0
//     */
//    public long zadd(String k, double score, String member);
//
//    /**
//     * 添加有序元素到set集合中
//     * @param k key
//     * @param scoreMembers  成员、分数的集合
//     * @return -1：key格式错误或程序出错         0、1。。。。N: 成功添加元素的数量,如果元素已存在，则只修改元素的值，不算添加的数量
//     */
//    public long zadd(String k, Map<String, Double> scoreMembers);
//
//    /**
//     * 返回移除元素的个数
//     * @param k key
//     * @param members  成员
//     * @return -1：key格式错误或程序出错         0: key不存在或移除的成员元素不存在   1....N:移除的元素个数
//     */
//    public long zrem(String k, String... members);
//
//    /**
//     * 为集合中指定的成员member添加score,若key或者member不存在，则会添加元素，并默认member初始score为0
//     * @param k key
//     * @param score 添加的分数
//     * @param members  成员
//     * @return -1：key格式错误或程序出错         其他值: 返回添加后的值
//     */
//    public double zincrby(String k, double score, String member);
//
//    /**
//     * 返回指定元素在集合中的下标位置，score 从小到大排序，下标从0开始
//     * @param k key
//     * @param members  成员
//     * @return -1：key格式错误或程序出错、key不存在 、member不存在        其他值: 返回member对应排序的下标
//     */
//    public long zrank(String k, String member);
//
//    /**
//     * 返回指定元素在集合中的下标位置，score 从大到小排序，下标从0开始
//     * @param k key
//     * @param members  成员
//     * @return -1：key格式错误或程序出错、key不存在 、member不存在        其他值: 返回member对应排序的下标
//     */
//    public long zrevrank(String k, String member);
//
//    /**
//     * 返回下标区间内的所有元素,score 从小到大排序,有序集合
//     * @param k key
//     * @param start 下标最小值
//     * @param end 下标最大值
//     * @return null：key格式错误或程序出错  其他: 返回下标区间的member集合
//     */
//    public Set<String> zrange(String k, long start, long end);
//
//    /**
//     * 返回下标区间内的所有元素,score 从大到小排序,有序集合
//     * @param k key
//     * @param start 下标最小值
//     * @param end 下标最大值
//     * @return null：key格式错误或程序出错  其他: 返回下标区间的member集合
//     */
//    public Set<String> zrevrange(String k, long start, long end);
//
//    /**
//     * 返回score区间内的所有元素,有序集合
//     * @param k key
//     * @param start score的最小值
//     * @param end score的最大值
//     * @return null：key格式错误或程序出错  其他: 返回score在min和max区间的member集合
//     */
//    public Set<String> zrangeByScore(String k, double start, double end);
//
//    /**
//     * 返回score区间内元素个数
//     * @param k key
//     * @param min score的最小值
//     * @param max score的最大值
//     * @return -1：key格式错误或程序出错  0：key不存在 、member不存在        1。。。。N: 返回score在min和max区间的值
//     */
//    public long zcount(String k, double min, double max);
//
//    /**
//     * 返回元素个数
//     * @param k key
//     * @return -1：key格式错误或程序出错  0：key不存在 、member不存在        1。。。。N: 返回元素个数
//     */
//    public long zcard(String k);
//
//    /**
//     * 返回指定元素的score
//     * @param k key
//     * @param members  成员
//     * @return null：key格式错误或程序出错、key不存在 、member不存在        其他值: 返回member对应score
//     */
//    public String zscore(String k, String member);
//
//    /**
//     * 返回删除指定下标区间内元素的数量
//     * @param k key
//     * @param start  下标最小值
//     * @param end 下标最大值
//     * @return -1：key格式错误或程序出错、 0：删除数量
//     */
//    public long zremrangeByRank(String k, long start, long end);
//
//    /**
//     * 返回删除指定score区间内元素的数量
//     * @param k key
//     * @param start  score最小值
//     * @param end score最大值
//     * @return -1：key格式错误或程序出错、 0：删除数量
//     */
//    public long zremrangeByScore(String k, double start, double end);
//
//    /**
//     * 根据key 获取对象
//     * @param key
//     * @return
//     */
//    public  <T> T getObject(String k, Class<T> clazz);
//
//}
