package com.njusc.npm.app.controller;

import java.util.*;
import javax.servlet.http.HttpServletRequest;

import com.njusc.base.PageResult;
import com.njusc.npm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.log4j.Logger;
import com.njusc.npm.metadata.entity.TLogEntity;

/**
 * 日志表
 *
 * @author Michael
 * @date 2021-01-18 12:26:27
 */
@Controller
@RequestMapping("/tLog")
public class TLogController {

    //日志
    private static final Logger log = Logger.getLogger(TLogController.class);

    @Autowired
    private TLogService tLogService;
    @Autowired
    private HttpServletRequest request;





    @RequestMapping("/toLog")
    public String toLog(String proInfo) {

        return "tLog/log";
    }


    @RequestMapping("/getLogs")
    public String goToPage(HttpServletRequest request, String userName, String page, String startTime, String endTime) {
        Map<String, Object> params = new HashMap<>();
        params.put("userName", userName);
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        Integer pageSize = 15;
        if (page != null && !"".equals(page) && !"0".equals(page)) {
            params.put("page", Integer.parseInt(page));
        } else {
            page = "1";
            params.put("page", Integer.parseInt(page));
        }
        params.put("limit", pageSize);
        PageResult<TLogEntity> result = new PageResult<TLogEntity>(tLogService.findList(params), params);
        Integer totalPage = 1;
        if (result.getCount() != null && result.getCount() != 0) {
            totalPage = (result.getCount() + pageSize - 1) / pageSize;
        }
        request.setAttribute("page", page);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("result", result);
        return "tLog/log";
    }
}

