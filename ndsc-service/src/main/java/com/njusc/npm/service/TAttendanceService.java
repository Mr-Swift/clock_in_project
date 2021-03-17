package com.njusc.npm.service;

import com.njusc.base.bean.BaseService;
import com.njusc.npm.metadata.entity.TAttendanceEntity;
import com.njusc.npm.metadata.entity.TBasecodeEntity;

import java.util.List;
import java.util.Map;

/**
 * 上班打卡
 */
public interface TAttendanceService extends BaseService<TAttendanceEntity,String> {

    void addGoToWork(TAttendanceEntity t);

    List<TAttendanceEntity> getGoToWork(Map<String,Object> params);

    String getGoToWorkTime();

    String getOffWorkTime();

    void addOffWork(TAttendanceEntity t);

    List<TAttendanceEntity> getOffWork(Map<String, Object> params);

    List<String> getOtherClockInType();

    void addGoOut(Map<String, Object> params);

    List<TAttendanceEntity> getGoOut(Map<String, Object> params);

    List<TAttendanceEntity> findDetail(Map<String,Object> params);

}
