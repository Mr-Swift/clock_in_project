package com.njusc.npm.app.controller;

import com.alibaba.fastjson.JSON;
import com.njusc.npm.app.util.SessionUtil;
import com.njusc.npm.metadata.entity.TFuncitonEntity;
import com.njusc.npm.metadata.entity.TRoleEntity;
import com.njusc.npm.metadata.entity.TRoleFunctionEntity;
import com.njusc.npm.metadata.entity.TUserEntity;
import com.njusc.npm.service.TFuncitonService;
import com.njusc.npm.service.TLogService;
import com.njusc.npm.service.TRoleFunctionService;
import com.njusc.npm.service.TRoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色和菜单关系信息
 *
 * @author Michael
 * @date 2021-01-18 12:26:27
 */
@Controller
@RequestMapping("/tRoleFunction")
public class TRoleFunctionController {

    //日志
    private static final Logger log = Logger.getLogger(TRoleFunctionController.class);


    @Autowired
    private TRoleFunctionService tRoleFunctionService;
    @Autowired
    private TRoleService tRoleService;
    @Autowired
    private TFuncitonService tFuncitonService;

    @Autowired
    private TLogService tLogService;

//    @RequestMapping(value = "/toAddRole", method = RequestMethod.GET)
//    @Token(save = true)
//    public String toAddRole(Model model, HttpServletRequest request, String id){
//        if(!StringUtils.isEmpty(id)){
//            Map<String,Object> params = new HashMap<>();
//            params.put("id", id);
//            model.addAttribute("data",tRoleService.find(params));
//        }
//        model.addAttribute("token", request.getSession(true).getAttribute("token"));//防止表单重复提交
//        return "tRoleFunction/roleAdd";
//    }
//    @RequestMapping("/roleUpdate1")
//    public String roleUpdate1(HttpServletRequest request, String roleName,Model model){
//
//        return "/tRole/roleManage";
//    }
    @RequestMapping("/roleUpdate")
    @ResponseBody
    public String update( String point1){

//        if(point1 !=null && !"".equals(point1)) {
//            //将前台字符转为中文
//            try {
//                point1 = java.net.URLDecoder.decode(point1, "UTF-8");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }
        //根据传回要修改的人查找菜单记录传回界面
        String roleName =point1;
        List list = new ArrayList();
        Map<String, Object> params = new HashMap<>();
        params.put("roleName", roleName);
        //model.addAttribute("data1", tRoleService.find(params));
        Map<String, Object> map = new HashMap<>();
        TRoleEntity tRoleEntity=tRoleService.find(params);
        map.put("roleId", tRoleEntity.getId());
        List<TRoleFunctionEntity> tRoleFunctionEntityList = tRoleFunctionService.findAll(map);
        List<TFuncitonEntity> tFunctionEntitiesList = tFuncitonService.findAll(null);
        if (tRoleFunctionEntityList.size() > 0) {
            for (int i = 0; i < tRoleFunctionEntityList.size(); i++) {
                for (int g = 0; g < tFunctionEntitiesList.size(); g++) {
                    if (tRoleFunctionEntityList.get(i).getFunctionId().equals(tFunctionEntitiesList.get(g).getParentId())) {
                        tRoleFunctionEntityList.remove(i);
                        i--;
                        break;
                    }
                }
            }
        }
        if (tRoleFunctionEntityList.size() > 0) {
            for (TRoleFunctionEntity tRoleFunctionEntity : tRoleFunctionEntityList) {
                list.add(tRoleFunctionEntity.getFunctionId());
            }
        }
        StringBuilder sb = new StringBuilder();
        String resultString = "";
        sb.append(tRoleEntity.getRoleName());
        sb.append(",");
        sb.append(tRoleEntity.getRemark());
        sb.append(",");
        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 1) {
                sb.append(list.get(i));
                sb.append(",");
            } else {
                sb.append(list.get(i));
            }
        }
        resultString = sb.toString();
