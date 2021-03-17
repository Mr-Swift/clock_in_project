/**
 * CacheOperate.java
 */
package com.njusc.npm.metadata.cache.annotation;

import java.lang.annotation.*;

/**
 * @author jinzf
 * @date May 13, 2015
 * @description TODO
 * @version 1.0
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CacheOperate {

    public enum OperateType{
        添加至缓存,清除缓存,添加至缓存列表,清除缓存列表;
    }
    int cacheId();
    OperateType Operate();
}
