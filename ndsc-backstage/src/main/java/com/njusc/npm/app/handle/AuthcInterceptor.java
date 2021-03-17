package com.njusc.npm.app.handle;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.njusc.base.ApiResult;
import com.njusc.npm.utils.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.njusc.npm.utils.enumeration.ResultCodeEnum;
/**
 * <p>
 * 登陆验证拦截器
 * </p>
 */
public class AuthcInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = Logger.getLogger(AuthcInterceptor.class);

//    @Resource
//	private RedisClient redisClient;

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {

//    	String accesstoken = request.getParameter(RequestConstants.ACCESSTOKEN);
//    	if(StringUtils.isBlank(accesstoken)){
//    		//accesstoken不存在，无效请求
//    		//TODO
//    		returnJson(response);
//    		return false;
//    	}
//    	//从redis中根据token获取userid
//    	String key = RedisConstants.TOKEN+accesstoken;
//    	String userid = redisClient.get(key);
//    	if(StringUtils.isBlank(userid)){
//    		//缓存中没有accesstoken，要求用户登陆
//    		//TODO
//    		returnJson(response);
//    		return false;
//    	}
//    	//剩余时间
//    	long lastsecends = redisClient.ttl(key);
//    	if(lastsecends == -2){
//    		//缓存中accesstoken已失效，要求用户重新登陆
//    		//TODO
//    		returnJson(response);
//    		return false;
//    	}
//    	//默认30天内有效，如果只剩余5天，则修改为30天
//    	if(lastsecends/60/60/24<5){
//    		redisClient.expire(key, RedisConstants.TOKEN_SECONDS);
////    		redisClient.set("aaa", "111", 10);
//    	}
//    	//3、将userid放到request中
//    	request.setAttribute(RequestConstants.APPUSERID, userid);
        return true;
    }

    /**
     * 请求失败，返回json
     * @param response
     * @throws Exception
     */
    private void returnJson(HttpServletResponse response) throws Exception{
    	ApiResult apiResult = ApiResult.failed(ResultCodeEnum.请重新登陆);
    	response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.print(JsonUtil.jsonToString(apiResult));
		writer.close();
    }
}