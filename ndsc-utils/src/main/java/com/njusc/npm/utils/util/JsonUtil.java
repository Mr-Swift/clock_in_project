package com.njusc.npm.utils.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

/**
 * json操作工具类，基于jackson
 *
 * @author Micahel
 */
public class JsonUtil {

    private static final Logger log = Logger.getLogger(JsonUtil.class);

    private static ObjectMapper om = new ObjectMapper();

    private JsonUtil() {
    }

    /**
     * json 对象转JSON
     *
     * @param t
     * @return
     */
    public static <T> String jsonToString(final T t) {
        try {
            return om.writeValueAsString(t);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }

    /**
     * 对象转json
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T jsonToObject(final String json,
                                     final Class<T> clazz) {
        try {
            return om.readValue(json, clazz);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 集合 字符串转 集合对象
     *
     * @param json
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(final String json, final TypeReference<T> typeReference) {
        try {
            return om.readValue(json, typeReference);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


}
