package com.njusc.npm.utils.util;

import java.util.ResourceBundle;

public class GetPropertiesUtil {
    /**
     * 获取配置文件里的数据
     * @return
     */

    public static final ResourceBundle PASSWORD_ISORGA= ResourceBundle.getBundle("system");

    public static String getProperties(String param) {

        String propersiesStr= PASSWORD_ISORGA.getString(param);

        return propersiesStr;

    }

    public static void main(String[] args) {
        System.out.println(getProperties("publicKey"));
    }
}
