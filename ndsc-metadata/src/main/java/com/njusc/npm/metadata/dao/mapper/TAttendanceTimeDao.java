package com.njusc.npm.metadata.dao.mapper;


import com.njusc.npm.metadata.dao.BaseDao;
import com.njusc.npm.metadata.entity.TAttendanceTimeEntity;
import com.njusc.npm.metadata.entity.TBasecodeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TAttendanceTimeDao extends BaseDao<TAttendanceTimeEntity,String> {
    /**
     * 获取所有的考勤类型
     * @return
     */
    public List<TBasecodeEntity> getAttendanceType();

    public List<TAttendanceTimeEntity> getAll();

    TAttendanceTimeEntity find(Map<String, Object> params);

    void save(TAttendanceTimeEntity tAttendanceTimeEntity);
    void update(TAttendanceTimeEntity tAttendanceTimeEntity);
    void delete(String id);

}
