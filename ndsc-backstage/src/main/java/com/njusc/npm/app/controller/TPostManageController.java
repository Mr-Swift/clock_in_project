package com.njusc.npm.app.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.njusc.base.ApiResult;
import com.njusc.base.PageResult;
import com.njusc.npm.metadata.entity.*;
import com.njusc.npm.service.TLogService;
import com.njusc.npm.utils.util.StringUtil;
import com.njusc.npm.utils.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.log4j.Logger;
import com.njusc.npm.service.TPostManageService;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 岗位信息
 *
 * @author Michael
 * @date 2021-01-18 12:26:27
 */
@Controller
@RequestMapping("/tPostManage")
public class TPostManageController {

    //日志
    private static final Logger log = Logger.getLogger(TPostManageController.class);

    @Autowired
    private TPostManageService tPostManageService;

    @Autowired
    private TLogService tLogService;


    /**
     * 分页
     * @param deptName
     * @param postName
     * @param page
     * @param request
     * @return
     */
    @RequestMapping("/getPosts")
    public String getPosts(String deptName, String postName, String page, HttpServletRequest request){
        if(deptName == null){
            deptName = "";
        }
        if(postName == null){
            postName = "";
        }

        Map<String,Object> params = new HashMap<>();
        params.put("deptName",deptName.trim());
        params.put("postName",postName.trim());
        Integer pageSize = 15;
        if(page != null && !"".equals(page) && !"0".equals(page)){
            params.put("page",Integer.parseInt(page));
        }else{
            page = "1";
            params.put("page",Integer.parseInt(page));
        }
        params.put("size",pageSize);


        PageResult<TPostEntity> result = new PageResult<TPostEntity>(tPostManageService.findList(params),params);
        Integer totalPage = 1;
        if(result.getCount() != null && result.getCount() != 0){
            totalPage = (result.getCount() + pageSize -1)/pageSize;
        }
        request.setAttribute("page",page);
        request.setAttribute("totalPage",totalPage);
        request.setAttribute("result",result);
        request.setAttribute("deptName",deptName.trim());
        request.setAttribute("postName",postName.trim());
        return "tPostManage/PostManage";
    }

    /**
     * 岗位添加/编辑（打开页面不执行保存方法）
     * @return
     */
    @RequestMapping("/showInfo")
    public String showInfo(String id,Model model){
        List<TDeptEntity> depts = tPostManageService.findDeptList();
        List<TBasecodeEntity> baseCodes = tPostManageService.findPostTypeList();
        TPostEntity tPostManageEntity = new TPostEntity();
        if(StringUtil.isNullOrEmpty(id)){//编辑
            Map<String,Object> params = new HashMap<>();
            params.put("id",id);
            tPostManageEntity = tPostManageService.find(params);
            model.addAttribute("tPostManageEntity",tPostManageEntity);
        }
        model.addAttribute("depts",depts);
        model.addAttribute("baseCodes",baseCodes);
        return "tPostManage/postDialog";
    }

    /**
     * 岗位添加/编辑（执行方法）
     * @param deptId
     * @param postTypeId
     * @param postName
     * @return
     */
    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public ApiResult saveOrUpdate(String deptId, String postTypeId, String postName, String id, Model model, HttpServletRequest request){
        TUserEntity user= (TUserEntity) request.getSession().getAttribute("user");
        List<TDeptEntity> depts = tPostManageService.findDeptList();
        List<TBasecodeEntity> baseCodes = tPostManageService.findPostTypeList();
        TPostEntity tPostManageEntity = new TPostEntity();
        if(StringUtil.isNullOrEmpty(id)){//编辑
            Map<String,Object> params = new HashMap<>();
            params.put("id",id);
            tPostManageEntity = tPostManageService.find(params);
            model.addAttribute("tPostManageEntity",tPostManageEntity);
            tPostManageEntity.setDeptId(deptId);
            tPostManageEntity.setPostTypeId(postTypeId);
            tPostManageEntity.setPostName(postName);
//            tPostManageEntity.setInsertUser(user.getUserId());
            tPostManageService.update(tPostManageEntity);

            // 前端查询动作记录日志
            Map map = new HashMap();
            map.put("operationRemark","用户:["+user.getUserName()+"],[更新]表[t_post_manage].操作内容[对部门名称为："+tPostManageEntity.getDeptName()+" 岗位id为:"+ postTypeId+",岗位名称为: "+postName+"的岗位进行更新操作]");
            map.put("insertUser",user.getId());
            tLogService.saveLog(map);

        }else{
            tPostManageEntity.setId(Util.uuid());
            tPostManageEntity.setDeptId(deptId);
            tPostManageEntity.setPostTypeId(postTypeId);
            tPostManageEntity.setPostName(postName);
            tPostManageEntity.setInsertDate(new Date());
//            tPostManageEntity.setInsertUser(user.getUserId());
            tPostManageEntity.setIsdel(0);
            tPostManageService.save(tPostManageEntity);

            // 前端查询动作记录日志
            Map map = new HashMap();
            map.put("operationRemark","用户:["+user.getUserName()+"],[添加]表[t_post_manage]的数据.操作内容[添加部门id为："+deptId+ " 岗位id为:"+ postTypeId+",岗位名称为: "+postName+"的岗位信息]");
            map.put("insertUser",user.getId());
            tLogService.saveLog(map);

        }
        model.addAttribute("depts",depts);
        model.addAttribute("baseCodes",baseCodes);
        return ApiResult.success();
    }

    /**
     * 岗位名称存在校验
     * @param deptId
     * @param postTypeId
     * @param postName
     * @param response
     */
    @RequestMapping("/verifyPostName")
    @ResponseBody
    public ApiResult verifyPostName(String deptId, String postTypeId, String postName,HttpServletResponse response) {
        try {
            TPostEntity postManageEntity = new TPostEntity();
            postManageEntity.setDeptId(deptId);
            postManageEntity.setPostTypeId(postTypeId);
            postManageEntity.setPostName(postName);
            List<TPostEntity> postManageEntities = tPostManageService.verifyPostName(postManageEntity);
            Map map = new HashMap();
            if(postManageEntities.size() > 0){
                map.put("INFO", 0);
            }else{
                map.put("INFO", 1);
            }
            return ApiResult.success(map);
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage(), e);
            return ApiResult.failed(e.getMessage());
        }
    }

    /**
     * 删除岗位
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    @ResponseBody
   public ApiResult deleteById(String id, HttpServletRequest request){
        try {
            tPostManageService.deleteByPostId(id);
            TUserEntity user= (TUserEntity) request.getSession().getAttribute("user");
            // 前端查询动作记录日志
            Map map = new HashMap();
            map.put("operationRemark","用户:["+user.getUserName()+"],[删除]表[t_post_manage]id为："+id+"的数据.操作内容[对ID为:"+id+"的数据进行删除]");
            map.put("insertUser",user.getId());
            tLogService.saveLog(map);
            return ApiResult.success();
        }catch (Exception e){
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return ApiResult.failed();
        }
   }
}
