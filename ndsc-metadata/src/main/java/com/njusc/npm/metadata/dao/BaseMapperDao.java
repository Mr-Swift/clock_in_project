// BaseDao
package com.njusc.npm.metadata.dao;

import com.njusc.npm.utils.util.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author jinzf
 * @date 2013年9月23日
 * @description TODO
 * @version 1.0
 */
public interface BaseMapperDao<E, PK> extends BaseDao {
	/**
	 * @param e @Options(useGeneratedKeys = true, keyProperty = "id")
	 * @description 详细说明
	 */
	void add(final E e);

	/**
	 * @param e @Options(useGeneratedKeys = true, keyProperty = "id")
	 * @description 详细说明
	 */

	void batchAdd(final List<E> list);

	/**
	 * @param e
	 * @description 详细说明
	 */
	Integer remove(@Param(value = "id") final PK e);

	void batchRemove(final PK[] ids);



	/**
	 * @param id
	 * @return
	 * @throws Exception
	 * @description 详细说明
	 */
	E getEntity(@Param(value = "id") final PK id);

	/**
	 * @return
	 * @description 详细说明
	 */
	List<E> getEntities(Pagination page, @Param(value = "e") final E e);
}

