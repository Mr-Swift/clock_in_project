package com.njusc.npm.service.impl;


import com.njusc.npm.metadata.dao.mapper.TAttendanceTimeDao;
import com.njusc.npm.metadata.entity.TAttendanceTimeEntity;
import com.njusc.npm.metadata.entity.TBasecodeEntity;
import com.njusc.npm.service.TAttendanceTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@SuppressWarnings("all")

/**
 * ruturn null待解决
 */
@Service
@Transactional
public class TAttendanceTimeServiceImpl implements TAttendanceTimeService {

    @Autowired
    private TAttendanceTimeDao tAttendanceTimeDao;


    @Override
    public void save(TAttendanceTimeEntity tAttendanceTimeEntity) {
        tAttendanceTimeDao.save(tAttendanceTimeEntity);
    }

    @Override
    public void saveAll(List<TAttendanceTimeEntity> list) {

    }

    @Override
    public void delete(String s) {
        tAttendanceTimeDao.delete(s);
    }

    @Override
    public void deleteAll(List<String> list) {

    }

    @Override
    public void update(TAttendanceTimeEntity tAttendanceTimeEntity) {
        tAttendanceTimeDao.update(tAttendanceTimeEntity);
    }

    @Override
    public void updateAll(List<TAttendanceTimeEntity> list) {

    }

    @Override
    public TAttendanceTimeEntity find(Map<String, Object> params) {
        return tAttendanceTimeDao.find(params);
    }

    @Override
    public List<TAttendanceTimeEntity> findAll(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<TBasecodeEntity> getAttendanceType() {
        return tAttendanceTimeDao.getAttendanceType();
    }


    @Override
    public List<TAttendanceTimeEntity> getAll() {
        return tAttendanceTimeDao.getAll();
    }
}
