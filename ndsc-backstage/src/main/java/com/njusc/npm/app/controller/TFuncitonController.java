package com.njusc.npm.app.controller;

import com.njusc.npm.app.util.SessionUtil;
import com.njusc.npm.metadata.entity.TFuncitonEntity;
import com.njusc.npm.metadata.entity.TUserEntity;
import com.njusc.npm.service.TFuncitonService;
import com.njusc.npm.service.TLogService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.Integer.parseInt;

/**
 * 菜单信息
 *
 * @author Michael
 * @date 2021-01-18 12:26:27
 */
@Controller
@RequestMapping("/tFunciton")
public class TFuncitonController {

    //日志
    private static final Logger log = Logger.getLogger(TFuncitonController.class);

    @Autowired
    private TFuncitonService tFuncitonService;

    @Autowired
    private TLogService tLogService;

    @RequestMapping("/add")                                                                                             //增加菜单模块
    @ResponseBody
    public String add(String funName,String funUrl,String funParentName,String orderNumber,String parentID) throws IOException {
        String check = check(funName,funUrl,funParentName,orderNumber,"insert");                               //调用验证方法
        if (check.equals("checkOK")) {
            if (!"".equals(parentID)) {
                Map<String, Object> firstMap = new HashMap<>();
                firstMap.put("id",parentID);
                TFuncitonEntity firstTFE = tFuncitonService.find(firstMap);
                if (!"0".equals(firstTFE.getParentId())) {
                    return "仅支持二级菜单，请点击父模块";
                }
            } else {
                parentID = "0";
            }
            String parentId = parentID;
            if (null == parentId) {
                return "添加失败，请点击父模块增加子模块";
            }
            Map<String, Object> map1 = new HashMap<>();
            map1.put("parentId", parentId);
            List<TFuncitonEntity> otherChildFunciton = tFuncitonService.findAll(map1);
            if  (otherChildFunciton.size()>0) {
                for (TFuncitonEntity tfeOtherChildFunciton : otherChildFunciton) {
                    if (funName.equals(tfeOtherChildFunciton.getFunName())) {                                           //验证同级模块中是否有与要增加的模块重名的模块
                        return "添加失败，该父模块下已有该模块名";
                    }
                }
            }//.replace(" ","").replace(" ","").replace(" ","")
            String url = funUrl;
            String name = funName;
            if ("".equals(url)) {                                                                                       //默认url
                url = name;
            }
            String order = orderNumber;
//            int min = 10;
            if (otherChildFunciton.size()>0) {                                                                          //验证是否已有子模块
                for (TFuncitonEntity tfeOtherChildFunciton2 : otherChildFunciton) {
                    if (parseInt(order) == tfeOtherChildFunciton2.getOrderNumber()) {
                        return "该排序已存在，请重新输入";
                    }
//                    if (min>tfeOtherChildFunciton2.getOrderNumber()) {                                                  //查找子模块中排序最小的模块
//                        min = tfeOtherChildFunciton2.getOrderNumber();
//                    }
                }
            }
//            if (min > parseInt(order)) {                                                                                //若新增模块为子模块中排序最小的模块
//                Map<String, Object> map3 = new HashMap<>();
//                map3.put("id",parentID);
//                TFuncitonEntity tfefu = tFuncitonService.find(map3);
//                tfefu.setFunUrl(url);
//                tFuncitonService.update(tfefu);                                                                         //则将父模块的url修改为新增模块的url
//            }
            TFuncitonEntity tfe = new TFuncitonEntity(url,name,parentId,parseInt(order));
            int id = 0;
            List<TFuncitonEntity> list = tFuncitonService.findAllWithDeleted(null);                               //id自增长
            for (TFuncitonEntity tf:list) {
                if (id<parseInt(tf.getId())) {
                    id = parseInt(tf.getId());
                }
            }
            id = id+1;
            tfe.setId(id+"");
            String userId = SessionUtil.getUser().getId();                                                              //获取当前用户id
            tfe.setInsertUser(userId);
            tfe.setInsertDate(new Date());                                                                              //获取当前系统时间
            tFuncitonService.save(tfe);                                                                                 //添加菜单
            Map<String,Object> log = new HashMap<>();                                                                   //日志
            TUserEntity user1 = SessionUtil.getUser();
            log.put("operationRemark","用户:["+user1.getUserName()+"],[添加]表[t_function]id为["+tfe.getId()+"]的数据。操作内容[对ID为"+tfe.getId()+"的菜单信息进行添加]");
            log.put("insertUser",user1.getId());
            tLogService.saveLog(log);
            return "添加成功";
        } else {
            return "添加失败，"+check;
        }
    }

