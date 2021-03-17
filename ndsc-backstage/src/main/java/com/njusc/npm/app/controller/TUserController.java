package com.njusc.npm.app.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.njusc.base.ApiResult;
import com.njusc.base.PageResult;
import com.njusc.npm.metadata.entity.TUserEntity;
import com.njusc.npm.service.TPostManageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.njusc.npm.metadata.entity.TRoleEntity;
import com.njusc.npm.service.TRoleService;
import com.njusc.npm.utils.enumeration.ResultCodeEnum;
import com.njusc.npm.utils.util.MD5;
import com.njusc.npm.utils.util.StringUtil;
import com.njusc.npm.utils.util.Util;
import org.apache.commons.lang.StringUtils;
import com.njusc.npm.app.util.SessionUtil;
import com.njusc.npm.metadata.entity.*;
import com.njusc.npm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * 人员表
 *
 * @author Michael
 * @date 2021-01-18 12:26:27
 */
@Controller
@RequestMapping("/tUser")
public class TUserController {

    //日志
    private static final Logger log = Logger.getLogger(TUserController.class);

    @Autowired
    private TUserService tUserService;
    @Autowired
    private TPostManageService tPostManageService;
    @Autowired
    private TUserPostService tUserPostService;
    @Autowired
    private TBasecodeService tBasecodeService;
    @Autowired
    private TDeptService tDeptService;
    @Autowired
    private TUserRoleService tUserRoleService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private TLogService tLogService;


    private List<String> taskUserId = new ArrayList<>();

    //员工信息管理
    @RequestMapping("/toUserInfo")
    public String toUserInfo(HttpServletRequest request,Model model) {
        Map<String, Object> paramsGet=Util.paramToMap(request);
        Map<String, Object> paramsSelect = new HashMap<>();

        if (paramsGet.get("userName") != null && !String.valueOf(paramsGet.get("userName")).trim().isEmpty()) {
            paramsSelect.put("userName", String.valueOf(paramsGet.get("userName")));
        }

        if (paramsGet.get("personNo") != null && !String.valueOf(paramsGet.get("personNo")).trim().isEmpty()) {
            paramsSelect.put("personNo", String.valueOf(paramsGet.get("personNo")));
        }

        if (paramsGet.get("dept") != null && !String.valueOf(paramsGet.get("dept")).trim().isEmpty()) {
            paramsSelect.put("deptId", String.valueOf(paramsGet.get("deptId")));
        }

        if (paramsGet.get("postName") != null && !String.valueOf(paramsGet.get("postName")).trim().isEmpty()) {
            paramsSelect.put("postName", String.valueOf(paramsGet.get("postName")));
        }

        if (paramsGet.get("userStatus") != null && !String.valueOf(paramsGet.get("userStatus")).trim().isEmpty()) {
            paramsSelect.put("userStatus", String.valueOf(paramsGet.get("userStatus")));
        }

        Integer pageSize = 15;
        paramsSelect.put("limit", pageSize);

        String page= (String) paramsGet.get("page");
        if (page != null && !"".equals(page) && !"0".equals(page)) {
            paramsSelect.put("page", Integer.parseInt(page));
        } else {
            page = "1";
            paramsSelect.put("page", Integer.parseInt(page));
        }

        PageResult<TUserEntity> result = new PageResult<>(tUserService.findUserInfo(paramsSelect), paramsSelect);

        Integer totalPage = 1;
        if (result.getCount() != null && result.getCount() != 0) {
            totalPage = (result.getCount() + pageSize - 1) / pageSize;
        }

        List<TDeptEntity> deptList=tDeptService.findDepts();
        System.out.println(deptList);

        List<String> userStatusList=tUserService.findUserStatus();


        String dept = (String) paramsGet.get("dept");
        model.addAttribute("userName", paramsGet.get("userName"));
        model.addAttribute("personNo", paramsGet.get("personNo"));
        model.addAttribute("dept", dept);
        model.addAttribute("postName", paramsGet.get("postName"));
        model.addAttribute("userStatus", paramsGet.get("userStatus"));

        model.addAttribute("deptList", deptList);
        model.addAttribute("userStatusList", userStatusList);
        model.addAttribute("result", result);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("page", page);



        return "/tUser/userinfo";
    }

    @Autowired
    private TRoleService tRoleService;


