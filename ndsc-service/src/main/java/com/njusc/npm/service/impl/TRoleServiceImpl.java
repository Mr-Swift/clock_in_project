package com.njusc.npm.service.impl;

import com.njusc.npm.metadata.dao.mapper.TRoleDao;
import com.njusc.npm.metadata.dao.mapper.TRoleFunctionDao;
import com.njusc.npm.metadata.entity.TRoleEntity;
import com.njusc.npm.metadata.entity.TRoleFunctionEntity;
import com.njusc.npm.service.TRoleService;
import com.njusc.npm.utils.util.Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@Transactional
public class TRoleServiceImpl implements TRoleService {


    //日志
    private static final Logger LOGGER = Logger.getLogger(TRoleServiceImpl.class);

    @Autowired
    private TRoleDao tRoleDao;
    @Autowired
    private TRoleFunctionDao tRoleFunctionDao;

    @Override
    public void save(TRoleEntity t) {
        tRoleDao.save(t);
    }

    @Override
    public void saveAll(List<TRoleEntity> list) {
        tRoleDao.saveAll(list);
    }

    @Override
    public void delete(String id) {
        tRoleDao.delete(id);
    }

    @Override
    public void deleteAll(List<String> list) {
        tRoleDao.deleteAll(list);
    }

    @Override
    public void update(TRoleEntity t) {
        tRoleDao.update(t);
    }

    @Override
    public void updateAll(List<TRoleEntity> list) {
        tRoleDao.updateAll(list);
    }

    @Override
    public TRoleEntity find(Map<String, Object> params) {
        return tRoleDao.find(params);
    }

    @Override
    public List<TRoleEntity> findAll(Map<String, Object> params) {
        return tRoleDao.findAll(params);
    }

    @Override
    public List<TRoleEntity> findUpdate(Map<String, Object> params) {
        return tRoleDao.findUpdate(params);
    }

    @Override
    public List<TRoleEntity> findList(Map<String, Object> params) {
        return tRoleDao.findList(params);
    }

    @Override
    public List<TRoleEntity> findRoleList() {
        return tRoleDao.findRoleList();
    }

    @Override
    public void saveOrUpdate(TRoleEntity t, String funId, String user) {
        Map<String,Object> params2 = new HashMap<>();
        params2.put("roleName",t.getRoleName());
        if (StringUtils.isEmpty(tRoleDao.find(params2))) {
            t.setId(Util.uuid());
            t.setInsertUser(user);
            t.setInsertDate(new Date());
            t.setIsdel(0);
            tRoleDao.save(t);

            if (!"".equals(funId) && null != funId) {
                String[] funIds = funId.split(",");
                List<TRoleFunctionEntity> list = new ArrayList<>();
                for (int i = 0; i < funIds.length; i++) {
                    TRoleFunctionEntity tRoleFunctionEntity = new TRoleFunctionEntity();
                    tRoleFunctionEntity.setFunctionId(funIds[i]);
                    tRoleFunctionEntity.setInsertDate(new Date());
                    tRoleFunctionEntity.setInsertUser(t.getInsertUser());
                    tRoleFunctionEntity.setRoleId(t.getId());
                    tRoleFunctionEntity.setIsdel(0);
                    tRoleFunctionEntity.setId(Util.uuid());
                    list.add(tRoleFunctionEntity);
                }
                tRoleFunctionDao.saveAll(list);
            }
        } else {
            Map<String, Object> params = new HashMap<>();
            TRoleEntity t2=tRoleDao.find(params2);
            params.put("id", t2.getId());
            Map<String, Object> map = new HashMap<>();
            map.put("roleId", t2.getId());
            TRoleEntity tRoleEntity = tRoleDao.find(params);
            tRoleEntity.setRoleName(t.getRoleName());
            tRoleEntity.setRemark(t.getRemark());
            tRoleEntity.setIsdel(0);
            tRoleDao.update(tRoleEntity);
            List<TRoleFunctionEntity> list = new ArrayList<>();
            List<TRoleFunctionEntity> tRoleFunctionEntitiesList = tRoleFunctionDao.findAll(map);
            if (tRoleFunctionEntitiesList.size() > 0) {
                for (TRoleFunctionEntity tRoleFunctionEntity : tRoleFunctionEntitiesList) {
                    tRoleFunctionEntity.setIsdel(1);
                    list.add(tRoleFunctionEntity);
                }
                tRoleFunctionDao.updateAll(list);
            }
            if (!"".equals(funId) && null != funId) {
                String[] funIds = funId.split(",");
                List<TRoleFunctionEntity> functionList = new ArrayList<>();
                for (int i = 0; i < funIds.length; i++) {
                    TRoleFunctionEntity tRoleFunctionEntity = new TRoleFunctionEntity();
                    tRoleFunctionEntity.setFunctionId(funIds[i]);
                    tRoleFunctionEntity.setInsertDate(new Date());
                    tRoleFunctionEntity.setInsertUser(t.getInsertUser());
                    tRoleFunctionEntity.setRoleId(t2.getId());
                    tRoleFunctionEntity.setIsdel(0);
                    tRoleFunctionEntity.setId(Util.uuid());
                    functionList.add(tRoleFunctionEntity);
                }
                tRoleFunctionDao.saveAll(functionList);
            }
        }
    }

