package com.njusc.npm.utils.util;

import java.util.ResourceBundle;

/**
 * 获取properties文件中内容
 */
public class PropertiesUtils {

    public static String getValue(String path,String key)
    {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(path);
        if(resourceBundle==null) {
            throw new RuntimeException("path error!");
        }
        return resourceBundle.getString(key);
    }
}
