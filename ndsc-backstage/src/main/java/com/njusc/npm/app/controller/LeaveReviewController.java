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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 请假审核
 */
@SuppressWarnings("all")

@Controller
@RequestMapping("/leaveReview")
public class LeaveReviewController {
    //日志
    protected static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private TLeaveService tLeaveService;

    @RequestMapping("/check")
    @ResponseBody
    public ApiResult check(HttpServletRequest request, Model model) {
        Map<String, Object> paramsFromJsp = Util.paramToMap(request);//从前台获取到的信息
        String id = String.valueOf(paramsFromJsp.get("id"));
        Map<String, Object> params=new HashMap<>();
        params.put("checkDate", new Date());
        params.put("checkUser", SessionUtil.getUser().getId());
        params.put("id", id);
        tLeaveService.check(params);
        return ApiResult.success();
    }

    @RequestMapping("/toLeaveReview")
    public String toLeaveReview(HttpServletRequest request, Model model) {
        List<TBasecodeEntity> leaveTypeList= tLeaveService.selectAllLeaveType();
        model.addAttribute("leaveTypeList", leaveTypeList);


        Map<String, Object> paramsGet = Util.paramToMap(request);//从前台获取到的信息
        Map paramsSelect = new HashMap();//送入service检索的条件

        String page = (String) paramsGet.get("page");


        if (paramsGet.get("leaveType") != null && !String.valueOf(paramsGet.get("leaveType")).trim().isEmpty()) {
            paramsSelect.put("leaveType", String.valueOf(paramsGet.get("leaveType")));
        }

        if (paramsGet.get("checkStatus") != null && !String.valueOf(paramsGet.get("checkStatus")).trim().isEmpty()) {
            paramsSelect.put("checkStatus", String.valueOf(paramsGet.get("checkStatus")));
        }

        if (paramsGet.get("checkUser") != null && !String.valueOf(paramsGet.get("checkUser")).trim().isEmpty()) {
            paramsSelect.put("checkUser", String.valueOf(paramsGet.get("checkUser")));
        }

        if (paramsGet.get("leaveUser") != null && !String.valueOf(paramsGet.get("leaveUser")).trim().isEmpty()) {
            paramsSelect.put("leaveUser", String.valueOf(paramsGet.get("leaveUser")));
        }

        Integer pageSize = 15;

        if (page != null && !"".equals(page) && !"0".equals(page)) {
            paramsSelect.put("page", Integer.parseInt(page));
        } else {
            page = "1";
            paramsSelect.put("page", Integer.parseInt(page));
        }

        paramsSelect.put("limit", pageSize);

        PageResult<TLeaveEntity> pageResult = new PageResult<TLeaveEntity>(tLeaveService.selectAllReviewData(paramsSelect),paramsSelect);


        Integer totalPage = 1;
        if (pageResult.getCount() != null && pageResult.getCount() != 0) {
            totalPage = (pageResult.getCount() + pageSize - 1) / pageSize;
        }


        model.addAttribute("result", pageResult);
        model.addAttribute("page", page);
        model.addAttribute("totalPage", totalPage);

        return "/leave/leaveReview";
    }
}
