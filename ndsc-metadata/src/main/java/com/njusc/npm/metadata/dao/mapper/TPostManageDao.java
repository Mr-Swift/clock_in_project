package com.njusc.npm.metadata.dao.mapper;

import com.njusc.npm.metadata.entity.TBasecodeEntity;
import com.njusc.npm.metadata.entity.TDeptEntity;
import com.njusc.npm.metadata.entity.TPostEntity;
import com.njusc.npm.metadata.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 岗位信息
 * 
 * @author Michael
 * @date 2021-01-18 10:52:41
 */
@Repository
public interface TPostManageDao extends BaseDao<TPostEntity,String> {

    public List<TPostEntity> getPosts(Map<String, Object> params);

    public List<TDeptEntity> getDepts();

    public List<TBasecodeEntity> getPostTypes();

    /**
     * 岗位名称查询
     * @param postManageEntity
     * @return
     */
    public List<TPostEntity> verifyPostName(TPostEntity postManageEntity);

    /**
     * 根据岗位id删除岗位
     * @param id
     */
    public void deleteByPostId(String id);

    /**
     * 根据部门ID删除岗位
     * @param id
     */
    public void deleteByDeptId(String id);


    List<TPostEntity> findPosts();
}
