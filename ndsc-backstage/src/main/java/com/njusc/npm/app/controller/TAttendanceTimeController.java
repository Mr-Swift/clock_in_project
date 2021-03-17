package com.njusc.npm.app.controller;

import com.njusc.base.ApiResult;
import com.njusc.npm.app.util.SessionUtil;
import com.njusc.npm.metadata.entity.TAttendanceTimeEntity;
import com.njusc.npm.metadata.entity.TBasecodeEntity;
import com.njusc.npm.service.TAttendanceService;
import com.njusc.npm.service.TAttendanceTimeService;
import com.njusc.npm.utils.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 考勤时间管理
 */
@Controller
@RequestMapping("/attendanceTime")
public class TAttendanceTimeController {

    //日志
    protected static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private TAttendanceTimeService tAttendanceTimeService;

    @RequestMapping("/toAttendanceTime")
    public String toAttendanceTime(HttpServletRequest request, Model model) {
        List<TBasecodeEntity> attendanceTypeList = tAttendanceTimeService.getAttendanceType();
        model.addAttribute("attendanceTypeList", attendanceTypeList);


        String loginId = SessionUtil.getUser().getId();
//        Map<String, Object> paramsGet = Util.paramToMap(request);//从前台获取到的信息
//        Map paramsSelect = new HashMap();//送入service检索的条件
        List<TAttendanceTimeEntity> dataList= tAttendanceTimeService.getAll();

        model.addAttribute("dataList", dataList);

        return "/attendance/attendanceTime";
    }


    @RequestMapping("add")
    public String add(HttpServletRequest request, Model model) {
        List<TBasecodeEntity> attendanceTypeList = tAttendanceTimeService.getAttendanceType();
        model.addAttribute("attendanceTypeList", attendanceTypeList);

        Map<String, Object> paramsFromJsp = Util.paramToMap(request);//从前台获取到的信息
        Map<String, Object> params=new HashMap<>();

        if(paramsFromJsp.get("id") != null && !String.valueOf(paramsFromJsp.get("id")).trim().isEmpty()){
            params.put("id",String.valueOf(paramsFromJsp.get("id")));
            TAttendanceTimeEntity tAttendance=tAttendanceTimeService.find(params);
            model.addAttribute("id",String.valueOf(paramsFromJsp.get("id")));
            model.addAttribute("tAttendance",tAttendance);
        }

        return "/attendance/addAttendanceTime";
    }



    @RequestMapping("save")
    @ResponseBody
    public ApiResult save(HttpServletRequest request, Model model) {
        Map<String, Object> paramsFromJsp = Util.paramToMap(request);//从前台获取到的信息
        TAttendanceTimeEntity tAttendanceTimeEntity=new TAttendanceTimeEntity();

        if (paramsFromJsp.get("clockInName") != null && !String.valueOf(paramsFromJsp.get("clockInName")).trim().isEmpty()) {
            tAttendanceTimeEntity.setClockInName(String.valueOf(paramsFromJsp.get("clockInName")));
        }

        if (paramsFromJsp.get("attendanceType") != null && !String.valueOf(paramsFromJsp.get("attendanceType")).trim().isEmpty()) {
            tAttendanceTimeEntity.setClockInType(String.valueOf(paramsFromJsp.get("attendanceType")));
        }

        if (paramsFromJsp.get("clockInRemark") != null && !String.valueOf(paramsFromJsp.get("clockInRemark")).trim().isEmpty()) {
            tAttendanceTimeEntity.setClockInRemark(String.valueOf(paramsFromJsp.get("clockInRemark")));
        }

        StringBuffer clockInTime=new StringBuffer();

        if (paramsFromJsp.get("date") != null && !String.valueOf(paramsFromJsp.get("date")).trim().isEmpty()) {
            clockInTime.append(String.valueOf(paramsFromJsp.get("date")));
        }

        clockInTime.append(" ");

        if (paramsFromJsp.get("hour") != null && !String.valueOf(paramsFromJsp.get("hour")).trim().isEmpty()) {
            clockInTime.append(String.valueOf(paramsFromJsp.get("hour")));
        }
        clockInTime.append(":");

        if (paramsFromJsp.get("minute") != null && !String.valueOf(paramsFromJsp.get("minute")).trim().isEmpty()) {
            clockInTime.append(String.valueOf(paramsFromJsp.get("minute")));
        }
        clockInTime.append(":");

        if (paramsFromJsp.get("second") != null && !String.valueOf(paramsFromJsp.get("second")).trim().isEmpty()) {
            clockInTime.append(String.valueOf(paramsFromJsp.get("second")));
        }

        tAttendanceTimeEntity.setClockInTime(clockInTime.toString());
        tAttendanceTimeEntity.setInsertUser(SessionUtil.getUser().getId());
        tAttendanceTimeEntity.setInsertDate(new Date());

        if (paramsFromJsp.get("id") != null && !String.valueOf(paramsFromJsp.get("id")).trim().isEmpty()) {
            tAttendanceTimeEntity.setId(String.valueOf(paramsFromJsp.get("id")));
            tAttendanceTimeService.update(tAttendanceTimeEntity);
        }else{
            tAttendanceTimeEntity.setId(Util.uuid());
            tAttendanceTimeService.save(tAttendanceTimeEntity);
        }

        return ApiResult.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ApiResult delete(HttpServletRequest request, Model model) {
        Map<String, Object> paramsFromJsp = Util.paramToMap(request);//从前台获取到的信息
        String id = String.valueOf(paramsFromJsp.get("id"));
        tAttendanceTimeService.delete(id);
        return ApiResult.success();
    }
}
