/**

 * 
 */
package com.njusc.npm.service.impl;

import com.njusc.npm.metadata.dao.BaseMapperDao;
import com.njusc.npm.service.BaseServiceOld;
import com.njusc.npm.utils.util.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public abstract class BaseServiceImpl<E, PK> implements BaseServiceOld<E, PK> {
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	
    protected abstract BaseMapperDao<E, PK> getMapperDao();
    
    /*
     * (non-Javadoc)
     * 
     * @see org.jinzf.basic.service.IBaseService#remove(java.lang.Object)
     */
    @Override
    public int remove(PK id) {
        return this.getMapperDao().remove(id);
    }

    @Override
    public void batchRemove(PK[] ids) {
        this.getMapperDao().batchRemove(ids);
    }
    @Override
    public void batchAdd(List<E> lists) {
        this.getMapperDao().batchAdd(lists);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jinzf.basic.service.IBaseService#getEntities(int, int,
     * java.lang.String, java.lang.String)
     */
    public List<E> getEntities(Pagination result, E e) {
        result.setRows(this.getMapperDao().getEntities(result, e));
        return result.getRows();
    }

    public List<E> getEntities(int page, int rows) {
        Pagination result = new Pagination(page, rows);
        result.setRows(this.getMapperDao().getEntities(result, null));
        return result.getRows();
    }

    /*
         * (non-Javadoc)
         *
         * @see org.jinzf.basic.service.IBaseService#getEntity(java.lang.Object)
         */
    @Override
    public E getEntity(PK id) {
        return this.getMapperDao().getEntity(id);
    }

}