    @RequestMapping("/select")                                                                                          //根据id查找并返回父类模块名
    @ResponseBody
    public String select(String id) {
        Map<String ,Object> map = new HashMap<>();
        map.put("id",id);
        TFuncitonEntity tfe =  tFuncitonService.find(map);
        return tfe.getFunName();
    }

    @RequestMapping("/init@")                                                                                           //显示初始化菜单
    public ModelAndView init() {
        List<TFuncitonEntity> list = tFuncitonService.findParentFunciton();                                             //查找父模块
        List<TFuncitonEntity> newList = new ArrayList<>();
        for (TFuncitonEntity function : list) {                                                                         //查询父模块是否有子模块
            List<TFuncitonEntity> childList = tFuncitonService.findChildFunciton(function.getId());
            List<TFuncitonEntity> newChildList = new ArrayList<>();
            if (childList.size()>0) {
                for (TFuncitonEntity function1 : childList) {
                    List<TFuncitonEntity> childList1 = tFuncitonService.findChildFunciton(function1.getId());
                    if (childList1.size()>0) {
                        function1.setChilds(childList1);                                                                //如果有子模块，则将子模块添加到父模块的childs属性中
                    }
                    newChildList.add(function1);
                }
                function.setChilds(newChildList);
            }
            newList.add(function);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("newList",newList);
        mav.setViewName("tFunciton/tFunciton");
        return mav;
    }

    @RequestMapping("/delete")                                                                                          //删除菜单
    @ResponseBody
    public String deletefun(String id,String funName,String funcitonURL,String functionParent,String functionOrder) {
        String check = check(funName,funcitonURL,functionParent,functionOrder,"delete");                       //调用验证方法
        if (check.equals("checkOK")) {
            if (id == null||id.equals("")) {
                return "删除失败，该模块不存在";
            }
            Map<String, Object> map1 = new HashMap<>();                                                                 //根据父模块名称获取id
            map1.put("funName", functionParent);
            String parentId = tFuncitonService.findParentIdByFunName(map1);
            Map<String, Object> map = new HashMap<>();                                                                  //根据数据找到要删除的菜单数据
            map.put("funName", funName);
            map.put("funUrl", funcitonURL);
            map.put("parentId", parentId);
            map.put("orderNumber", functionOrder);
            TFuncitonEntity tfe = tFuncitonService.find(map);
            if (!id.equals(tfe.getId())) {                                                                              //验证项目是否存在
                return "删除失败，请重新删除";
            }
            List<TFuncitonEntity> childList = tFuncitonService.findChildFunciton(id);                                   //查找要删除的菜单模块是否有子模块
            if (childList.size() > 0) {                                                                                 //如果有子模块，则先删除子模块
                for (TFuncitonEntity childTFE : childList) {
                    List<TFuncitonEntity> childList1 = tFuncitonService.findChildFunciton(childTFE.getId());
                    if (childList1.size()>0) {
                        for (TFuncitonEntity childTFE1 : childList1) {
                            tFuncitonService.delete(childTFE1.getId());
                        }
                    }
                    tFuncitonService.delete(childTFE.getId());
                }
            }
            tFuncitonService.delete(id);                                                                                //删除菜单
            Map<String,Object> log = new HashMap<>();                                                                   //日志
            TUserEntity user1 = SessionUtil.getUser();
            log.put("operationRemark","用户:["+user1.getUserName()+"],[删除]表[t_function]id为["+id+"]的数据。操作内容[对ID为"+id+"的菜单信息进行删除]");
            log.put("insertUser",user1.getId());
            tLogService.saveLog(log);
            return "删除成功";
        } else {
            return "删除失败，"+check;
        }
    }

    @RequestMapping("/update")                                                                                          //修改菜单数据
    @ResponseBody
    public String update(String id,String funName,String funcitonURL,String functionParent,String functionOrder) {
        String check = check(funName,funcitonURL,functionParent,functionOrder,"update");                       //调用验证方法
        if (check.equals("checkOK")) {
            if (id == null||id.equals("")) {                                                                            //验证菜单是否存在
                return "修改失败，该模块不存在";
            }
            Map<String ,Object> map = new HashMap<>();
            map.put("id",id);
            TFuncitonEntity tfe =  tFuncitonService.find(map);                                                          //根据id查找修改前的菜单数据
            //.replace(" ","").replace(" ","").replace(" ","")
            String url = funcitonURL;
            String name = funName;
            String order = functionOrder;
            Map<String, Object> map2 = new HashMap<>();
            map2.put("parentId", tfe.getParentId());
            List<TFuncitonEntity> otherChildFunciton = tFuncitonService.findAll(map2);
//            int min = 10;
            if (otherChildFunciton.size()>0) {                                                                          //验证是否已有子模块
                for (TFuncitonEntity tfeOtherChildFunciton2 : otherChildFunciton) {
                    if(parseInt(order) == tfeOtherChildFunciton2.getOrderNumber()&&parseInt(order)!=tfe.getOrderNumber()){
                        return "该排序已存在，请重新输入";
                    }
//                    if (min>tfeOtherChildFunciton2.getOrderNumber()) {                                                  //查找子模块中排序最小的模块
//                        min = tfeOtherChildFunciton2.getOrderNumber();
//                    }
                }
            }
//            if (min == parseInt(order)) {                                                                               //若新增模块为子模块中排序最小的模块
//                Map<String, Object> map3 = new HashMap<>();
//                map3.put("id",tfe.getParentId());
//                TFuncitonEntity tfefu = tFuncitonService.find(map3);
//                tfefu.setFunUrl(url);
//                tFuncitonService.update(tfefu);                                                                         //则将父模块的url修改为新增模块的url
//            }
            tfe.setFunName(name);
            tfe.setFunUrl(url);
            tfe.setOrderNumber(Integer.valueOf(order));
            String userId = SessionUtil.getUser().getId();                                                              //获取当前用户id
            tfe.setInsertUser(userId);
            tFuncitonService.update(tfe);                                                                               //修改菜单
            Map<String,Object> log = new HashMap<>();                                                                   //日志
            TUserEntity user1 = SessionUtil.getUser();
            log.put("operationRemark","用户:["+user1.getUserName()+"],[修改]表[t_function]id为["+tfe.getId()+"]的数据。操作内容[对ID为"+tfe.getId()+"的菜单信息进行修改]");
            log.put("insertUser",user1.getId());
            tLogService.saveLog(log);
            return "已修改";
        } else {
            return "修改失败，"+check;
        }
    }

    @RequestMapping("/onblur")                                                                                          //修改菜单数据
    @ResponseBody
    public String checkOnBlur(String funName,String method,String id) {
        funName = funName.replace(" ","");
        if (!"".equals(funName)) {                                                                                      //验证模块名是否为空
            Pattern namePattern = Pattern.compile("[`~!@#$^&*()=|{}':;,\\[\\].<>/?！￥…（）—【】‘；：”“。，、？_%+《》]*");
            String [] str = funName.split("");//Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
            for (String str1 : str) {
                Matcher nameCheck = namePattern.matcher(str1);
                if (nameCheck.matches()) {                                                                              //验证模块名是否含有特殊字符
                    if ("insert".equals(method)) {
                        return "添加失败，模块名包含非法字符，请重新输入";
                    } else {
                        return "模块名包含非法字符，请重新输入";
                    }
                }
            }
            if("".equals(id)){
                id = "0";
            }
            Map<String, Object> map1 = new HashMap<>();
            map1.put("parentId", id);
            List<TFuncitonEntity> otherChildFunciton = tFuncitonService.findAll(map1);
            if (otherChildFunciton.size() > 0) {
                for (TFuncitonEntity tfeOtherChildFunciton : otherChildFunciton) {
                    if (funName.equals(tfeOtherChildFunciton.getFunName())) {                                           //验证同级模块中是否有与要增加的模块重名的模块
                        if ("insert".equals(method)) {
                            return "添加失败，该父模块下已有该模块名";
                        } else {
                            return "该父模块下已有该模块名";
                        }
                    }
                }
            }
            if (funName.length() <= 20) {                                                                               //验证模块名长度
                return "名称可用";
            } else {
                return "名称过长，限定20位";
            }
        } else {
            if("insert".equals(method)){
                return "添加失败，功能模块名称不可为空";
            } else {
                return "功能模块名称不可为空";
            }
        }
    }

//    @RequestMapping("/ztree")                                                                                          //修改菜单数据
//    @ResponseBody
//    public List<Map<String,Object>> initZTree(String id) {
//        if(id == null){
//            id = "0";
//        }
//        List<Map<String,Object>> a = new ArrayList<>();
//        Map<String,Object> map2 = new HashMap<>();
//        map2.put("parentId",id);
//        List<TFuncitonEntity> list = tFuncitonService.findAll(map2);                                                    //查找父模块
//        for (TFuncitonEntity function : list) {                                                                         //查询父模块是否有子模块
//            Map<String, Object> map = new HashMap<>();
//            map.put("name", function.getFunName());
//            map.put("id", function.getId());
//            List<TFuncitonEntity> childList = tFuncitonService.findChildFunciton(function.getId());
//            if (childList.size() > 0) {
//                map.put("isParent", true);
//            }
//            a.add(map);
//        }
//        return a;
//    }
//    @RequestMapping("/selectf")                                                                                          //修改菜单数据
//    @ResponseBody
//    public String selectf(String id){
//        Map<String, Object> map = new HashMap<>();
//        map.put("id",id);
//        TFuncitonEntity tfe = tFuncitonService.find(map);
//        Map<String, Object> map2 = new HashMap<>();
//        map2.put("id",tfe.getParentId());
//        TFuncitonEntity tfe2 = tFuncitonService.find(map2);
//        String str = tfe.getFunName()+" "+tfe.getFunUrl()+" "+tfe2.getFunName()+" "+tfe.getOrderNumber();
//        return str;
//    }

    public String check(String name,String url,String parentName,String order,String method) {                          //验证菜单数据方法
        if (!( "".equals(name)&&"".equals(url)&&"".equals(parentName)&&"".equals(order) ) ) {                           //验证是否选择菜单
            if (!"".equals(name)) {                                                                                     //验证模块名与排序是否为空
                if (!"".equals(order)) {                                                                                //验证排序是否为空
                   Pattern namePattern = Pattern.compile("[`~!@#$^&*()=|{}':;,\\[\\].<>/?！￥…（）—【】‘；：”“。，、？_%+《》]*");
                   String [] str = name.split("");//Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
                   for (String str1 : str) {
                       Matcher nameCheck = namePattern.matcher(str1);
                       if (nameCheck.matches()) {                                                                       //验证模块名是否含有特殊字符
                           return "模块名包含非法字符，请重新输入";
                       }
                   }
                   Pattern orderPattern = Pattern.compile("[0-9]*");
                   Matcher isNum = orderPattern.matcher(order);
                   if (isNum.matches()) {                                                                               //验证排序是否为数字
                       return "checkOK";                                                                                //验证通过
                   } else {
                       return "排序内容包括非法字符，请重新输入，排序应为整数";
                   }
               } else {
                   return "排序不可为空";
               }
            } else {
                return "功能模块名称不可为空";
            }
        } else {
            if ("insert".equals(method)) {
                return "请输入数据";
            } else if ("delete".equals(method)) {
                return "请选择要删除的菜单";
            } else if ("update".equals(method)) {
                return "请选择要修改的菜单";
            } else {
                return "系统异常";
            }
        }
    }
}