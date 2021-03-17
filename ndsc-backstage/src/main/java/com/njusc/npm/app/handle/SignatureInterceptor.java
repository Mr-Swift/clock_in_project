package com.njusc.npm.app.handle;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.njusc.base.ApiResult;
import com.njusc.npm.utils.util.JsonUtil;
import com.njusc.npm.utils.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


import com.njusc.npm.app.security.RequestConstants;
import com.njusc.npm.utils.enumeration.ResultCodeEnum;

/**
 * <p>
 * 访问合法性验证验证
 * 按顺序将（请求时间+请求uuid+版本号+前后台约定的md5的字符串）拼接成固定字符串后，再使用md5生成摘要作为signature的值，
 * 判断生成的signature与请求体中的signature值是否相同，如果不相等，则请求非法
 * </p>
 */
public class SignatureInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = Logger.getLogger(SignatureInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
    	String request_time = request.getParameter(RequestConstants.REQUESTTIME);
    	String usystemid = request.getParameter(RequestConstants.USYSTEMID);
    	String system_version = request.getParameter(RequestConstants.SYSTEM_VERSION);
    	String signature = request.getParameter(RequestConstants.SIGNATURE);
    	if(StringUtils.isBlank(request_time) 
    			||StringUtils.isBlank(usystemid) 
    			||StringUtils.isBlank(system_version) 
    			||StringUtils.isBlank(signature) 
    			){
    		//请求非法
    		//TODO
    		returnJson(response);
    		return false;
    		
    	}
    	if(!signature.equals(MD5Util.MD5Encode(request_time+usystemid+system_version+RequestConstants.MD5_STR, "UTF-8"))){
    		//请求非法
    		//TODO
    		returnJson(response);
    		return false;
    	}
        return true;
    }
    /**
     * 请求失败，返回json
     * @param response
     * @throws Exception
     */
    private void returnJson(HttpServletResponse response) throws Exception{
    	ApiResult apiResult = ApiResult.failed(ResultCodeEnum.非法请求);
    	response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.print(JsonUtil.jsonToString(apiResult));
		writer.close();
    }

}