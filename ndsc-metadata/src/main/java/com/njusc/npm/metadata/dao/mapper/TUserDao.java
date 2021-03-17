package com.njusc.npm.metadata.dao.mapper;

import com.njusc.npm.metadata.entity.TUserEntity;
import com.njusc.npm.metadata.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 人员表
 * 
 * @author Michael
 * @date 2021-01-18 10:52:41
 */
@Repository
public interface TUserDao extends BaseDao<TUserEntity,String> {

    public List<TUserEntity> getUsers(Map<String, Object> params);

    public TUserEntity selectUserById(Map<String, Object> params);

    public TUserEntity findUserById(Map<String, Object> params);

    public void updateRole(Map<String, Object> params);

    TUserEntity find(Map<String,Object> map);

    List<TUserEntity> findAll(Map<String,Object> map);

    @Override
    void save(TUserEntity ptProPlanEntity);

    void saveAll(List<TUserEntity> planEntities);

//    void update(TUserEntity ptProPlanEntity);

    void updateAll(List<TUserEntity> planEntities);

    void delete(String id);

    void deleteAll(List<String> id);

	List findUserAndDept(Map<String, Object> params);

	List<String> findAllPersonNo();

    List<TUserEntity> findAll2(Map<String,Object> map);

//    void updateTUser(TUserEntity t);

    public TUserEntity getIdByName(Map<String, Object> params);


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