    @RequestMapping("/toGrantRole")
    public String toGrantRole(String id, Model model){
        TUserEntity user = tUserService.getUserById(id);
        List<TRoleEntity> roles = tRoleService.findRoleList();
        Map<String, Object> params = new HashMap<>();
        params.put("userId", user.getId());
        List<TUserRoleEntity> userRole = tUserRoleService.findAll(params);
        String roleId = "";
        for (int i = 0; i < userRole.size(); i++) {
            roleId+=userRole.get(i).getRoleId()+",";
        }
        model.addAttribute("user", user);
        model.addAttribute("roles",roles);
        model.addAttribute("roleId",roleId);
        return "tUser/userDialog";
    }

    @ResponseBody
    @RequestMapping("/grant")
    public ApiResult grantRole(String userId, String roleIds){
        TUserEntity user  = (TUserEntity) request.getSession().getAttribute("user");
        if (!StringUtils.isEmpty(userId)) {
            tUserService.updateRole(userId);
            Map logMap = new HashMap();
            logMap.put("operationRemark","用户:["+user.getUserName()+"],[更新]表[t_user_role]userId为"+userId+"的数据。操作内容[对userId为"+userId+"的数据进行删除(isDel=1)]");
            logMap.put("insertUser", user.getId());
            tLogService.saveLog(logMap);
            List<TUserRoleEntity> list = new ArrayList();
            String[] split = roleIds.split(",");
            for (String roleId: split) {
                TUserRoleEntity tUserRoleEntity = new TUserRoleEntity();
                tUserRoleEntity.setId(Util.uuid());
                tUserRoleEntity.setRoleId(roleId);
                tUserRoleEntity.setUserId(userId);
                tUserRoleEntity.setInsertDate(new Date());
                tUserRoleEntity.setInsertUser(user.getId());
                tUserRoleEntity.setIsdel(0);
                list.add(tUserRoleEntity);
            }
            if(roleIds.length()!= 0 && !roleIds.equals("")){
                tUserRoleService.saveAll(list);
                Map loggMap = new HashMap();
                loggMap.put("operationRemark","用户:["+user.getUserName()+"],[更新]表[t_user_role]userId为"+userId+"的数据。操作内容[对userId为"+userId+"的用户添加角色");
                loggMap.put("insertUser", user.getId());
                tLogService.saveLog(loggMap);
            }
            return ApiResult.success();
        } else {
            return ApiResult.failed(ResultCodeEnum.参数异常);
        }
    }

    @ResponseBody
    @RequestMapping("/resetPwd")
    public ApiResult resetPwd(String id){
        if (!StringUtils.isEmpty(id)) {
            TUserEntity user = tUserService.getUserById(id);
            user.setUserPassword(MD5.GetMD5Code("Njusc001"));
            tUserService.update(user);
            TUserEntity u = (TUserEntity) request.getSession().getAttribute("user");
            Map loggMap = new HashMap();
            loggMap.put("operationRemark","用户:["+u.getUserName()+"],[更新]表[t_user]id为"+id+"的用户的数据。操作内容[对id为"+id+"的密码进行重置]");
            loggMap.put("insertUser", u.getId());
            tLogService.saveLog(loggMap);
            return ApiResult.success();
        } else {
            return ApiResult.failed(ResultCodeEnum.参数异常);
        }

    }

    @RequestMapping("/getUsers")
    public String getUsers(String deptName, String personNo, String userName, String postName, Integer userStatus,String page,HttpServletRequest request){
        Map<String,Object> params = new HashMap<>();
        params.put("deptName",deptName);
        params.put("personNo",personNo);
        params.put("userName",userName);
        params.put("postName",postName);
        params.put("userStatus",userStatus);
        Integer pageSize = 15;
        if(page != null && !"".equals(page) && !"0".equals(page)){
            params.put("page",Integer.parseInt(page));
        }else{
            page = "1";
            params.put("page",Integer.parseInt(page));
        }
        params.put("size",pageSize);
        PageResult<TUserEntity> result = new PageResult<TUserEntity>(tUserService.findList(params),params);
        Integer totalPage = 1;
        if(result.getCount() != null && result.getCount() != 0){
            totalPage = (result.getCount() + pageSize -1)/pageSize;
        }
        Map m = new HashMap();
        m.put("deptName",deptName);
        m.put("personNo",personNo);
        m.put("userName",userName);
        m.put("postName",postName);
        m.put("userStatus",userStatus);
        PageResult<TUserEntity> noPage = new PageResult<TUserEntity>(tUserService.findList(m),m);
        request.setAttribute("page",page);
        request.setAttribute("totalPage",totalPage);
        request.setAttribute("result",result);
        request.setAttribute("noPage",noPage);
        return "tUser/UserManage";
    }

