package com.njusc.npm.app.controller;

import com.njusc.base.ApiResult;
import com.njusc.npm.app.util.SessionUtil;
import com.njusc.npm.metadata.entity.TUserEntity;
import com.njusc.npm.service.TLogService;
import com.njusc.npm.service.TUserService;
import com.njusc.npm.utils.enumeration.ResultCodeEnum;
import com.njusc.npm.utils.util.MD5;
import com.njusc.npm.utils.util.Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import static org.apache.shiro.SecurityUtils.*;

/**
 * 登录
 *
 * @author Michael
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    //日志
    protected static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private TUserService tUserService;
    @Autowired
    private TLogService tLogService;

    @RequestMapping("/toLogin")
    public String toLogin(HttpServletRequest request) {

        return "/login";
    }

    //退出
    @RequestMapping("/gobank")
    @ResponseBody
    public ApiResult gobank(HttpServletRequest request) {

        TUserEntity user = (TUserEntity) request.getSession().getAttribute("user");
        if (user != null) {
//            Map logMap = new HashMap();
//            logMap.put("operationRemark", "用户:[" + user.getUserName() + "],[退出登录]");
//            logMap.put("insertUser", user.getId());
//            tLogService.saveLog(logMap);
        }
        return ApiResult.success();
    }

    //更新密码
    @RequestMapping("/passwordUpdate")
    @ResponseBody
    public ApiResult passwordUp(HttpServletRequest request, String bpwd, String npwd) {
        TUserEntity user = (TUserEntity) request.getSession().getAttribute("user");

        Map<String, Object> params = new HashMap<>();
        params.put("id", user.getId());
        TUserEntity userEntity = tUserService.find(params);
        new MD5();
        if (userEntity.getUserPassword().equals(MD5.GetMD5Code(bpwd))) {
            new MD5();
            userEntity.setUserPassword(MD5.GetMD5Code(npwd));
            tUserService.update(userEntity);
            return ApiResult.success();
        } else {
            return ApiResult.failed("原密码不正确");
        }
    }

    @RequestMapping("/checkBpwd")
    @ResponseBody
    public ApiResult checkBpwd(HttpServletRequest request, String bpwd) {
        TUserEntity user = (TUserEntity) request.getSession().getAttribute("user");
        Map<String, Object> params = new HashMap<>();
        params.put("id", user.getId());
        TUserEntity userEntity = tUserService.find(params);
        if (userEntity.getUserPassword().equals(MD5.GetMD5Code(bpwd))) {
            return ApiResult.success();
        } else {
            return ApiResult.failed("原密码不正确");
        }
    }

    //登录
    @RequestMapping("/loginIn")
    @ResponseBody
    public ApiResult login(HttpServletRequest request, String proCode) {
        Map<String, Object> params = Util.paramToMap(request);
        try {
            String randCode = (String) request.getSession().getAttribute("imgCode");
            if (!StringUtils.isEmpty(randCode) && !StringUtils.isEmpty(proCode)) {
                if (randCode.toLowerCase().equals(proCode.toLowerCase())) {
                    getSubject().login(new UsernamePasswordToken(URLDecoder.decode(params.get("account").toString(), "UTF-8"), URLDecoder.decode(params.get("password").toString(), "UTF-8")));

                    TUserEntity u = (TUserEntity) request.getSession().getAttribute("user");

//                    Map logMap = new HashMap();
//                    logMap.put("operationRemark", "用户:[" + u.getUserName() + "],[登录系统]");
//                    logMap.put("insertUser", u.getId());
//                    tLogService.saveLog(logMap);

                    return ApiResult.success();
                } else {
                    return ApiResult.failed(ResultCodeEnum.验证码错误);
                }
            } else {
                return ApiResult.failed(ResultCodeEnum.参数异常);
            }
        } catch (UnknownAccountException e) {
            return ApiResult.failed(ResultCodeEnum.账户密码出错);
        } catch (DisabledAccountException e) {
            return ApiResult.failed(ResultCodeEnum.用户已禁用);
        } catch (AccountException e) {
            return ApiResult.failed("您的密码较弱，不符合系统安全规则，已被禁用，请联系系统管理员！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return ApiResult.failed();
        }
    }

    /**
     * 验证码
     */
    @RequestMapping("/exeImg")
    public String executeImage() {
        return "/image";
    }

    /**
     * 验证码校验
     */
    @RequestMapping("/valiPicCode")
    @ResponseBody
    public ApiResult valiPicCode(HttpServletRequest request, String picCode) {
        if (!StringUtils.isEmpty(picCode)) {
            String randCode = (String) request.getSession().getAttribute("imgCode");
            if (randCode.toLowerCase().equals(picCode.toLowerCase())) {
                return ApiResult.success();
            } else {
                return ApiResult.failed(ResultCodeEnum.验证码错误);
            }
        } else {
            return ApiResult.failed(ResultCodeEnum.参数异常);
        }
    }
}
