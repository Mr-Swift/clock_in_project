package com.njusc.npm.app.handle;

import com.njusc.base.ApiResult;
import com.njusc.npm.metadata.entity.TUserEntity;
import com.njusc.npm.utils.annotation.Token;
import com.njusc.npm.utils.enumeration.ResultCodeEnum;
import com.njusc.npm.utils.util.JsonUtil;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * 全局表单防止重复提交拦截器
 *
 * @author Michael
 * @since 2018-11-22
 */
public class AvoidDuplicateSubmissionInterceptor extends HandlerInterceptorAdapter {

    public static Logger logger = Logger.getLogger(AvoidDuplicateSubmissionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {


        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Token annotation = method.getAnnotation(Token.class);
        if (annotation != null) {
            boolean needSaveSession = annotation.save();
            if (needSaveSession) {
                request.getSession(true).setAttribute("token", UUID.randomUUID().toString());
            }
            boolean needRemoveSession = annotation.remove();
            if (needRemoveSession) {
                if (isRepeatSubmit(request)) {

                    if(isAjaxRequest(request))
                    {
                        //如果是ajax请求，返回json
                        response.setCharacterEncoding("UTF-8");
                        response.setContentType("text/html;charset=UTF-8");
                        PrintWriter out = response.getWriter();
                        ApiResult apiResult=ApiResult.failed(ResultCodeEnum.请勿重复提交);
                        String result = JsonUtil.jsonToString(apiResult);
                        out.print(result);
                        out.close();
                        return false;
                    }
                    else
                    {
                        response.setCharacterEncoding("UTF-8");
                        response.setContentType("text/html;charset=UTF-8");
                        PrintWriter out = response.getWriter();
                        StringBuilder builder = new StringBuilder();
                        builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
                        builder.append("alert('操作正在进行，请勿重复操作！');</script>");
                        out.print(builder.toString());
                        out.close();
                        return false;
                    }
                }
                request.getSession(false).removeAttribute("token");
            }
        }
        return true;
    }
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("x-requested-with");
        if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        } else {
            return false;
        }
    }

    //校验是否重复提交
    public boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) request.getSession(true).getAttribute("token");

        if (serverToken == null) {
            return true;
        }
        String clientToken = request.getParameter("token");
        if (clientToken == null) {
            return true;
        }
        if (!serverToken.equals(clientToken)) {
            return true;
        }
        return false;
    }


}