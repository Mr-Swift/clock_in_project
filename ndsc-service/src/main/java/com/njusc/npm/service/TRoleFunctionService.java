package com.njusc.npm.service;

import com.njusc.base.bean.BaseService;
import com.njusc.npm.metadata.entity.TRoleFunctionEntity;

import java.util.List;
import java.util.Map;


/**
 * 角色和菜单关系信息
 *
 * @author Michael
 * @date 2021-01-18 10:52:40
 */
public interface TRoleFunctionService extends BaseService<TRoleFunctionEntity,String> {
    List<TRoleFunctionEntity> getRoleFunctionByRoleId(Map params);
    void deletFunction(String roleId);
}

