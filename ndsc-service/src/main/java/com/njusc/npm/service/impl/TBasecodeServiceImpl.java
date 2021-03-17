package com.njusc.npm.service.impl;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.njusc.npm.metadata.dao.mapper.TBasecodeDao;
import com.njusc.npm.metadata.entity.TBasecodeEntity;
import com.njusc.npm.service.TBasecodeService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TBasecodeServiceImpl implements TBasecodeService {

    //日志
    private static final Logger LOGGER = Logger.getLogger(TBasecodeServiceImpl.class);

    @Autowired
    private TBasecodeDao tBasecodeDao;

    @Override
    public void save(TBasecodeEntity t) {
        tBasecodeDao.save(t);
    }

    @Override
    public void saveAll(List<TBasecodeEntity> list) {
        tBasecodeDao.saveAll(list);
    }

    @Override
    public void delete(String id) {
        tBasecodeDao.delete(id);
    }

    @Override
    public void deleteAll(List<String> list) {
        tBasecodeDao.deleteAll(list);
    }

    @Override
    public void update(TBasecodeEntity t) {
        tBasecodeDao.update(t);
    }

    @Override
    public void updateAll(List<TBasecodeEntity> list) {
        tBasecodeDao.updateAll(list);
    }

    @Override
    public TBasecodeEntity find(Map<String, Object> params) {
        return tBasecodeDao.find(params);
    }

    @Override
    public List<TBasecodeEntity> findAll(Map<String, Object> params) {
        return tBasecodeDao.findAll(params);
    }

    @Override
    public List<TBasecodeEntity> findEducations() {
        return tBasecodeDao.findEducations();
    }

    @Override
    public List<TBasecodeEntity> findMarriages() {
        return tBasecodeDao.findMarriages();
    }
}
