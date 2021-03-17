package com.njusc.npm.metadata.dao.mapper;

import com.njusc.npm.metadata.entity.TUserPostEntity;
import com.njusc.npm.metadata.dao.BaseDao;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 人员岗位信息
 * 
 * @author Michael
 * @date 2021-01-18 10:52:41
 */
@Repository
public interface TUserPostDao extends BaseDao<TUserPostEntity,String> {

    List<TUserPostEntity> findAll2(Map<String,Object> map);

    void deleteByUserId(String userId);
    /**
     * 根据岗位id删除
     * @param id
     */
   void deleteByPostId(String postManageId);

    void updateByUserId(TUserPostEntity t);
}
