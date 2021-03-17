package com.njusc.npm.service.impl;

import java.util.List;
import java.util.Map;

import com.njusc.npm.metadata.dao.mapper.TPostManageDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.njusc.npm.metadata.dao.mapper.TDeptDao;
import com.njusc.npm.metadata.entity.TDeptEntity;
import com.njusc.npm.service.TDeptService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TDeptServiceImpl implements TDeptService {

    //日志
    private static final Logger LOGGER = Logger.getLogger(TDeptServiceImpl.class);

    @Autowired
    private TDeptDao tDeptDao;
    @Autowired
    private TPostManageDao tPostManageDao;

    @Override
    public void save(TDeptEntity t) {
        tDeptDao.save(t);
    }

    @Override
    public void saveAll(List<TDeptEntity> list) {
        tDeptDao.saveAll(list);
    }

    @Override
    public void delete(String id) {
        tDeptDao.delete(id);
        tPostManageDao.deleteByDeptId(id);
    }

    @Override
    public void deleteAll(List<String> list) {
        tDeptDao.deleteAll(list);
    }

    @Override
    public void update(TDeptEntity t) {
        tDeptDao.update(t);
    }

    @Override
    public void updateAll(List<TDeptEntity> list) {
        tDeptDao.updateAll(list);
    }

    @Override
    public TDeptEntity find(Map<String, Object> params) {
        return tDeptDao.find(params);
    }

    @Override
    public List<TDeptEntity> findAll(Map<String, Object> params) {
        return tDeptDao.findAll(params);
    }


    @Override
    public List<TDeptEntity> findList(Map<String, Object> params) {
        return tDeptDao.findList(params);
    }

    @Override
    public List<Map> findCheckList(Map<String, Object> params) {
        return tDeptDao.findCheckList(params);
    }

    @Override
    public List<TDeptEntity> findByDeptName(String deptName) {
        return tDeptDao.findByDeptName(deptName);
    }

    /**
     * 下拉列表，查询所有的部门
     * @return
     */
    @Override
    public List<TDeptEntity> findDepts() {
        return tDeptDao.findDepts();
    }
}
