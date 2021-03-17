package com.njusc.npm.service;


import com.njusc.base.bean.BaseService;
import com.njusc.npm.metadata.entity.TBasecodeEntity;
import com.njusc.npm.metadata.entity.TLeaveEntity;
import com.njusc.npm.service.impl.TDeptServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface TLeaveService extends BaseService<TLeaveEntity,String> {
    List<TBasecodeEntity> selectAllLeaveType();

    List<TLeaveEntity> selectAllApplicationData(Map<String,Object> params);

    List<TLeaveEntity> selectAllReviewData(Map<String,Object> params);

    void delete(String str);


    void check(Map<String, Object> params);

    void save(TLeaveEntity tLeaveEntity);
    void update(TLeaveEntity tLeaveEntity);

    TLeaveEntity findLeaveApplication(Map<String, Object> params);
}
