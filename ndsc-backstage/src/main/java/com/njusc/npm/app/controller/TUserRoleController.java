package com.njusc.npm.app.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.log4j.Logger;
import com.njusc.npm.metadata.entity.TUserRoleEntity;
import com.njusc.npm.service.TUserRoleService;

/**
 * 用户角色管理
 *
 * @author Michael
 * @date 2021-01-18 12:26:27
 */
@Controller
@RequestMapping("/tUserRole")
public class TUserRoleController {

    //日志
    private static final Logger log = Logger.getLogger(TUserRoleController.class);


    @Autowired
    private TUserRoleService tUserRoleService;

}
