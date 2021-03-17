package com.njusc.npm.metadata.dao.mapper;

import com.njusc.npm.metadata.entity.TDeptEntity;
import com.njusc.npm.metadata.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 部门信息
 * 
 * @author Michael
 * @date 2021-01-18 10:52:41
 */
@Repository
public interface TDeptDao extends BaseDao<TDeptEntity,String> {

    List<TDeptEntity> findList(Map<String,Object> params);
    List<Map> findCheckList(Map<String, Object> params);
    List<TDeptEntity> findByDeptName(String deptName);

    /**
     * 下拉列表，查询所有的部门名称
     * @return
     */
    List<TDeptEntity> findDepts();

}