    //检查工号
    @RequestMapping("/checkPersonNo")
    @ResponseBody
    public String checkPersonNo(String personNo){
        String msg = "";
        List<String> allPersonNo = tUserService.findAllPersonNo();
        for (String str:allPersonNo){
            if(str.equals(personNo)){
                msg = "no";
            }
        }
        return msg;
    }

    @RequestMapping("/editUser")
    public String editUser(HttpServletRequest request,Model model) {
        Map<String, Object> paramsGet = Util.paramToMapNew(request);
        Map<String, Object> paramsSelect=new HashMap<>();

        List<TDeptEntity> deptList=tDeptService.findDepts();
        model.addAttribute("deptList",deptList);

        List<TPostEntity> postList=tPostManageService.findPosts();
        model.addAttribute("postList",postList);

        List<TBasecodeEntity> levelList= tBasecodeService.findEducations();
        model.addAttribute("levelList",levelList);

        List<TBasecodeEntity> marriageList= tBasecodeService.findMarriages();
        model.addAttribute("marriageList",marriageList);


        if (paramsGet.get("id") != null && !String.valueOf(paramsGet.get("id")).trim().isEmpty()) {
            paramsSelect.put("id", paramsGet.get("id"));
            TUserEntity tUserEntity=tUserService.find(paramsSelect);
            model.addAttribute("tUserEntity", tUserEntity);
        }

        return "/tUser/editUser";
    }


