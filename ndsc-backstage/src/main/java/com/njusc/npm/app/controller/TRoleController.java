package com.njusc.npm.app.controller;

import com.njusc.base.PageResult;
import com.njusc.npm.metadata.entity.TFuncitonEntity;
import com.njusc.npm.metadata.entity.TRoleEntity;
import com.njusc.npm.service.TFuncitonService;
import com.njusc.npm.service.TRoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色表
 *
 * @author Michael
 * @date 2021-01-18 12:26:27
 */
@Controller
@RequestMapping("/tRole")
public class TRoleController {

    //日志
    private static final Logger log = Logger.getLogger(TRoleController.class);


    @Autowired
    private TRoleService tRoleService;
    @Autowired
    private TFuncitonService tFuncitonService;



    @RequestMapping("/toRoleManage")
    public String roleManageDate(HttpServletRequest request, Model model, String page, String roleName){
        Map<String,Object> params = new HashMap<>();
        //显示的数据长度
        int pageSize= 15;
        if(page !=null && !"".equals(page) && !"0".equals(page)){
            params.put("page",Integer.parseInt(page));
        } else {
            page = "1";
            params.put("page",Integer.parseInt(page));
        }
        if(roleName !=null && !"".equals(roleName)){
            //将前台字符转为中文
            try {
                roleName = java.net.URLDecoder.decode(roleName,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            params.put("roleName",roleName);
        }
//        int page2=(Integer)params.get("page");
//        page2=(page2-1)*pageSize;
//        params.put("page2",page2);
        params.put("size",pageSize);
        List roleList =tRoleService.findList(params);
        PageResult<TRoleEntity> infoList = new PageResult<>(tRoleService.findList(params),params);
        Integer totalPage = 1;
        if(infoList.getCount() != null && infoList.getCount() !=0){
            totalPage =(infoList.getCount() + pageSize -1)/pageSize;
        }

        //显示树菜单数据
        StringBuilder jsonTree = new StringBuilder();
        jsonTree.append("[");
        List<TFuncitonEntity> list = tFuncitonService.findFunciton2();
        List<TFuncitonEntity> newList1 = new ArrayList<>();

        //System.out.println(newList1);

        int j = 0;
        for (TFuncitonEntity function : list) {
            int i= 0 ;
//            List<TFuncitonEntity> childList = tFuncitonService.findChildFunciton(function.getId());
            jsonTree.append("{\"id\":").append(function.getId()).append(",");
            jsonTree.append("\"pid\":").append(function.getParentId()).append(",");
            jsonTree.append("\"name\":").append("\"").append(function.getFunName()).append("\"");

//            if(childList.size()>0){
//                jsonTree.append(",");
//                function.setChilds(childList);
//                jsonTree.append("children:[");
//                for (TFuncitonEntity function2 :childList) {
//
//                    jsonTree.append("{\"id\":").append(function2.getId()).append(",");
//                    jsonTree.append("\"pid\":").append(function2.getParentId()).append(",");
//                    jsonTree.append("\"name\":").append("\"").append(function2.getFunName()).append("\"").append("}");
//                    if(i!=childList.size()-1){jsonTree.append(",");}
//                    i++;
//                }
//                jsonTree.append("]");
//            }
            jsonTree.append("}");
            if(j!=list.size()-1){jsonTree.append(",");}
            j++;
            newList1.add(function);
        }
        jsonTree.append("]");
        //String json= JSON.toJSONString(jsonTree);
        model.addAttribute("roleName",roleName);
        model.addAttribute("jsonTree",jsonTree);
        model.addAttribute("roleList",roleList);
        model.addAttribute("newList1",newList1);
        model.addAttribute("page",page);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("infoList",infoList);

        return "tRole/roleManage";
    }




}
