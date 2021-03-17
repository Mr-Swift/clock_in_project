package com.njusc.npm.app.controller;

import com.njusc.npm.metadata.entity.*;
import com.njusc.npm.service.TAttendanceService;
import com.njusc.npm.service.TAttendanceStatisticsService;
import com.njusc.npm.service.TAttendanceTimeService;
import com.njusc.npm.service.TUserService;
import com.njusc.npm.utils.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.*;

@SuppressWarnings("all")
/**
 * 考勤统计
 */
@Controller
@RequestMapping("/attendanceStatistics")
public class TAttendanceStatisticsController {
    //日志
    protected static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private TAttendanceStatisticsService tAttendanceStatisticsService;

    @Autowired
    private TUserService tUserService;

    @Autowired
    private TAttendanceTimeService tAttendanceTimeService;

    @Autowired
    private TAttendanceService tAttendanceService;

    @RequestMapping("/toAttendanceStatistics")
    public String toAttendanceStatistics(HttpServletRequest request, Model model) {

        List<TBasecodeEntity> attendanceTypeList = tAttendanceTimeService.getAttendanceType();
        model.addAttribute("attendanceTypeList", attendanceTypeList);

        List<TAttendanceTimeEntity> attendanceNameList = tAttendanceTimeService.getAll();
        model.addAttribute("attendanceNameList", attendanceNameList);


        Map<String, Object> paramsFromJsp = Util.paramToMap(request);//从前台获取到的信息
        Map<String, Object> params = new HashMap<>();

        if ((paramsFromJsp.get("month") == null || String.valueOf(paramsFromJsp.get("month")).trim().isEmpty()) && (paramsFromJsp.get("year") == null || String.valueOf(paramsFromJsp.get("year")).trim().isEmpty()) && paramsFromJsp.get("flag") == null) {
            params.put("month", getPresentDate().get("year") + "-" + getPresentDate().get("month"));
        } else {

            if (paramsFromJsp.get("month") != null && !String.valueOf(paramsFromJsp.get("month")).trim().isEmpty()) {
                params.put("month", String.valueOf(paramsFromJsp.get("month")));
            }
            if (paramsFromJsp.get("year") != null && !String.valueOf(paramsFromJsp.get("year")).trim().isEmpty()) {
                params.put("year", String.valueOf(paramsFromJsp.get("year")));
            }
        }

        if (paramsFromJsp.get("userName") != null && !String.valueOf(paramsFromJsp.get("userName")).trim().isEmpty()) {
            params.put("userName", String.valueOf(paramsFromJsp.get("userName")));
        }

        if (paramsFromJsp.get("clockInType") != null && !String.valueOf(paramsFromJsp.get("clockInType")).trim().isEmpty()) {
            params.put("attendanceType", String.valueOf(paramsFromJsp.get("attendanceType")));
        }

        if (paramsFromJsp.get("clockInName") != null && !String.valueOf(paramsFromJsp.get("clockInName")).trim().isEmpty()) {
            params.put("attendanceName", String.valueOf(paramsFromJsp.get("attendanceName")));
        }

        List<TAttendanceStatisticsEntity> statisticsList = tAttendanceStatisticsService.findAll(params);
        List<TAttendanceEntity> detailList = tAttendanceService.findDetail(params);

//        System.out.println(paramsFromJsp);
//        System.out.println(params);
//        System.out.println(statisticsList);
//        System.out.println(detailList);

        model.addAttribute("userName", params.get("userName"));
        model.addAttribute("year", params.get("year"));
        model.addAttribute("month", params.get("month"));
        model.addAttribute("clockInType", params.get("clockInType"));
        model.addAttribute("clockInName", params.get("clockInName"));

        model.addAttribute("statisticsList", statisticsList);
        model.addAttribute("detailList", detailList);


        return "attendance/attendanceStatistics";
    }


