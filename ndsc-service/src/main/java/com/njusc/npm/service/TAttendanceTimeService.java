package com.njusc.npm.service;

import com.njusc.base.bean.BaseService;
import com.njusc.npm.metadata.entity.TAttendanceTimeEntity;
import com.njusc.npm.metadata.entity.TBasecodeEntity;

import java.util.List;
import java.util.Map;

public interface TAttendanceTimeService extends BaseService<TAttendanceTimeEntity,String> {
    /**
     * 获取所有的的考勤类型
     */
    public List<TBasecodeEntity> getAttendanceType();

    public List<TAttendanceTimeEntity> getAll();

    TAttendanceTimeEntity find(Map<String, Object> params);

    void save(TAttendanceTimeEntity tAttendanceTimeEntity);
    void update(TAttendanceTimeEntity tAttendanceTimeEntity);
    void delete(String id);
}
