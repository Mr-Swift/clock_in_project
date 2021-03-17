package com.njusc.npm.app.controller;

import com.njusc.npm.app.constant.Constant;
import com.njusc.npm.app.util.SessionUtil;
import com.njusc.npm.metadata.entity.TFuncitonEntity;
import com.njusc.npm.metadata.entity.TUserEntity;
import com.njusc.npm.service.TFuncitonService;
import com.njusc.npm.service.TLogService;
import com.njusc.npm.service.TUserService;
import com.njusc.npm.service.TUserService;
import com.njusc.npm.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 主页面
 */
@Controller
@RequestMapping("/main")
public class MainController {

    //日志
    protected static final Logger logger = LoggerFactory.getLogger(MainController.class);


    @Autowired
    private TUserService tUserService;
    @Autowired
    private TLogService tLogService;

    @Autowired
    private TFuncitonService funcitonService;
    @RequestMapping("/toPtProRealWorkQuality")
    public String toPtProRealWorkQuality()
    {
        return "/ptProRealWork/ptProRealWorkQuality";
    }

   @RequestMapping("/index")
   public String main(Model model,HttpServletRequest request) {
       TUserEntity user = (TUserEntity) request.getSession().getAttribute("user");
       model.addAttribute("user", user);
       if (null != user) {
           if ("admin".equals(user.getPersonNo())) {
               List<TFuncitonEntity> functionList = funcitonService.getFunctionListAdmin();
               List<TFuncitonEntity> functionNewList = new ArrayList<>();
               if (functionList.size() > 0) {
                   for (TFuncitonEntity function : functionList) {
                       Map<String, Object> map = new HashMap<>();
                       map.put("parentId", function.getId());
                       List<TFuncitonEntity> listNext = funcitonService.getNextFunctionListAdmin(map);
                       if (listNext.size() > 0) {
                           function.setChilds(listNext);
                       }
                       functionNewList.add(function);
                   }
               }
               model.addAttribute("functionNewList", functionNewList);


           } else {
               List<TFuncitonEntity> functionList = funcitonService.getFunctionList(user.getId());
               List<TFuncitonEntity> functionNewList = new ArrayList<>();
               if (functionList.size() > 0) {
                   for (TFuncitonEntity function : functionList) {
                       Map<String, Object> map = new HashMap<>();
                       map.put("userId", user.getId());
                       map.put("parentId", function.getId());
                       List<TFuncitonEntity> listNext = funcitonService.getNextFunctionList(map);
                       if (listNext.size() > 0) {
                           function.setChilds(listNext);
                       }
                       functionNewList.add(function);
                   }
               }
               model.addAttribute("functionNewList", functionNewList);
           }
       }
       return "/main";
   }


    @RequestMapping("/mainshow")
    public String mainshow(Model model,HttpServletRequest request) {

        return "/mainShow";
    }

}
