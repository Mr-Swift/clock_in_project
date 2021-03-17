package com.njusc.npm.metadata.dao.mapper;

import com.njusc.npm.metadata.dao.BaseDao;
import com.njusc.npm.metadata.entity.TAttendanceStatisticsEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TAttendanceStatisticsDao extends BaseDao<TAttendanceStatisticsEntity,String> {
    TAttendanceStatisticsEntity extract(Map<String, Object> params);

    void saveList(List<TAttendanceStatisticsEntity> list);
}
