package com.njusc.npm.service;

import com.njusc.base.bean.BaseService;
import com.njusc.npm.metadata.entity.TRoleEntity;

import java.util.List;
import java.util.Map;


/**
 * 角色表
 *
 * @author Michael
 * @date 2021-01-18 10:52:40
 */
public interface TRoleService extends BaseService<TRoleEntity,String> {
    List<TRoleEntity> findUpdate(Map<String,Object> params);

    List<TRoleEntity> findList(Map<String,Object> params);

    public List<TRoleEntity> findRoleList();

    void saveOrUpdate(TRoleEntity t, String funId,String user);

    void delRole(String id);

    void saveUpdate(TRoleEntity t, String funId,String user, String oldName);

}

