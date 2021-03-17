package com.njusc.npm.service.impl;

import com.njusc.npm.metadata.dao.mapper.TAttendanceStatisticsDao;
import com.njusc.npm.metadata.entity.TAttendanceStatisticsEntity;
import com.njusc.npm.service.TAttendanceStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TAttendanceStatisticsServiceImpl implements TAttendanceStatisticsService {
    @Autowired
    private TAttendanceStatisticsDao tAttendanceStatisticsDao;

    @Override
    public void save(TAttendanceStatisticsEntity tAttendanceStatisticsEntity) {

    }

    @Override
    public void saveAll(List<TAttendanceStatisticsEntity> list) {

    }

    @Override
    public void delete(String s) {

    }

    @Override
    public void deleteAll(List<String> list) {

    }

    @Override
    public void update(TAttendanceStatisticsEntity tAttendanceStatisticsEntity) {

    }

    @Override
    public void updateAll(List<TAttendanceStatisticsEntity> list) {

    }

    @Override
    public TAttendanceStatisticsEntity find(Map<String, Object> params) {
        return null;
    }

    @Override
    public List<TAttendanceStatisticsEntity> findAll(Map<String, Object> params) {
        return tAttendanceStatisticsDao.findAll(params);
    }


    @Override
    public TAttendanceStatisticsEntity extract(Map<String, Object> params) {
        return tAttendanceStatisticsDao.extract(params);
    }

    @Override
    public void saveList(List<TAttendanceStatisticsEntity> list) {
        tAttendanceStatisticsDao.saveList(list);
    }
}
