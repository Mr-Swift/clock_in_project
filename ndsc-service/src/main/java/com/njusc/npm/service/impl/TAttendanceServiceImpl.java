package com.njusc.npm.service.impl;

import com.njusc.npm.metadata.dao.mapper.TAttendanceDao;
import com.njusc.npm.metadata.entity.TAttendanceEntity;
import com.njusc.npm.metadata.entity.TBasecodeEntity;
import com.njusc.npm.service.TAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
@Transactional
public class TAttendanceServiceImpl implements TAttendanceService {
    @Autowired
    private TAttendanceDao tAttendanceDao;


    @Override
    public void save(TAttendanceEntity tAttendanceEntity) {

    }

    @Override
    public void saveAll(List<TAttendanceEntity> list) {

    }

    @Override
    public void delete(String s) {

    }

    @Override
    public void deleteAll(List<String> list) {

    }

    @Override
    public void update(TAttendanceEntity tAttendanceEntity) {

    }

    @Override
    public void updateAll(List<TAttendanceEntity> list) {

    }

    @Override
    public TAttendanceEntity find(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<TAttendanceEntity> findAll(Map<String, Object> params) {
        return null;
    }


    @Override
    public void addGoToWork(TAttendanceEntity t) {
        tAttendanceDao.addGoToWork(t);
    }

    @Override
    public List<TAttendanceEntity> getGoToWork(Map<String, Object> params) {
        return tAttendanceDao.getGoToWork(params);
    }

    @Override
    public String getGoToWorkTime() {
        return tAttendanceDao.getGoToWorkTime();
    }


    @Override
    public String getOffWorkTime() {
       return tAttendanceDao.getOffWorkTime();
    }

    @Override
    public void addOffWork(TAttendanceEntity t) {
        tAttendanceDao.addOffWork(t);
    }

    @Override
    public List<TAttendanceEntity> getOffWork(Map<String, Object> params) {
        return tAttendanceDao.getOffWork(params);
    }

    @Override
    public List<String> getOtherClockInType() {
        return tAttendanceDao.getOtherClockInType();
    }


    @Override
    public void addGoOut(Map<String, Object> params) {
        tAttendanceDao.addGoOut(params);
    }

    @Override
    public List<TAttendanceEntity> getGoOut(Map<String, Object> params) {
        return tAttendanceDao.getGoOut(params);
    }

    @Override
    public List<TAttendanceEntity> findDetail(Map<String, Object> params) {
        return tAttendanceDao.findDetail(params);
    }
}
