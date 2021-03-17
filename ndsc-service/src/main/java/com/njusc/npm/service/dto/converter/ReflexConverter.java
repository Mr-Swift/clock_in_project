/**
 * ReflexConverter.java
 */
package com.njusc.npm.service.dto.converter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
 * @author jinzf
 * @date Mar 12, 2015
 * @description 反身转换者
 * @version 1.0
 */
public class ReflexConverter implements Converter {

    public <R extends Serializable> R converter(Object t, Class<R> clzR) {
        if (t == null || clzR == null) {
            throw new NullPointerException();
        }
        R o = null;
        try {
            o = clzR.newInstance();
            BeanUtils.copyProperties(t, o);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // R o = null;
        // try {
        // o = clzR.newInstance();
        // Field[] fs = clzR.getFields();
        // for (Field f : fs) {
        // Field tf, rf;
        // try {
        // tf = clzR.getDeclaredField(f.getName());
        // tf.setAccessible(true);
        // Object to = tf.get(t);
        // rf = o.getClass().getDeclaredField(f.getName());
        // rf.set(o, to);
        // } catch (NoSuchFieldException e1) {
        // e1.printStackTrace();
        // } catch (SecurityException e1) {
        // e1.printStackTrace();
        // }
        // }
        // } catch (InstantiationException e1) {
        // e1.printStackTrace();
        // } catch (IllegalAccessException e1) {
        // e1.printStackTrace();
        // }

        return o;
    }

    public <R extends Serializable, T extends Object> List<R> converterList(
            List<T> ts, Class<R> clzR) throws Exception {
        if (ts == null || clzR == null) {
            throw new NullPointerException();
        }
        if (ts.isEmpty()) {
            return Collections.emptyList();
        }

        List<R> result = new ArrayList<R>();
        R o = null;
        for (T e : ts) {
            try {
                o = clzR.newInstance();
                BeanUtils.copyProperties(e, o);
                result.add(o);
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            }
        }
        // R o;
        // Field[] fs;
        // Field tf, rf;
        // for (T e : t) {
        // try {
        // o = clzR.newInstance();
        // fs = clzR.getDeclaredFields();
        // for (Field f : fs) {
        // try {
        // tf = e.getClass().getDeclaredField(f.getName());
        // tf.setAccessible(true);
        // Object to = tf.get(e);
        // rf = o.getClass().getDeclaredField(f.getName());
        // rf.setAccessible(true);
        // rf.set(o, to);
        // } catch (NoSuchFieldException e1) {
        // e1.printStackTrace();
        // } catch (SecurityException e1) {
        // e1.printStackTrace();
        // }
        // }
        // result.add(o);
        // } catch (InstantiationException e1) {
        // e1.printStackTrace();
        // } catch (IllegalAccessException e1) {
        // e1.printStackTrace();
        // }
        // o=null;
        // fs=null;
        // tf=null;
        // rf=null;
        // }

        return result;
    }

}
