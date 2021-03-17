/**
 * BaseServiceOld.java
 */
package com.njusc.npm.service;

import com.njusc.npm.utils.exception.ResultCodeException;
import com.njusc.npm.utils.util.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author jinzf
 * @date Apr 12, 2015
 * @description TODO
 * @version 1.0
 */
public interface BaseServiceOld<E, PK> {

    /**
     * @description TODO
     * @param e
     */
    PK save(E e) throws ResultCodeException;

    /**
     * @description TODO
     * @param e
     * @return
     */
    int remove(@Param(value = "id") final PK e);

    /**
     * @description TODO
     * @param ids
     */
    void batchRemove(final PK[] ids);
    /**
     * @description TODO
     * @param ids
     */
    void batchAdd(final List<E> lists);
    /**
     * @description 详细说明
     * @param id
     * @return
     * @throws Exception
     */
    E getEntity(@Param(value = "id") final PK id);

    /**
     * @description 详细说明
     * @return
     */
    List<E> getEntities(Pagination page, @Param(value = "e") final E e);

}
