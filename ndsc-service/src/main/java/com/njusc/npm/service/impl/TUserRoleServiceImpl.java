package com.njusc.npm.service.impl;

import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.njusc.npm.metadata.dao.mapper.TUserRoleDao;
import com.njusc.npm.metadata.entity.TUserRoleEntity;
import com.njusc.npm.service.TUserRoleService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TUserRoleServiceImpl implements TUserRoleService {

    //日志
    private static final Logger LOGGER = Logger.getLogger(TUserRoleServiceImpl.class);

    @Autowired
    private TUserRoleDao tUserRoleDao;

    @Override
    public void save(TUserRoleEntity t) {
        tUserRoleDao.save(t);
    }

    @Override
    public void saveAll(List<TUserRoleEntity> list) {
        tUserRoleDao.saveAll(list);
    }

    @Override
    public void delete(String id) {
        tUserRoleDao.delete(id);
    }

    @Override
    public void deleteAll(List<String> list) {
        tUserRoleDao.deleteAll(list);
    }

    @Override
    public void update(TUserRoleEntity t) {
        tUserRoleDao.update(t);
    }

    @Override
    public void updateAll(List<TUserRoleEntity> list) {
        tUserRoleDao.updateAll(list);
    }

    @Override
    public TUserRoleEntity find(Map<String, Object> params) {
        return tUserRoleDao.find(params);
    }

    @Override
    public List<TUserRoleEntity> findAll(Map<String, Object> params) {
        return tUserRoleDao.findAll(params);
    }


}
