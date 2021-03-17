package com.njusc.npm.service;

import com.njusc.npm.metadata.entity.*;
import com.njusc.base.bean.BaseService;

import java.util.List;
import java.util.Map;


/**
 * 岗位信息
 *
 * @author Michael
 * @date 2021-01-18 10:52:41
 */
public interface TPostManageService extends BaseService<TPostEntity,String> {

    public List<TPostEntity> findList(Map<String, Object> params);

    public List<TDeptEntity> findDeptList();

    public List<TBasecodeEntity> findPostTypeList();

    public List<TPostEntity> verifyPostName(TPostEntity postManageEntity);

    /**
     * 根据岗位Id删除岗位
     * @param id
     */
    public void deleteByPostId(String id);


    List<TPostEntity> findPosts();
}

