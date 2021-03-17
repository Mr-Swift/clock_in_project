package com.njusc.npm.service;

import com.njusc.base.bean.BaseService;
import com.njusc.npm.metadata.entity.TAttendanceStatisticsEntity;

import java.util.List;
import java.util.Map;

public interface TAttendanceStatisticsService extends BaseService<TAttendanceStatisticsEntity,String> {
    TAttendanceStatisticsEntity extract(Map<String, Object> params);

    void saveList(List<TAttendanceStatisticsEntity> list);
}
