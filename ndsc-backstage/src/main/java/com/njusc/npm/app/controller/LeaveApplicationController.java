package com.njusc.npm.app.controller;

import com.njusc.base.ApiResult;
import com.njusc.base.PageResult;
import com.njusc.npm.app.util.SessionUtil;
import com.njusc.npm.metadata.entity.TBasecodeEntity;
import com.njusc.npm.metadata.entity.TLeaveEntity;
import com.njusc.npm.metadata.entity.TUserEntity;
import com.njusc.npm.service.TLeaveService;
import com.njusc.npm.utils.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 请假申请
 */
@SuppressWarnings("all")

@Controller
@RequestMapping("/leaveApplication")
public class LeaveApplicationController {
    //日志
    protected static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private TLeaveService tLeaveService;

    @RequestMapping("/toLeaveApplication")
    public String toLeaveApplication(HttpServletRequest request,Model model) {
        List<TBasecodeEntity> leaveTypeList= tLeaveService.selectAllLeaveType();
        model.addAttribute("leaveTypeList", leaveTypeList);

        TUserEntity user= SessionUtil.getUser();
        Map<String, Object> paramsGet = Util.paramToMap(request);//从前台获取到的信息
        Map paramsSelect = new HashMap();//送入service检索的条件

        String page = (String) paramsGet.get("page");

        paramsSelect.put("userName",user.getUserName());
        paramsSelect.put("userId",user.getId());


        if (paramsGet.get("leaveType") != null && !String.valueOf(paramsGet.get("leaveType")).trim().isEmpty()) {
            paramsSelect.put("leaveType", String.valueOf(paramsGet.get("leaveType")));
        }

        if (paramsGet.get("checkStatus") != null && !String.valueOf(paramsGet.get("checkStatus")).trim().isEmpty()) {
            paramsSelect.put("checkStatus", String.valueOf(paramsGet.get("checkStatus")));
        }

        Integer pageSize = 15;

        if (page != null && !"".equals(page) && !"0".equals(page)) {
            paramsSelect.put("page", Integer.parseInt(page));
        } else {
            page = "1";
            paramsSelect.put("page", Integer.parseInt(page));
        }

        paramsSelect.put("limit", pageSize);

        PageResult<TLeaveEntity> pageResult = new PageResult<TLeaveEntity>(tLeaveService.selectAllApplicationData(paramsSelect),paramsSelect);

        Integer totalPage = 1;
        if (pageResult.getCount() != null && pageResult.getCount() != 0) {
            totalPage = (pageResult.getCount() + pageSize - 1) / pageSize;
        }


        model.addAttribute("result", pageResult);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);