//            System.out.println("最后拼接的字符串结果：" + resultString);
        //model.addAttribute("functionlist", resultString);

        //JSONObject j = new JSONObject();
        return resultString.toString() ;
    }
    //判断查找的人是否唯一
    @RequestMapping("/selectOnly")
    @ResponseBody
    public String selectOnly(HttpServletRequest request, String roleName){
        Map<String, Object> params = new HashMap<>();
        params.put("roleName",roleName);
        TRoleEntity tRoleEntity = new TRoleEntity();
        tRoleEntity = tRoleService.find(params);
        if(tRoleService.findList(params).size()>0 ){
            return roleName;
        }
        return "";
    }
    //判断添加的人是否唯一
    @RequestMapping("/isOnly")
    @ResponseBody
    public String isOnly(String addRoleName){
        Map<String, Object> params = new HashMap<>();
        params.put("roleName",addRoleName);
        TRoleEntity tRoleEntity = new TRoleEntity();
        if(tRoleService.findAll(params).size()>0 ){
            return addRoleName;
        }
        return "";
    }
    //修改角色名时进行判断是否唯一
    @RequestMapping("/isOnly2")
    @ResponseBody
    public String isOnly2(String updateRoleName, String oldName) {
        Map<String, Object> params = new HashMap<>();
        params.put("roleName", oldName);
        Map<String, Object> params2 = new HashMap<>();
        params2.put("roleName", updateRoleName);
        if (updateRoleName.equals(oldName)) {
            return "";
        }
       if (tRoleService.findAll(params2).size() > 0) {
            return updateRoleName;
        }
        return "";
    }

    //进行添加操作
    @RequestMapping("/roleSaveOrUpdate")
    @ResponseBody
    public String save(HttpServletRequest request,String menuIds,String roleName, String remark){
        TRoleEntity tRoleEntity = new TRoleEntity();
        try {
            String user = SessionUtil.getUser().getId();
            tRoleEntity.setRoleName(roleName);
            tRoleEntity.setRemark(remark);
            tRoleService.saveOrUpdate(tRoleEntity,menuIds,user);
            String suc="保存成功";
            //日志
            TUserEntity user1 = (TUserEntity)request.getSession().getAttribute("user");
            Map lo = new HashMap<>();
            lo.put("operationRemark","用户:["+user1.getUserName()+"],[添加]表[t_role][t_role_function]id为["+tRoleEntity.getId()+"]的数据。操作内容[对ID为"+tRoleEntity.getId()+"的角色信息进行添加]");
            lo.put("insertUser",user1.getId());
            tLogService.saveLog(lo);
            return suc.toString();
        }catch (Exception e){
            log.error(e.getMessage());
            String fal="操作失败";
            return  fal;
        }
    }
    //进行修改操作
    @RequestMapping("/roleUpdate2")
    @ResponseBody
    public String update(HttpServletRequest request,String menuIds,String roleName, String remark,String oldName){
        TRoleEntity tRoleEntity = new TRoleEntity();
        try {

            String user = SessionUtil.getUser().getId();
            tRoleEntity.setRoleName(roleName);
            tRoleEntity.setRemark(remark);
            tRoleService.saveUpdate(tRoleEntity,menuIds,user,oldName);
            String suc="保存成功";
            //日志
            TRoleEntity tRoleEntity2 = new TRoleEntity();
            Map<String, Object> params = new HashMap<>();
            params.put("roleName",oldName);
            tRoleEntity2 = tRoleService.find(params);
            TUserEntity user1 = (TUserEntity)request.getSession().getAttribute("user");
            Map lo = new HashMap<>();
            lo.put("operationRemark","用户:["+user1.getUserName()+"],[修改]表[t_role][t_role_function]id为["+tRoleEntity2.getId()+"]的数据。操作内容[对ID为"+tRoleEntity2.getId()+"的角色信息进行修改]");
            lo.put("insertUser",user1.getId());
            tLogService.saveLog(lo);
            return suc.toString();
        }catch (Exception e){
            log.error(e.getMessage());
            String fal="操作失败";
            return  fal;
        }
    }
    //进行删除操作
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(HttpServletRequest request,String delName) {
        Map<String,Object> params = new HashMap<>();
        params.put("roleName",delName);
        TRoleEntity tRoleEntity=tRoleService.find(params);
        //tRoleService.delete(tRoleEntity.getId());
        tRoleService.delRole(tRoleEntity.getId());
        String s="成功";
        //日志
        TUserEntity user1 = (TUserEntity)request.getSession().getAttribute("user");
        Map lo = new HashMap<>();
        lo.put("operationRemark","用户:["+user1.getUserName()+"],[删除]表[t_role][t_role_function]id为["+tRoleEntity.getId()+"]的数据。操作内容[对ID为"+tRoleEntity.getId()+"的角色信息进行删除]");
        lo.put("insertUser",user1.getId());
        tLogService.saveLog(lo);
        return s;
    }

    @RequestMapping("/init")  //显示菜单
    public List init(Model model){
        StringBuilder jsonTree1 = new StringBuilder();
        jsonTree1.append("[");
        List<TFuncitonEntity> list = tFuncitonService.findParentFunciton();
        List<TFuncitonEntity> newList1 = new ArrayList<>();

        System.out.println(newList1);

        int j = 0;
        for (TFuncitonEntity function : list) {
            int i= 0 ;
            List<TFuncitonEntity> childList = tFuncitonService.findChildFunciton(function.getId());
            jsonTree1.append("{\"id\":").append(function.getId()).append(",");

            jsonTree1.append("\"name\":\"").append("\"").append(function.getFunName()).append("\"");
            if(childList.size()>0){
                jsonTree1.append(",");
                function.setChilds(childList);
                jsonTree1.append("children:[");
                for (TFuncitonEntity function2 :childList) {

                    jsonTree1.append("{\"id\":").append(function2.getId()).append(",");
                    jsonTree1.append("\"name\":\"").append("\"").append(function2.getFunName()).append("\"").append("\"}");
                    if(i!=childList.size()-1){jsonTree1.append(",");}
                    i++;
                }
                jsonTree1.append("]");
            }
            jsonTree1.append("}");
            if(j!=list.size()-1){jsonTree1.append(",");}
            j++;
            newList1.add(function);
        }
        jsonTree1.append("]");
        String json=JSON.toJSONString(newList1);
        model.addAttribute("jsonTree1",jsonTree1.toString());
        return newList1;
    }


}
