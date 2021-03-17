package com.njusc.npm.app.controller;

import com.njusc.base.ApiResult;
import com.njusc.base.PageResult;
import com.njusc.npm.metadata.entity.TAttendanceEntity;
import com.njusc.npm.metadata.entity.TUserEntity;
import com.njusc.npm.service.TAttendanceService;
import com.njusc.npm.service.TUserService;
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
import java.util.*;


/**
 * 上班打卡
 */


@Controller
@RequestMapping("/goToWork")
public class GoToWorkController {
    //日志
    protected static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    private TAttendanceService tAttendanceService;


    @RequestMapping("/toGoToWork")
    public String toGoToWork(HttpServletRequest request,Model model) {
        TUserEntity user = (TUserEntity)request.getSession().getAttribute("user");
        Map<String, Object> paramsGet = Util.paramToMap(request);//从前台获取到的信息
        Map paramsSelect = new HashMap();//送入service检索的条件

        String page = (String) paramsGet.get("page");

        paramsSelect.put("userName",user.getUserName());
        paramsSelect.put("userId",user.getId());

        if (paramsGet.get("year") != null && !String.valueOf(paramsGet.get("year")).trim().isEmpty()) {
            paramsSelect.put("year", String.valueOf(paramsGet.get("year")));
        }

        if (paramsGet.get("month") != null && !String.valueOf(paramsGet.get("month")).trim().isEmpty()) {
            paramsSelect.put("month", String.valueOf(paramsGet.get("month")));
        }

        if (paramsGet.get("day") != null && !String.valueOf(paramsGet.get("day")).trim().isEmpty()) {
            paramsSelect.put("day", String.valueOf(paramsGet.get("day")));
        }

        if (paramsGet.get("remark") != null && !String.valueOf(paramsGet.get("remark")).trim().isEmpty() && !String.valueOf(paramsGet.get("remark")).trim().equals("全部")) {
            paramsSelect.put("remark", String.valueOf(paramsGet.get("remark")));
        }

        Integer pageSize = 15;

        if (page != null && !"".equals(page) && !"0".equals(page)) {
            paramsSelect.put("page", Integer.parseInt(page));
        } else {
            page = "1";
            paramsSelect.put("page", Integer.parseInt(page));
        }

        paramsSelect.put("limit", pageSize);

        PageResult<TAttendanceEntity> pageResult = new PageResult<TAttendanceEntity>(tAttendanceService.getGoToWork(paramsSelect), paramsSelect);

        Integer totalPage = 1;
        if (pageResult.getCount() != null && pageResult.getCount() != 0) {
            totalPage = (pageResult.getCount() + pageSize - 1) / pageSize;
        }


        model.addAttribute("result", pageResult);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);

        return "/clockIn/goToWork";
    }

    /**
     * 新增上班打卡数据
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public ApiResult add(HttpServletRequest request, Model model){
        TUserEntity user = (TUserEntity)request.getSession().getAttribute("user");

        TAttendanceEntity tAttendanceEntity=new TAttendanceEntity();
        tAttendanceEntity.setId(Util.uuid());
        tAttendanceEntity.setUserId(user.getId());

        String goToWorkHour=tAttendanceService.getGoToWorkTime();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);

        String str=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+ calendar.get(Calendar.DATE)+" "+goToWorkHour;

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Date date=null;

        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (date.getTime() < (calendar.getTime()).getTime()) {
            tAttendanceEntity.setRemark("迟到");
        }else{
            tAttendanceEntity.setRemark("正常");
        }

        tAttendanceService.addGoToWork(tAttendanceEntity);

        return ApiResult.success();
    }
}