    //新增或修改员工信息
    @RequestMapping("/addUserInfo")
    @ResponseBody
    public String addUserInfo(String personNo, String username, String mobile,
                              String sex, String dept, String isDeptLeader,
                              String[] array, String hunyu, String university,
                              String workDate, String salary, String zhuangtai,
                              String idcard, String date, String minzu,
                              String hujiLocation, String juzhuLocation, String emergencyContact,
                              String emMobile, String major, String graduateDate,
                              String xueli, String zhuanzhengDate, String hetong1,
                              String hetong11, String hetong2, String hetong22,
                              String hetong3, String hetong33, String jiguan,
                              Integer iden, String uid, String oldPostId) throws ParseException {//iden用来判断添加数据还是修改数据
        String[] oldPostIdArray = oldPostId.split(",");
        Map mapLog = new HashMap();
        TUserEntity user= (TUserEntity) request.getSession().getAttribute("user");
        //将值转换成数据库对应的数字
        int sexInt, isDeptLeaderInt, zhuangtaiInt;
        String deptId = "", hunyuStr = "", xueliStr = "", idStr = "", isok = "0", id, user_id;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = formatter.format(new Date());
        if (sex.equals("男")){
            sexInt = 1;
        }else {
            sexInt = 2;
        }

        if (isDeptLeader.equals("否")){
            isDeptLeaderInt = 0;
        }else {
            isDeptLeaderInt = 1;
        }

        if (zhuangtai.equals("在职")){
            zhuangtaiInt = 1;
        }else {
            zhuangtaiInt = 2;
            taskUserId.add(uid);
        }
        if (hunyu.equals("未婚")){
            hunyuStr = "1";
        }else if (hunyu.equals("离异")){
            hunyuStr = "4";
        }else if (hunyu.equals("已婚未育")){
            hunyuStr = "2";
        }else {
            hunyuStr = "3";
        }

        if (xueli.equals("高中")){
            xueliStr = "5";
        }else if (xueli.equals("大专")){
            xueliStr = "6";
        }else if (xueli.equals("本科")){
            xueliStr = "7";
        }else if (xueli.equals("研究生")){
            xueliStr = "8";
        }else {
            xueliStr = "9";
        }
        List<TDeptEntity> allDept = tDeptService.findAll(null);
        for (int i = 0;i<allDept.size();i++){
            if (dept.equals(allDept.get(i).getDeptName())){
                deptId = allDept.get(i).getId();
            }
        }
        //将数据装入实体类
        TUserEntity tue = new TUserEntity();
        tue.setId(user_id = Util.uuid()); tue.setPersonNo(personNo); tue.setUserName(username);
        if (!salary.equals("")){
            tue.setSalary(Double.parseDouble(salary));
        }
        if (!date.equals("")){
            tue.setBirthDate(format.parse(date));
        }
        if (!graduateDate.equals("")){
            tue.setGraduateDate(format.parse(graduateDate));
        }
        if (!workDate.equals("")){
//            tue.setEntryCompanyDate(format.parse(workDate));
        }
        if (!zhuanzhengDate.equals("")){
            tue.setRegularWorkDate(format.parse(zhuanzhengDate));
        }
        if (!hetong1.equals("")){
            tue.setFirstContractStartDate(format.parse(hetong1));
        }
        if (!hetong11.equals("")){
            tue.setFirstContractEndDate(format.parse(hetong11));
        }
        if (!hetong2.equals("")){
            tue.setSecondContractStartDate(format.parse(hetong2));
        }
        if (!hetong22.equals("")){
            tue.setSecondContractEndDate(format.parse(hetong22));
        }
        if (!hetong3.equals("")){
            tue.setThirdContractStartDate(format.parse(hetong3));
        }
        if (!hetong33.equals("")){
            tue.setThirdContractEndDate(format.parse(hetong33));
        }

//        tue.setSex(sexInt); tue.setTelephone(mobile);
        tue.setUserStatus(zhuangtaiInt); tue.setCardNo(idcard);
        tue.setEthnic(minzu); tue.setNativePlace(jiguan); tue.setCensusRegisterAddress(hujiLocation);
        tue.setFamilyAddress(juzhuLocation); tue.setMarriageStatus(hunyuStr); tue.setHurryContractPerson(emergencyContact);
//        tue.setHurryContractTelephone(emMobile); tue.setSchool(university); tue.setNameOfMajor(major);
        tue.setHighLevel(xueliStr);
        tue.setDeptId(deptId);
        tue.setUserPassword(MD5.GetMD5Code("Njusc001")); tue.setIsdel(0);
        tue.setIsDeptManager(isDeptLeaderInt);
        tue.setInsertUser(SessionUtil.getUser().getId());
        tue.setInsertDate(formatter.parse(dateStr));
        //执行添加t_user表
        //如果iden是1,执行保存数据
        if (iden==1){
            tUserService.save(tue);
            //添加日志
            mapLog.put("operationRemark","用户:["+user.getUserName()+"][更新] 表 [t_user] id为"+user_id+"的数据。 操作内容[对员工id为"+user_id+"的信息进行添加操作]");
            mapLog.put("insertUser",user.getId());
            tLogService.saveLog(mapLog);
        }else {//iden不是1,执行修改数据
            tue.setId(uid);
//            tUserService.updateTUser(tue);
            //添加日志
            mapLog.put("operationRemark","用户:["+user.getUserName()+"][修改] 表 [t_user] id为"+uid+"的数据。 操作内容[对员工id为"+uid+"的信息进行更新操作]");
            mapLog.put("insertUser",user.getId());
            tLogService.saveLog(mapLog);
        }

        //添加数据到t_user_post表
        TUserPostEntity tupe = new TUserPostEntity();
        Map map = new HashMap();
        map.put("userId", uid);
        List<TUserPostEntity> allId = tUserPostService.findAll(map);
        int index = 0;
        for (int i = 0;i<array.length;i++){
            tupe.setPostManageId(array[i]);
            if (iden==1){
                tupe.setId(id = Util.uuid());
                tupe.setUserId(user_id);
                tupe.setInsertUser(SessionUtil.getUser().getId());
                tupe.setInsertDate(formatter.parse(dateStr));
                tupe.setIsdel(0);
                tUserPostService.save(tupe);
                //添加日志
                mapLog.put("operationRemark","用户:["+user.getUserName()+"][更新] 表 [t_user_post] id为"+id+"的数据。 操作内容[对员工id为"+user_id+"的岗位信息进行添加操作]");
                mapLog.put("insertUser",user.getId());
                tLogService.saveLog(mapLog);
            }else {
                if (allId!=null){
                    if (allId.size()>1&&allId.size()>array.length){
                        if (i==0){
                            for (int n = 0;n<allId.size();n++){
                                tUserPostService.deleteByUserId(allId.get(n).getUserId());
                            }
                        }

                        tupe.setId(id = Util.uuid());
                        tupe.setUserId(uid);
                        tupe.setInsertUser(SessionUtil.getUser().getId());
                        tupe.setInsertDate(formatter.parse(dateStr));
                        tupe.setIsdel(0);
                        tUserPostService.save(tupe);
                        //添加日志
                        mapLog.put("operationRemark","用户:["+user.getUserName()+"][修改] 表 [t_user_post] id为"+id+"的数据。 操作内容[对员工id为"+uid+"的岗位信息进行更新操作]");
                        mapLog.put("insertUser",user.getId());
                        tLogService.saveLog(mapLog);
                    }else {
                        if (index==0){
                            for (int n = 0;n<allId.size();n++){
                                tUserPostService.deleteByUserId(allId.get(n).getUserId());
                            }
                            index = -1;
                        }
                        tupe.setId(id = Util.uuid());
                        tupe.setUserId(uid);
                        tupe.setInsertUser(SessionUtil.getUser().getId());
                        tupe.setInsertDate(formatter.parse(dateStr));
                        tupe.setIsdel(0);
                        tUserPostService.save(tupe);
                        //添加日志
                        mapLog.put("operationRemark","用户:["+user.getUserName()+"][修改] 表 [t_user_post] id为"+id+"的数据。 操作内容[对员工id为"+uid+"的岗位信息进行更新操作]");
                        mapLog.put("insertUser",user.getId());
                        tLogService.saveLog(mapLog);
                    }
                }
            }
        }

        isok = "1";
        return isok;
    }