    @RequestMapping("/search")
    public String search(HttpServletRequest request, Model model) {

        List<TBasecodeEntity> attendanceTypeList = tAttendanceTimeService.getAttendanceType();
        model.addAttribute("attendanceTypeList", attendanceTypeList);

        List<TAttendanceTimeEntity> attendanceNameList = tAttendanceTimeService.getAll();
        model.addAttribute("attendanceNameList", attendanceNameList);


        Map<String, Object> paramsFromJsp = Util.paramToMap(request);//从前台获取到的信息
        Map<String, Object> params = new HashMap<>();


        if (paramsFromJsp.get("month") != null && !String.valueOf(paramsFromJsp.get("month")).trim().isEmpty()) {
            params.put("month", String.valueOf(paramsFromJsp.get("month")));
        }
        if (paramsFromJsp.get("year") != null && !String.valueOf(paramsFromJsp.get("year")).trim().isEmpty()) {
            params.put("year", String.valueOf(paramsFromJsp.get("year")));
        }


        if (paramsFromJsp.get("userName") != null && !String.valueOf(paramsFromJsp.get("userName")).trim().isEmpty()) {
            params.put("userName", String.valueOf(paramsFromJsp.get("userName")));
        }

        if (paramsFromJsp.get("clockInType") != null && !String.valueOf(paramsFromJsp.get("clockInType")).trim().isEmpty()) {
            params.put("attendanceType", String.valueOf(paramsFromJsp.get("attendanceType")));
        }

        if (paramsFromJsp.get("clockInName") != null && !String.valueOf(paramsFromJsp.get("clockInName")).trim().isEmpty()) {
            params.put("attendanceName", String.valueOf(paramsFromJsp.get("attendanceName")));
        }

        List<TAttendanceStatisticsEntity> statisticsList = tAttendanceStatisticsService.findAll(params);
        List<TAttendanceEntity> detailList = tAttendanceService.findDetail(params);

//        System.out.println(paramsFromJsp);
//        System.out.println(params);
//        System.out.println(statisticsList);
//        System.out.println(detailList);

        model.addAttribute("userName", params.get("userName"));
        model.addAttribute("year", params.get("year"));
        model.addAttribute("month", params.get("month"));
        model.addAttribute("clockInType", params.get("clockInType"));
        model.addAttribute("clockInName", params.get("clockInName"));

        model.addAttribute("statisticsList", statisticsList);
        model.addAttribute("detailList", detailList);

        model.addAttribute("hit", paramsFromJsp.get("hit"));


        return "attendance/attendanceStatistics";
    }


    /**
     * @return
     * @author Mr.Swift
     * 获取当前年、月、日
     */
    public Map getPresentDate() {
        Map<String, Integer> presentDateMap = new HashMap();

        /**
         * 获取当前日期，年、月、日（方法一）
         */
//        Date dateNow=new Date();
//        SimpleDateFormat yearNowSdf=new SimpleDateFormat("YYYY");
//        SimpleDateFormat monthNowSdf=new SimpleDateFormat("MM");
//        SimpleDateFormat dayNowSdf=new SimpleDateFormat("dd");
//        String yearNow= yearNowSdf.format(dateNow);
//        String monthNow=monthNowSdf.format(dateNow);
//        String dayNow=dayNowSdf.format(dateNow);

        /**
         * 获取当前日期，年、月、日（方法二）
         * 注：获取月份的时候应当+1
         */
        Calendar calendar1 = Calendar.getInstance(Locale.CHINA);
        int year = calendar1.get(Calendar.YEAR);//当前年
        int month = calendar1.get(Calendar.MONTH) + 1;//当前月
        int day = calendar1.get(Calendar.DATE);//当前日

        presentDateMap.put("year", year);
        presentDateMap.put("month", month);
        presentDateMap.put("day", day);

        return presentDateMap;

    }


    @RequestMapping("/extract")
    public void extract() throws ParseException {
        Map<String, Object> map = new HashMap<>();
        List<TUserEntity> userEntityList = tUserService.findUserInfo(map);

        int daysOfMonth = getDaysOfMonth();
        Date date = new Date();

        List<TAttendanceStatisticsEntity> tAttendanceStatisticsEntityList = new ArrayList<>();

        for (TUserEntity user : userEntityList
        ) {
            Map<String, Object> params = new HashMap<>();
            params.put("date", date);
            params.put("daysOfMonth", daysOfMonth);
            params.put("userId", user.getId());
            params.put("id", Util.uuid());
            tAttendanceStatisticsEntityList.add(tAttendanceStatisticsService.extract(params));
        }
        tAttendanceStatisticsService.saveList(tAttendanceStatisticsEntityList);
    }


    /**
     * @Mr.Swift 返回当前月一共有多少天
     */
    public int getDaysOfMonth() throws ParseException {

        /**
         * 方法一：
         */
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
//        String yearAndMonthStr = String.valueOf(year) + "-" + String.valueOf(month);
//        Date dateYearAndMonth=simpleDateFormat.parse(yearAndMonthStr);
//        Calendar calendar=Calendar.getInstance();
//        calendar.setTime(dateYearAndMonth);
//        Integer daysOfMonth=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//        return daysOfMonth;

        /**
         * 方法二：
         */
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
