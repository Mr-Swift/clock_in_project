/**
 * CacheStoreName.java
 */
package com.njusc.npm.metadata.cache.annotation;

import java.lang.annotation.*;

/**
 * @author jinzf
 * @date May 13, 2015
 * @description TODO
 * @version 1.0
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CacheStoreName {
    String[] cacheNames();
    Class<?> targetClazz();
}
