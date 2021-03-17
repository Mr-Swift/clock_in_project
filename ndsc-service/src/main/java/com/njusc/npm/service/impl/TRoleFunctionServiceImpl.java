package com.njusc.npm.service.impl;

import com.njusc.npm.metadata.dao.mapper.TRoleFunctionDao;
import com.njusc.npm.metadata.entity.TRoleFunctionEntity;
import com.njusc.npm.service.TRoleFunctionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TRoleFunctionServiceImpl implements TRoleFunctionService {

    //日志
    private static final Logger LOGGER = Logger.getLogger(TRoleFunctionServiceImpl.class);

    @Autowired
    private TRoleFunctionDao tRoleFunctionDao;

    @Override
    public void save(TRoleFunctionEntity t) {
        tRoleFunctionDao.save(t);
    }

    @Override
    public void saveAll(List<TRoleFunctionEntity> list) {
        tRoleFunctionDao.saveAll(list);
    }

    @Override
    public void delete(String id) {
        tRoleFunctionDao.delete(id);
    }

    @Override
    public void deleteAll(List<String> list) {
        tRoleFunctionDao.deleteAll(list);
    }

    @Override
    public void update(TRoleFunctionEntity t) {
        tRoleFunctionDao.update(t);
    }

    @Override
    public void updateAll(List<TRoleFunctionEntity> list) {
        tRoleFunctionDao.updateAll(list);
    }

    @Override
    public TRoleFunctionEntity find(Map<String, Object> params) {
        return tRoleFunctionDao.find(params);
    }

    @Override
    public List<TRoleFunctionEntity> findAll(Map<String, Object> params) {
        return tRoleFunctionDao.findAll(params);
    }


    @Override
    public List<TRoleFunctionEntity> getRoleFunctionByRoleId(Map params) {
        return tRoleFunctionDao.getRoleFunctionByRoleId(params);
    }

    @Override
    public void deletFunction(String roleId) { tRoleFunctionDao.deletFunction(roleId);
    }
}
