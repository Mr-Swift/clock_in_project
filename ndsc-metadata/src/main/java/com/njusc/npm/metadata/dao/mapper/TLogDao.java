package com.njusc.npm.metadata.dao.mapper;

import com.njusc.npm.metadata.entity.TLogEntity;
import com.njusc.npm.metadata.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 日志表
 *
 * @author Michael
 * @date 2021-01-18 10:52:40
 */
@Repository
public interface TLogDao extends BaseDao<TLogEntity,String> {

    public void save(Map logEntity);

    List<TLogEntity> getLogs(Map<String,Object>params);

    List<TLogEntity> getLogs();
}
