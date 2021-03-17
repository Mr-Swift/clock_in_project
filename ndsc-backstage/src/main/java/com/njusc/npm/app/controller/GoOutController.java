package com.njusc.npm.app.controller;

import com.njusc.base.ApiResult;
import com.njusc.base.PageResult;
import com.njusc.npm.metadata.entity.TAttendanceEntity;
import com.njusc.npm.metadata.entity.TBasecodeEntity;
import com.njusc.npm.metadata.entity.TUserEntity;
import com.njusc.npm.service.TAttendanceService;
import com.njusc.npm.utils.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 外出打卡
 */
@Controller
@RequestMapping("/goOut")
@SuppressWarnings("all")

public class GoOutController {

    //日志
    protected static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private TAttendanceService tAttendanceService;

    @RequestMapping("/toGoOut")
    public String toGoOut(HttpServletRequest request, Model model) {
        List<String> remarksList= tAttendanceService.getOtherClockInType();

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

        PageResult<TAttendanceEntity> pageResult = new PageResult<TAttendanceEntity>(tAttendanceService.getGoOut(paramsSelect), paramsSelect);

        Integer totalPage = 1;
        if (pageResult.getCount() != null && pageResult.getCount() != 0) {
            totalPage = (pageResult.getCount() + pageSize - 1) / pageSize;
        }


        model.addAttribute("result", pageResult);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);

        model.addAttribute("remarksList",remarksList);
        return "/clockIn/goOut";
    }

    @RequestMapping("add")
    @ResponseBody
    public ApiResult add(HttpServletRequest request, Model model) {
        TUserEntity user = (TUserEntity)request.getSession().getAttribute("user");

        Map<String, Object> paramsGet = Util.paramToMap(request);//从前台获取到的信息
        Map params = new HashMap();//送入service检索的条件

        params.put("remark", paramsGet.get("remark"));
        params.put("id", Util.uuid());
        params.put("userId", user.getId());

        tAttendanceService.addGoOut(params);
        return ApiResult.success();

    }
}
