package com.njusc.npm.metadata.dao.mapper;

import com.njusc.npm.metadata.dao.BaseDao;
import com.njusc.npm.metadata.entity.TBasecodeEntity;
import com.njusc.npm.metadata.entity.TLeaveEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TLeaveDao extends BaseDao<TLeaveEntity,String> {
    List<TBasecodeEntity> selectAllLeaveType();

    List<TLeaveEntity> selectAllApplicationData(Map<String,Object> params);

    List<TLeaveEntity> selectAllReviewData(Map<String,Object> params);

    TLeaveEntity findLeaveApplication(Map<String, Object> params);

    void delete(String str);

    void check(Map<String, Object> params);

    void save(TLeaveEntity tLeaveEntity);
    void update(TLeaveEntity tLeaveEntity);
}
