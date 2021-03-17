package com.njusc.npm.service;

import com.njusc.npm.metadata.entity.TBasecodeEntity;
import com.njusc.base.bean.BaseService;

import java.util.List;


/**
 * 字典表
 *
 * @author Michael
 * @date 2021-01-18 10:52:41
 */
public interface TBasecodeService extends BaseService<TBasecodeEntity,String> {
    List<TBasecodeEntity> findEducations();
    List<TBasecodeEntity> findMarriages();
}

