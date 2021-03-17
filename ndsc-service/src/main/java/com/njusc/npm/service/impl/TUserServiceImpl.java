package com.njusc.npm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.njusc.npm.metadata.dao.mapper.TUserDao;
import com.njusc.npm.metadata.entity.TUserEntity;
import com.njusc.npm.service.TUserService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TUserServiceImpl implements TUserService {

    //日志
    private static final Logger LOGGER = Logger.getLogger(TUserServiceImpl.class);

    @Autowired
    private TUserDao tUserDao;

    @Override
    public TUserEntity getUserById(String id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id",id);
        return tUserDao.selectUserById(params);
    }

    @Override
    public List<TUserEntity> findList(Map<String, Object> params) {
        return tUserDao.getUsers(params);
    }

    @Override
    public TUserEntity findUser(String id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id",id);
        return tUserDao.findUserById(params);
    }

    @Override
    public void updateRole(String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId",userId);
        tUserDao.updateRole(params);
    }

    @Override
    public void save(TUserEntity t) {
        tUserDao.save(t);
    }

    @Override
    public void saveAll(List<TUserEntity> list) {
        tUserDao.saveAll(list);
    }

    @Override
    public void delete(String id) {
        tUserDao.delete(id);
    }

    @Override
    public void deleteAll(List<String> list) {
        tUserDao.deleteAll(list);
    }

    @Override
    public void update(TUserEntity t) {
        tUserDao.update(t);
    }

    @Override
    public void updateAll(List<TUserEntity> list) {
        tUserDao.updateAll(list);
    }

    @Override
    public TUserEntity find(Map<String, Object> params) {
        return tUserDao.find(params);
    }

    @Override
    public List<TUserEntity> findAll(Map<String, Object> params) {
        return tUserDao.findAll(params);
    }


    @Override
    public List findUserAndDept(Map<String, Object> params) {
        return tUserDao.findUserAndDept(params);
    }

    @Override
    public List<String> findAllPersonNo() {
        return tUserDao.findAllPersonNo();
    }

    @Override
    public List<TUserEntity> findAll2(Map<String, Object> map) {
        return tUserDao.findAll2(map);
    }

//    @Override
//    public void updateTUser(TUserEntity t) {
//        tUserDao.updateTUser(t);
//    }

    @Override
    public TUserEntity getIdByName(Map<String, Object> params) {
        return tUserDao.getIdByName(params);
    }


    /**
     * 查询用户表所有的用户状态，用于下拉列表
     * @return
     */
    @Override
    public List<String> findUserStatus() {
        return tUserDao.findUserStatus();
    }

     /**
     * 员工信息管理数据显示
     * @param params
     * @return
     */
    @Override
    public List<TUserEntity> findUserInfo(Map<String, Object> params) {
        return tUserDao.findUserInfo(params);
    }
}
