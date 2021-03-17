package com.njusc.npm.metadata.dao.mapper;

import com.njusc.npm.metadata.dao.BaseDao;
import com.njusc.npm.metadata.entity.TRoleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色表
 * 
 * @author Michael
 * @date 2021-01-18 10:52:40
 */
@Repository
public interface TRoleDao extends BaseDao<TRoleEntity,String> {
    List<TRoleEntity> findUpdate(Map<String,Object> params);
    List<TRoleEntity> findList(Map<String,Object> params);

    public List<TRoleEntity> findRoleList();
}
