package com.nus.iss.etscommon.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonUtils {

    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> String objectToString(T object) {
        if (null == object) {
            return null;
        }
        try {
            return object instanceof String ? (String) object : objectMapper.writeValueAsString(object);
        } catch (IOException exception) {
            log.info("Error parsing object to string: ", exception);
            return null;
        }
    }

    public static <T> T stringToObject(String str, Class<T> clazz) {
        if (str.isEmpty() || null == clazz) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (IOException exception) {
            log.info("Error parsing String to Object: ", exception);
            return null;
        }
    }
}