    //删除员工信息
    @RequestMapping("/del")
    @ResponseBody
    public String deleteById(String id, String userName){
        tUserPostService.deleteByUserId(id);
        tUserService.delete(id);
        TUserEntity user = (TUserEntity) request.getSession().getAttribute("user");
        Map mapLog = new HashMap();
        //添加日志
        mapLog.put("operationRemark","用户:["+user.getUserName()+"][更新] 表 [t_user] id为"+id+"的数据。 操作内容[对员工id为"+id+"的信息进行删除操作]");
        mapLog.put("insertUser",user.getId());
        tLogService.saveLog(mapLog);

        return "ok";
    }

    //分页查询
    @RequestMapping("/page")
    public ModelAndView page(String page, String userName, String personNo,
                             String deptId, String postManageId,
                             String userStatusS, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        session.setAttribute("userNameValue", userName);
        session.setAttribute("personNoValue", personNo);
        session.setAttribute("deptIdValue", deptId);
        session.setAttribute("postManageIdValue", postManageId);
        session.setAttribute("userStatusSValue", userStatusS);
        Integer userStatus = 0;
        List<TDeptEntity> allDept = tDeptService.findAll(null);
        //分页
        Map<String, Object> params = new HashMap<>();
        Integer pageSize = 15;
        if (page != null && !"".equals(page) && !"0".equals(page)) {
            params.put("page", Integer.parseInt(page));
        } else {
            page = "1";
            params.put("page", Integer.parseInt(page));
        }
        params.put("size", pageSize);
        //查询条件
        if (userStatusS != null) {
            if (userStatusS.equals("在职")) {
                userStatus = 1;
                params.put("userStatus", userStatus);
            } else if (userStatusS.equals("离职")) {
                userStatus = 2;
                params.put("userStatus", userStatus);
            }
        }
        if (deptId != null) {
            if (!deptId.equals("全部")) {
                for (int i = 0; i < allDept.size(); i++) {
                    if (deptId.equals(allDept.get(i).getDeptName())) {
                        deptId = allDept.get(i).getId();
                        params.put("deptId", deptId);
                    }
                }
            }
        }
        if (postManageId != null) {
            params.put("postName", postManageId);
        }
        params.put("userName", userName.replace("_","\\_"));
        params.put("personNo", personNo.replace("_","\\_"));
        List<TUserDeptEntity> listUser = tUserService.findUserAndDept(params);

        //将最高学历对应字典表的id转换为文字
        Map map = new HashMap();
        map.put("baseType", "ZGXL");
        List<TBasecodeEntity> allZGXL = tBasecodeService.findAll(map);
        for (int i = 0;i<listUser.size();i++){
            for (int j = 0;j<allZGXL.size();j++){
                if (listUser.get(i).getHighLevel()!=null){
                    if ((listUser.get(i).getHighLevel()).equals(allZGXL.get(j).getId())){
                        listUser.get(i).setHighLevel(allZGXL.get(j).getDataValue1());

                    }
                }
            }
        }
        PageResult<TUserDeptEntity> allUser = new PageResult<TUserDeptEntity>(listUser, params);
        String postNameCount = "";
        Integer totalPage = 1;
        if(allUser.getCount() != null && allUser.getCount() != 0){
            totalPage = (allUser.getCount() + pageSize -1)/pageSize;
        }
        List allPostManage = tPostManageService.findAll(null);
        List<TUserDeptEntity> allUserNoPage = tUserService.findUserAndDept(params);
        mav.addObject("YN", 1);
        mav.addObject("allUserNoPage",allUserNoPage);
        mav.addObject("allPostManage",allPostManage);
        mav.addObject("allZGXL", allZGXL);
        mav.addObject("page", page);
        mav.addObject("allUser", allUser);
        mav.addObject("totalPage", totalPage);
        mav.addObject("allDept", allDept);
        mav.setViewName("/tUser/userinfo");
        return mav;
    }
}