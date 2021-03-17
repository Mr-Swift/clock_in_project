package com.njusc.npm.service.impl;

import com.njusc.npm.metadata.dao.mapper.TLeaveDao;
import com.njusc.npm.metadata.entity.TBasecodeEntity;
import com.njusc.npm.metadata.entity.TLeaveEntity;
import com.njusc.npm.service.TLeaveService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TLeaveServiceImpl implements TLeaveService {
    //日志
    private static final Logger LOGGER = Logger.getLogger(TDeptServiceImpl.class);

    @Autowired
    private TLeaveDao tLeaveDao;

    @Override
    public void save(TLeaveEntity tLeaveEntity) {
        tLeaveDao.save(tLeaveEntity);
    }

    @Override
    public void saveAll(List<TLeaveEntity> list) {

    }

    @Override
    public void delete(String s) {
        tLeaveDao.delete(s);
    }

    @Override
    public void deleteAll(List<String> list) {

    }

    @Override
    public void update(TLeaveEntity tLeaveEntity) {
        tLeaveDao.update(tLeaveEntity);
    }

    @Override
    public void updateAll(List<TLeaveEntity> list) {

    }

    @Override
    public TLeaveEntity find(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<TLeaveEntity> findAll(Map<String, Object> params) {
        return null;
    }


    @Override
    public List<TBasecodeEntity> selectAllLeaveType() {
        return tLeaveDao.selectAllLeaveType();
    }


    @Override
    public List<TLeaveEntity> selectAllApplicationData(Map<String, Object> params) {
        return tLeaveDao.selectAllApplicationData(params);
    }


    @Override
    public List<TLeaveEntity> selectAllReviewData(Map<String, Object> params) {
        return tLeaveDao.selectAllReviewData(params);
    }

    @Override
    public void check(Map<String, Object> params) {
        tLeaveDao.check(params);
    }

    @Override
    public TLeaveEntity findLeaveApplication(Map<String, Object> params) {
        return tLeaveDao.findLeaveApplication(params);
    }
}
