package com.micro.utils.lang3;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * 类型转换类
 */
public class ParseUtils {
    /**
     * List<?> 转化为 List<?>
     *
     * @param list  原列表
     * @param clazz 新Obj
     */
    public static <T> List<T> parseArray(List<?> list, Class<T> clazz) {
        return JSON.parseArray(JSON.toJSONString(list), clazz);
    }

    /**
     * Object
     */
    public static <T> T parseObject(Object list, Class<T> clazz) {
        return JSON.parseObject(JSON.toJSONString(list), clazz);
    }
}
