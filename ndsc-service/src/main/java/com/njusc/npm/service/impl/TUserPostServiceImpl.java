package com.njusc.npm.service.impl;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.njusc.npm.metadata.dao.mapper.TUserPostDao;
import com.njusc.npm.metadata.entity.TUserPostEntity;
import com.njusc.npm.service.TUserPostService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TUserPostServiceImpl implements TUserPostService {

    //日志
    private static final Logger LOGGER = Logger.getLogger(TUserPostServiceImpl.class);

    @Autowired
    private TUserPostDao tUserPostDao;

    @Override
    public void save(TUserPostEntity t) {
        tUserPostDao.save(t);
    }

    @Override
    public void saveAll(List<TUserPostEntity> list) {
        tUserPostDao.saveAll(list);
    }

    @Override
    public void delete(String id) {
        tUserPostDao.delete(id);
    }

    @Override
    public void deleteAll(List<String> list) {
        tUserPostDao.deleteAll(list);
    }

    @Override
    public void update(TUserPostEntity t) {
        tUserPostDao.update(t);
    }

    @Override
    public void updateAll(List<TUserPostEntity> list) {
        tUserPostDao.updateAll(list);
    }

    @Override
    public TUserPostEntity find(Map<String, Object> params) {
        return tUserPostDao.find(params);
    }

    @Override
    public List<TUserPostEntity> findAll(Map<String, Object> params) {
        return tUserPostDao.findAll(params);
    }


    @Override
    public List<TUserPostEntity> findAll2(Map<String, Object> map) {
        return tUserPostDao.findAll2(map);
    }

    @Override
    public void deleteByUserId(String userId) {
        tUserPostDao.deleteByUserId(userId);
    }

    @Override
    public void updateByUserId(TUserPostEntity t) {
        tUserPostDao.updateByUserId(t);
    }

    @Override
    public void deleteByPostId(String postManageId) {
        tUserPostDao.deleteByPostId(postManageId);
    }

}
