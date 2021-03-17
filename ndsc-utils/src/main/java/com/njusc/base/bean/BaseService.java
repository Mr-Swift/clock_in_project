package com.njusc.base.bean;

import java.util.List;
import java.util.Map;

/**
 * service基类
 * @author Michael
 * @since 2020-2-5
 */
public interface BaseService<T, ID> {
    /**
     * 保存对象
     *
     * @param t 需保存的对象
     */
    void save(T t);

    /***
     * 批量保存
     *
     * @param list
     */
    void saveAll(List<T> list);

    /**
     * 根据ID删除对象
     *
     * @param id
     */
    void delete(ID id);

    /**
     * 批量删除
     *
     * @param list
     */
    void deleteAll(List<ID> list);

    /**
     * 单个修改
     *
     * @param t
     */
    void update(T t);

    /**
     * 批量修改
     *
     * @param list
     */
    void updateAll(List<T> list);

    /**
     * 查询单个对象
     *
     * @param params
     * @return
     */
    T find(Map<String, Object> params);


    /**
     * 查询多个对象
     *
     * @param params
     * @return
     */
    List<T> findAll(Map<String, Object> params);



}
