package com.njusc.npm.metadata.dao.mapper;

import com.njusc.npm.metadata.dao.BaseDao;
import com.njusc.npm.metadata.entity.TAttendanceEntity;
import com.njusc.npm.metadata.entity.TBasecodeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TAttendanceDao extends BaseDao<TAttendanceEntity,String> {
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
