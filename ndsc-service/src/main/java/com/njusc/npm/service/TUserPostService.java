package com.njusc.npm.service;

import com.njusc.npm.metadata.entity.TUserPostEntity;
import com.njusc.base.bean.BaseService;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;


/**
 * 人员岗位信息
 *
 * @author Michael
 * @date 2021-01-18 10:52:41
 */
public interface TUserPostService extends BaseService<TUserPostEntity,String> {

    List<TUserPostEntity> findAll2(Map<String,Object> map);

    void deleteByUserId(String userId);

    void updateByUserId(TUserPostEntity t);

    void deleteByPostId(String postManageId);
}

