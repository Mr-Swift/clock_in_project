package com.njusc.npm.app.util;

import com.njusc.npm.metadata.entity.TUserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import javax.servlet.http.HttpServletRequest;

/**
 * 会话操作
 * 
 * @author michael
 *
 */
public class SessionUtil {

    public static final String SESSION_KEY_USER = "user";

    public static Object getSession(final HttpServletRequest request,
                                    final String name) {
        return request.getAttribute(name);
    }
    public static void setUser(final TUserEntity value) {
        Session session = SecurityUtils.getSubject().getSession();
        session.setTimeout(1000 * 60 * 60 * 2);// 两小时
        session.setAttribute(SESSION_KEY_USER, value);
    }
    public static void setSession(final HttpServletRequest request,
                                  final String name,
                                  final Object value) {
        request.getSession().setAttribute(name, value);
    }

    public static TUserEntity getUser() {
        return (TUserEntity) SecurityUtils.getSubject().getSession().getAttribute(SESSION_KEY_USER);
    }

}
