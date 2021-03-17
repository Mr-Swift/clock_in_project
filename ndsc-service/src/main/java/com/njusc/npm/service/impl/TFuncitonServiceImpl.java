package com.njusc.npm.service.impl;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.njusc.npm.metadata.dao.mapper.TFuncitonDao;
import com.njusc.npm.metadata.entity.TFuncitonEntity;
import com.njusc.npm.service.TFuncitonService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TFuncitonServiceImpl implements TFuncitonService {

    //日志
    private static final Logger LOGGER = Logger.getLogger(TFuncitonServiceImpl.class);

    @Autowired
    private TFuncitonDao tFuncitonDao;

    @Override
    public void save(TFuncitonEntity t) {
        tFuncitonDao.save(t);
    }

    @Override
    public void saveAll(List<TFuncitonEntity> list) {
        tFuncitonDao.saveAll(list);
    }

    @Override
    public void delete(String id) {
        tFuncitonDao.delete(id);
    }

    @Override
    public void deleteAll(List<String> list) {
        tFuncitonDao.deleteAll(list);
    }

    @Override
    public void update(TFuncitonEntity t) {
        tFuncitonDao.update(t);
    }

    @Override
    public void updateAll(List<TFuncitonEntity> list) {
        tFuncitonDao.updateAll(list);
    }

    @Override
    public TFuncitonEntity find(Map<String, Object> params) {
        return tFuncitonDao.find(params);
    }

    @Override
    public List<TFuncitonEntity> findAll(Map<String, Object> params) {
        return tFuncitonDao.findAll(params);
    }

    @Override
    public List<TFuncitonEntity> findFunciton2() {
        return tFuncitonDao.findFunciton2();
    }

    @Override
    public List<TFuncitonEntity> findParentFunciton() {
        return tFuncitonDao.findParentFunciton();
    }

    @Override
    public List<TFuncitonEntity> findChildFunciton(String id) {
        return tFuncitonDao.findChildFunciton(id);
    }

    @Override
    public String findParentIdByFunName(Map map) {
        return tFuncitonDao.findParentIdByFunName(map);
    }

    @Override
    public List<TFuncitonEntity> getFunctionListAdmin() {
        return tFuncitonDao.getFunctionListAdmin();
    }

    @Override
    public List<TFuncitonEntity> getNextFunctionListAdmin(Map<String, Object> param) {
        return tFuncitonDao.getNextFunctionListAdmin(param);
    }

    @Override
    public List<TFuncitonEntity> getFunctionList(String userId) {
        return tFuncitonDao.getFunctionList(userId);
    }

    @Override
    public List<TFuncitonEntity> getNextFunctionList(Map<String, Object> param) {
        return tFuncitonDao.getNextFunctionList(param) ;
    }

    @Override
    public List<TFuncitonEntity> findAllWithDeleted(Map map) {
        return tFuncitonDao.findAllWithDeleted(map);
    }

    @Override
    public TFuncitonEntity findFunctionByFunNameAndID(Map map) {
        return tFuncitonDao.findFunctionByFunNameAndID(map);
    }
}
