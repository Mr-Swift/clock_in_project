package com.njusc.npm.service;

import com.njusc.npm.metadata.entity.TLogEntity;
import com.njusc.base.bean.BaseService;

import java.util.List;
import java.util.Map;


/**
 * 日志表
 *
 * @author Michael
 * @date 2021-01-18 10:52:40
 */
public interface TLogService extends BaseService<TLogEntity,String> {

    /**
     * 日志记录
     * @param logMap
     */
    public void saveLog(Map logMap);
    List<TLogEntity> findList(Map<String, Object> params);
}

