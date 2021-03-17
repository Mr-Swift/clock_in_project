package com.njusc.npm.service;

import com.njusc.npm.metadata.entity.TUserDeptEntity;
import com.njusc.npm.metadata.entity.TUserEntity;
import com.njusc.base.bean.BaseService;

import java.util.List;
import java.util.Map;


/**
 * 人员表
 *
 * @author Michael
 * @date 2021-01-18 10:52:41
 */
public interface TUserService extends BaseService<TUserEntity,String> {
    List<TUserDeptEntity> findUserAndDept(Map<String, Object> params);

    public TUserEntity getUserById(String id);

    public List<TUserEntity> findList(Map<String, Object> params);

    public TUserEntity findUser(String id);

    public void updateRole(String userId);

    List<String> findAllPersonNo();

    List<TUserEntity> findAll2(Map<String,Object> map);

//    void updateTUser(TUserEntity t);

    TUserEntity getIdByName(Map<String, Object> params);


    /**
     * 查询用户表所有的用户状态，用于下拉列表
     * @return
     */
    List<String> findUserStatus();

    /**
     * 员工信息管理数据显示
     * @param params
     * @return
     */
    List<TUserEntity> findUserInfo(Map<String, Object> params);




}

