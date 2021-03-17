package com.njusc.npm.metadata.dao.mapper;

import com.njusc.npm.metadata.dao.BaseDao;
import com.njusc.npm.metadata.entity.TRoleFunctionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色和菜单关系信息
 * 
 * @author Michael
 * @date 2021-01-18 10:52:40
 */
@Repository
public interface TRoleFunctionDao extends BaseDao<TRoleFunctionEntity,String> {

    List<TRoleFunctionEntity> getRoleFunctionByRoleId(Map params);

    void deletFunction(String roleId);
}