    @Override
        public void delRole (String id){
            Map<String, Object> map = new HashMap<>();
            map.put("roleId", id);
            List<TRoleFunctionEntity> functionList = new ArrayList<>();
            List<TRoleFunctionEntity> tRoleFunctionEntityList = tRoleFunctionDao.findAll(map);
            if(tRoleFunctionEntityList.size()>0){
                for(TRoleFunctionEntity tRoleFunctionEntity:tRoleFunctionEntityList){
                    tRoleFunctionEntity.setIsdel(1);
                    functionList.add(tRoleFunctionEntity);
                }
                tRoleFunctionDao.updateAll(functionList);
            }
            tRoleDao.delete(id);
        }

    @Override
    public void saveUpdate(TRoleEntity t, String funId, String user, String oldName) {
        Map<String, Object> params = new HashMap<>();
        params.put("roleName", oldName);
        TRoleEntity t2 = tRoleDao.find(params);
        params.put("id", t2.getId());
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", t2.getId());
        TRoleEntity tRoleEntity = tRoleDao.find(params);
        tRoleEntity.setRoleName(t.getRoleName());
        tRoleEntity.setRemark(t.getRemark());
        tRoleEntity.setIsdel(0);
        tRoleDao.update(tRoleEntity);
        List<TRoleFunctionEntity> list = new ArrayList<>();
        List<TRoleFunctionEntity> tRoleFunctionEntitiesList = tRoleFunctionDao.findAll(map);
        if (tRoleFunctionEntitiesList.size() > 0) {
            for (TRoleFunctionEntity tRoleFunctionEntity : tRoleFunctionEntitiesList) {
                tRoleFunctionEntity.setIsdel(1);
                list.add(tRoleFunctionEntity);
            }
            tRoleFunctionDao.updateAll(list);
        }
        if (!"".equals(funId) && null != funId) {
            String[] funIds = funId.split(",");
            List<TRoleFunctionEntity> functionList = new ArrayList<>();
            for (int i = 0; i < funIds.length; i++) {
                TRoleFunctionEntity tRoleFunctionEntity = new TRoleFunctionEntity();
                tRoleFunctionEntity.setFunctionId(funIds[i]);
                tRoleFunctionEntity.setInsertDate(new Date());
                tRoleFunctionEntity.setInsertUser(t.getInsertUser());
                tRoleFunctionEntity.setRoleId(t2.getId());
                tRoleFunctionEntity.setIsdel(0);
                tRoleFunctionEntity.setId(Util.uuid());
                functionList.add(tRoleFunctionEntity);
            }
            tRoleFunctionDao.saveAll(functionList);
        }
    }

}
