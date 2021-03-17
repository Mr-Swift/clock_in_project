package com.njusc.npm.service;

import com.njusc.npm.metadata.entity.TFuncitonEntity;
import com.njusc.base.bean.BaseService;
import java.util.List;
import java.util.Map;

/**
 * 菜单信息
 *
 * @author Michael
 * @date 2021-01-18 10:52:41
 */
public interface TFuncitonService extends BaseService<TFuncitonEntity,String> {
    public List<TFuncitonEntity> findFunciton2();

    List<TFuncitonEntity> getFunctionListAdmin();
    List<TFuncitonEntity> getNextFunctionListAdmin(Map<String, Object> param);
    List<TFuncitonEntity> getFunctionList(String userId);
    List<TFuncitonEntity> getNextFunctionList(Map<String, Object> param);

    List<TFuncitonEntity> findParentFunciton();
    List<TFuncitonEntity> findChildFunciton(String id);
    String findParentIdByFunName(Map map);
    List<TFuncitonEntity> findAllWithDeleted(Map map);
    TFuncitonEntity findFunctionByFunNameAndID(Map map);
}

