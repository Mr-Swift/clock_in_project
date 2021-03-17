package com.njusc.npm.utils.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 *  <p>
 * 防止重复提交注解，用于方法上<br/>
 * 在新建页面方法上，设置save为true，此时拦截器会在Session中保存一个token，
 * 同时需要在新建的页面中添加
 * <input type="hidden" name="token" value="${token}">
 * <br/>
 * 保存方法需要验证重复提交的，设置remove为true
 * 此时会在拦截器中验证是否重复提交
 * </p>
 * @author Michael
 * @since 2018-11-22
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {

    boolean save() default false;
    boolean remove() default false;
}
