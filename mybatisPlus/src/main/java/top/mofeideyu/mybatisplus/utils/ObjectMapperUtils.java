package top.mofeideyu.mybatisplus.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author: Administrator
 * @date: 2023/5/29 10:48
 * @description:
 */
public class ObjectMapperUtils {
    private static final ObjectMapper SINGLETON_MAPPER;

    public ObjectMapperUtils() {
    }

    public static String toJsonString(Object fromValue) {
        try {
            return fromValue == null ? null : SINGLETON_MAPPER.writeValueAsString(fromValue);
        } catch (Exception var2) {
            return null;
        }
    }

    public static <T> T convertValue(String json, Class<T> valueType) {
        try {
            return json == null ? null : SINGLETON_MAPPER.readValue(json, valueType);
        } catch (Exception var3) {
            return null;
        }
    }

    static {
        SINGLETON_MAPPER = (new ObjectMapper()).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}