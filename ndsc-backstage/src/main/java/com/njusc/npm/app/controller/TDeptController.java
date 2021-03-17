package com.njusc.npm.app.controller;

import com.njusc.base.ApiResult;
import com.njusc.base.PageResult;
import com.njusc.npm.metadata.entity.TDeptEntity;
import com.njusc.npm.metadata.entity.TUserEntity;
import com.njusc.npm.service.*;
import com.njusc.npm.utils.util.StringUtil;
import com.njusc.npm.utils.util.Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


    /**
     * 部门信息
     *
     * @author Michael
     * @date 2021-01-18 12:26:28
     */
    @Controller
    @RequestMapping("/tDept")
    public class TDeptController {


        @Autowired
        private TUserService tuserService;
        @Autowired
        private TDeptService TDeptService;
        @Autowired
        private TLogService tLogService;

        @RequestMapping("/toDept")
        public String toDept(HttpServletRequest request, ModelMap modelMap, String page) {
            Map<String, Object> params = Util.paramToMapNew(request);
            Integer pageSize = 15;
            if (page != null && !"".equals(page) && !"0".equals(page)) {
                params.put("page", Integer.parseInt(page));
            } else {
                page = "1";
                params.put("page", Integer.parseInt(page));
            }
            params.put("size", pageSize);
            PageResult<TDeptEntity> result = new PageResult<TDeptEntity>(TDeptService.findAll(params), params);
            Integer totalPage = 1;
            if (result.getCount() != null && result.getCount() != 0) {
                totalPage = (result.getCount() + pageSize - 1) / pageSize;
            }
            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("result", result);
            return "/tDept/deptManage";
        }

        /**
         * 新增 修改
         *
         * @param request
         * @param modelMap
         * @return
         */
        @RequestMapping("/addDept")
        public String addDept(HttpServletRequest request, ModelMap modelMap) {
            Map<String, Object> params = Util.paramToMapNew(request);
            TDeptEntity entity = new TDeptEntity();
            if (StringUtil.isNullOrEmpty(params.get("id"))) {
                entity = TDeptService.find(params);
            }
            modelMap.addAttribute("entity", entity);

            return "/tDept/editDept";
        }

        /**
         * 验证部门名称是否存在
         *
         * @param response
         * @param deptName
         * @param oldName
         * @return
         */
        @RequestMapping("/verifyDeptName")
        @ResponseBody
        public ApiResult verifyDeptName(HttpServletResponse response, String deptName, String oldName) {
            String isExist = "0";
            if (StringUtil.isNullOrEmpty(oldName) && deptName.equals(oldName)) {
                isExist = "0";
            } else {
                List<TDeptEntity> deptEntity = TDeptService.findByDeptName(deptName);
                if (deptEntity.size() > 0) {
                    isExist = "1";
                }
            }
            Map map = new HashMap();
            map.put("isExist", isExist);
            return ApiResult.success(map);
        }


        /**
         * 新增 修改
         *
         * @return
         */
        @RequestMapping("/saveDept")
        public String saveDept(HttpServletRequest request, TDeptEntity tDeptEntity) {
            TUserEntity user = (TUserEntity) request.getSession().getAttribute("user");
            if (Util.n(tDeptEntity.getId())) {
                tDeptEntity.setId(Util.uuid());
                tDeptEntity.setInsertDate(new Date());
                tDeptEntity.setInsertUser(user.getId());
                tDeptEntity.setIsdel(0);
                TDeptService.save(tDeptEntity);//新增

                Map map = new HashMap();
                map.put("operationRemark", "用户:[" + user.getUserName() + "],[新增]表[t_dept].操作内容[对部门名称："+ tDeptEntity.getDeptName() +" 部门说明："+ tDeptEntity.getDeptRemark()+"进行新增操作]");
                map.put("insertUser", user.getId());
                tLogService.saveLog(map);


            } else {
                TDeptService.update(tDeptEntity);//修改

                Map map = new HashMap();
                map.put("operationRemark", "用户:[" + user.getUserName() + "],[修改]表[t_dept].操作内容[对部门名称："+ tDeptEntity.getDeptName() +" 部门说明："+tDeptEntity.getDeptRemark()+"进行修改操作]");
                map.put("insertUser", user.getId());
                tLogService.saveLog(map);


            }


            return "redirect:/tDept/toDept.do";
        }

        /**
         * 删除
         *
         * @param id
         * @param request
         * @return
         */
        @RequestMapping("/deleteById") //删除
        @ResponseBody
        public ApiResult deleteById(String id, HttpServletRequest request) {
            TUserEntity user = (TUserEntity) request.getSession().getAttribute("user");
            try {
                Map<String, Object> params = new HashMap<>();
                params.put("deptId", id);
                List<TUserEntity> userList = tuserService.findAll(params);
                if (Util.n(userList)) {
                    TDeptService.delete(id);

                    Map map = new HashMap();
                    map.put("operationRemark", "用户:[" + user.getUserName() + "],[删除]表[t_dept]id为[" + params.get("deptId") + "]的数据。操作内容[对ID为" + params.get("deptId") + "的部门进行删除]");
                    map.put("insertUser", user.getId());
                    tLogService.saveLog(map);

                } else {

                    for (int i = 0; i < userList.size(); i++) {
                        user = new TUserEntity();
                        user = userList.get(i);
                        user.setDeptId(null);
                        tuserService.update(user);

                        Map map = new HashMap();
                        map.put("operationRemark", "用户:[" + user.getUserName() + "],[删除]表[t_dept]id为[" + params.get("deptId") + "]的数据。操作内容[对ID为" + params.get("deptId") + "的部门进行删除]");
                        map.put("insertUser", user.getId());
                        tLogService.saveLog(map);

                    }
                    TDeptService.delete(id);

                    Map map = new HashMap();
                    map.put("operationRemark", "用户:[" + user.getUserName() + "],[删除]表[t_dept]id为[" + params.get("deptId") + "]的数据。操作内容[对ID为" + params.get("deptId") + "的部门进行删除]");
                    map.put("insertUser", user.getId());
                    tLogService.saveLog(map);

                }

                return ApiResult.success();
            } catch (Exception e) {
                e.printStackTrace();
                return ApiResult.failed();
            }


        }

    }
