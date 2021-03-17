package com.njusc.npm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.njusc.npm.metadata.entity.TUserEntity;
import com.njusc.npm.utils.util.Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.njusc.npm.metadata.dao.mapper.TLogDao;
import com.njusc.npm.metadata.entity.TLogEntity;
import com.njusc.npm.service.TLogService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TLogServiceImpl implements TLogService {

    //日志
    private static final Logger LOGGER = Logger.getLogger(TLogServiceImpl.class);

    @Autowired
    private TLogDao tLogDao;

    @Override
    public void save(TLogEntity t) {
        tLogDao.save(t);
    }



    @Override
    public void saveAll(List<TLogEntity> list) {
        tLogDao.saveAll(list);
    }

    @Override
    public void delete(String id) {
        tLogDao.delete(id);
    }

    @Override
    public void deleteAll(List<String> list) {
        tLogDao.deleteAll(list);
    }

    @Override
    public void update(TLogEntity t) {
        tLogDao.update(t);
    }

    @Override
    public void updateAll(List<TLogEntity> list) {
        tLogDao.updateAll(list);
    }

    @Override
    public TLogEntity find(Map<String, Object> params) {
        return tLogDao.find(params);
    }

    @Override
    public List<TLogEntity> findAll(Map<String, Object> params) {
        return tLogDao.findAll(params);
    }

    @Override
    public List<TLogEntity> findList(Map<String,Object>params){
        return tLogDao.getLogs(params);
    }

    @Override
    public void saveLog(Map logMap) {
        logMap.put("id", Util.uuid());
        logMap.put("insertTime",new Date());
        logMap.put("isdel",0);
        try {
            tLogDao.save(logMap);
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
            e.printStackTrace();
        }
    }

/*    @Override
    public List getLogs() {
        return null;
    }*/

//    @Override
//    public List getLogs() {
//        return tLogDao.getLogs();
//    }
}
