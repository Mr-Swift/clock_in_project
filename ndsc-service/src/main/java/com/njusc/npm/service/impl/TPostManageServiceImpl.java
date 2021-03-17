package com.njusc.npm.service.impl;

import java.util.List;
import java.util.Map;

import com.njusc.npm.metadata.dao.mapper.TUserPostDao;
import com.njusc.npm.metadata.entity.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.njusc.npm.metadata.dao.mapper.TPostManageDao;
import com.njusc.npm.service.TPostManageService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TPostManageServiceImpl implements TPostManageService {

    //日志
    private static final Logger LOGGER = Logger.getLogger(TPostManageServiceImpl.class);

    @Autowired
    private TPostManageDao tPostManageDao;

    @Autowired
    private TUserPostDao tUserPostDao;

    @Override
    public List<TBasecodeEntity> findPostTypeList() {
        return tPostManageDao.getPostTypes();
    }

    @Override
    public List<TPostEntity> verifyPostName(TPostEntity postManageEntity) {
        return tPostManageDao.verifyPostName(postManageEntity);
    }

    @Override
    public void deleteByPostId(String id) {
        tUserPostDao.deleteByPostId(id);
        tPostManageDao.deleteByPostId(id);
    }

    @Override
    public List<TDeptEntity> findDeptList() {
        return tPostManageDao.getDepts();
    }

    @Override
    public List<TPostEntity> findList(Map<String, Object> params) {

        return tPostManageDao.getPosts(params);
    }

    @Override
    public void save(TPostEntity t) {
        tPostManageDao.save(t);
    }

    @Override
    public void saveAll(List<TPostEntity> list) {
        tPostManageDao.saveAll(list);
    }

    @Override
    public void delete(String id) {
        tPostManageDao.delete(id);
    }

    @Override
    public void deleteAll(List<String> list) {
        tPostManageDao.deleteAll(list);
    }

    @Override
    public void update(TPostEntity t) {
        tPostManageDao.update(t);
    }

    @Override
    public void updateAll(List<TPostEntity> list) {
        tPostManageDao.updateAll(list);
    }

    @Override
    public TPostEntity find(Map<String, Object> params) {
        return tPostManageDao.find(params);
    }

    @Override
    public List<TPostEntity> findAll(Map<String, Object> params) {
        return tPostManageDao.findAll(params);
    }


    @Override
    public List<TPostEntity> findPosts() {
        return tPostManageDao.findPosts();
    }
}
