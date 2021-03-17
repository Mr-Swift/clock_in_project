package com.njusc.npm.utils.util;

import sun.misc.BASE64Decoder;

/**
 * 将前台传过来的经过base64加密后的中文还原
 *
 */
public class Base64Decode {

    /**
     * 将前台传过来的经过base64加密后的中文还原
     *
     * @param key 经过base64加密的中文字符串
     * @return 解码后的中文字符串
     */
    public static String decode(String key) {
        String value = key;
        if (value != null && !"".equals(value)) {
            // System.out.println(value);
            value = value.replaceAll(" ", "+");
            try {
                value = new String(new BASE64Decoder().decodeBuffer(value), "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value;
    }
}
