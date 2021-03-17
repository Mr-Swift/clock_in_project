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
import com.njusc.npm.metadata.entity.TUserPostEntity;
import com.njusc.npm.service.TUserPostService;

/**
 * 人员岗位信息
 *
 * @author Michael
 * @date 2021-01-18 12:26:27
 */
@Controller
@RequestMapping("/tUserPost")
public class TUserPostController {

    //日志
    private static final Logger log = Logger.getLogger(TUserPostController.class);


    @Autowired
    private TUserPostService tUserPostService;

}
