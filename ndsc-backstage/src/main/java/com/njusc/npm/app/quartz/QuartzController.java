package com.njusc.npm.app.quartz;

import com.njusc.npm.metadata.entity.TAttendanceStatisticsEntity;
import com.njusc.npm.metadata.entity.TUserEntity;
import com.njusc.npm.service.TAttendanceStatisticsService;
import com.njusc.npm.service.TUserService;
import com.njusc.npm.utils.util.Util;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/quartz")
public class QuartzController {
    private final Logger log = Logger.getLogger(getClass());
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Autowired
    private TAttendanceStatisticsService tAttendanceStatisticsService;

    @Autowired
    private TUserService tUserService;

    @RequestMapping("/extract")
    @ResponseBody
    public String extract() throws ParseException {
        Map<String,Object> map = new HashMap<>();
        List<TUserEntity> userEntityList= tUserService.findUserInfo(map);

        int daysOfMonth=getDaysOfMonth();
        Date date = new Date();

        List<TAttendanceStatisticsEntity> tAttendanceStatisticsEntityList=new ArrayList<>();

        for (TUserEntity user:userEntityList
        ) {
            Map<String,Object> params=new HashMap<>();
            params.put("date",date);
            params.put("daysOfMonth",daysOfMonth);
            params.put("userId",user.getId());
            params.put("id", Util.uuid());
            tAttendanceStatisticsEntityList.add(tAttendanceStatisticsService.extract(params));
        }
        tAttendanceStatisticsService.saveList(tAttendanceStatisticsEntityList);
        return "ok";
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
