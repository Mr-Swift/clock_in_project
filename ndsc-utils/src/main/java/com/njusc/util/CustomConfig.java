package com.njusc.util;

import java.util.ResourceBundle;

/**
 * config.properties 配置文件处理
 *
 * @author Michael
 */
public class CustomConfig {
    private static final ResourceBundle RB = ResourceBundle.getBundle("es-config");

    public static String esIpAddress() {
        return RB.getString("es.host.ip");
    }

    public static String esIpPort() {
        return RB.getString("es.host.port");
    }

    public static String esUserName() {
        return RB.getString("es.host.user.name");
    }

    public static String esPassword() {
        return RB.getString("es.host.password");
    }

    public static String timeout() {
        return RB.getString("es.timeout");
    }
}