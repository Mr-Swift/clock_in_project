package com.njusc.npm.metadata.dao.mapper;

import com.njusc.npm.metadata.entity.TBasecodeEntity;
import com.njusc.npm.metadata.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 字典表
 * 
 * @author Michael
 * @date 2021-01-18 10:52:41
 */
@Repository
public interface TBasecodeDao extends BaseDao<TBasecodeEntity,String> {
    List<TBasecodeEntity> findEducations();
    List<TBasecodeEntity> findMarriages();


}