        return "/leave/leaveApplication";
    }


    @RequestMapping("/add")
    public String add(HttpServletRequest request, Model model) {
        List<TBasecodeEntity> leaveTypeList= tLeaveService.selectAllLeaveType();
        model.addAttribute("leaveTypeList", leaveTypeList);

        Map<String, Object> paramsFromJsp = Util.paramToMap(request);//从前台获取到的信息
        Map<String, Object> params=new HashMap<>();

        if(paramsFromJsp.get("id") != null && !String.valueOf(paramsFromJsp.get("id")).trim().isEmpty()){
            params.put("id",String.valueOf(paramsFromJsp.get("id")));
            TLeaveEntity tLeaveEntity = tLeaveService.findLeaveApplication(params);

            model.addAttribute("id",String.valueOf(paramsFromJsp.get("id")));
            model.addAttribute("tLeaveEntity",tLeaveEntity);
        }

        return "/leave/addLeaveApplication";
    }


    @RequestMapping("/save")
    @ResponseBody
    public ApiResult save(HttpServletRequest request, Model model) throws ParseException {
        Map<String, Object> paramsFromJsp = Util.paramToMap(request);//从前台获取到的信息
        TLeaveEntity tLeaveEntity=new TLeaveEntity();

        if (paramsFromJsp.get("leaveType") != null && !String.valueOf(paramsFromJsp.get("leaveType")).trim().isEmpty()) {
            tLeaveEntity.setLeaveType(String.valueOf(paramsFromJsp.get("leaveType")));
        }

        if (paramsFromJsp.get("leaveReason") != null && !String.valueOf(paramsFromJsp.get("leaveReason")).trim().isEmpty()) {
            tLeaveEntity.setLeaveReason(String.valueOf(paramsFromJsp.get("leaveReason")));
        }

        StringBuffer leaveStartDate = new StringBuffer();
        StringBuffer leaveEndDate = new StringBuffer();

        if (paramsFromJsp.get("startDay") != null && !String.valueOf(paramsFromJsp.get("startDay")).trim().isEmpty()) {
            leaveStartDate.append(String.valueOf(paramsFromJsp.get("startDay")));
        }

        leaveStartDate.append(" ");

        if (paramsFromJsp.get("startHour") != null && !String.valueOf(paramsFromJsp.get("startHour")).trim().isEmpty()) {
            leaveStartDate.append(String.valueOf(paramsFromJsp.get("startHour")));
        }
        leaveStartDate.append(":");

        if (paramsFromJsp.get("startMinute") != null && !String.valueOf(paramsFromJsp.get("startMinute")).trim().isEmpty()) {
            leaveStartDate.append(String.valueOf(paramsFromJsp.get("startMinute")));
        }
        leaveStartDate.append(":");

        if (paramsFromJsp.get("startSecond") != null && !String.valueOf(paramsFromJsp.get("startSecond")).trim().isEmpty()) {
            leaveStartDate.append(String.valueOf(paramsFromJsp.get("startSecond")));
        }


        if (paramsFromJsp.get("endDay") != null && !String.valueOf(paramsFromJsp.get("endDay")).trim().isEmpty()) {
            leaveStartDate.append(String.valueOf(paramsFromJsp.get("endDay")));
        }

        leaveEndDate.append(" ");

        if (paramsFromJsp.get("endHour") != null && !String.valueOf(paramsFromJsp.get("endHour")).trim().isEmpty()) {
            leaveStartDate.append(String.valueOf(paramsFromJsp.get("endHour")));
        }
        leaveEndDate.append(":");

        if (paramsFromJsp.get("endMinute") != null && !String.valueOf(paramsFromJsp.get("endMinute")).trim().isEmpty()) {
            leaveStartDate.append(String.valueOf(paramsFromJsp.get("endMinute")));
        }
        leaveEndDate.append(":");

        if (paramsFromJsp.get("endSecond") != null && !String.valueOf(paramsFromJsp.get("endSecond")).trim().isEmpty()) {
            leaveStartDate.append(String.valueOf(paramsFromJsp.get("endSecond")));
        }

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        tLeaveEntity.setLeaveStartDate(simpleDateFormat.parse(String.valueOf(leaveStartDate)));
        tLeaveEntity.setLeaveEndDate(simpleDateFormat.parse(String.valueOf(leaveEndDate)));

        tLeaveEntity.setInsertUser(SessionUtil.getUser().getId());
        tLeaveEntity.setInsertDate(new Date());

        if (paramsFromJsp.get("id") != null && !String.valueOf(paramsFromJsp.get("id")).trim().isEmpty()) {
            tLeaveEntity.setId(String.valueOf(paramsFromJsp.get("id")));
            tLeaveService.update(tLeaveEntity);
        }else{
            tLeaveEntity.setId(Util.uuid());
            tLeaveService.save(tLeaveEntity);
        }
        return ApiResult.success();
    }


    @RequestMapping("/delete")
    @ResponseBody
    public ApiResult delete(HttpServletRequest request, Model model) {
        Map<String, Object> paramsFromJsp = Util.paramToMap(request);//从前台获取到的信息
        String id = String.valueOf(paramsFromJsp.get("id"));
        tLeaveService.delete(id);
        return ApiResult.success();
    }


}
