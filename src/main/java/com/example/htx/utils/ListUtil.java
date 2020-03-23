package com.example.htx.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 关于集合的工具类
 */
public class ListUtil {

    /**
     * 字符串转集合
     */
    public static List<String> getStringToList(String s){
        List<String> stringList = new ArrayList<>();
        if (StringUtils.isNotBlank(s)){
            stringList = Arrays.asList(s.split(","));
        }
        return stringList;
    }
}
