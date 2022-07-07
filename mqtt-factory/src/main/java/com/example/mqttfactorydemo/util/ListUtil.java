package com.example.mqttfactorydemo.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author peiyuxiang
 * @date 2020-8-29
 */
public class ListUtil {

    private ListUtil(){

    }
    /**
     * 判断List是否有意义(不为null,且不为空)
     *
     * @param <T>  集合泛型
     * @param list list
     * @return 返回结果
     */
    public static <T> boolean isValid(Collection<T> list) {
        return (list != null && !list.isEmpty());
    }

    /**
     * 判断List是否有意义(不为null,且不为空)
     *
     * @param <T>  集合泛型
     * @param list list
     * @return 返回结果
     */
    public static <T> boolean isInvalid(Collection<T> list) {
        return !isValid(list);
    }

    /**
     * 判断数组是否有意义(不为null,且不为空)
     *
     * @param <T>  数组泛型
     * @param list list
     * @return 返回结果
     */
    public static <T> boolean isValid(T[] list) {
        return (list != null && list.length > 0);
    }

    /**
     * 判断数组是否有意义(不为null,且不为空)
     *
     * @param <T>  集合泛型
     * @param list list
     * @return 返回结果
     */
    public static <T> boolean isInvalid(T[] list) {
        return !isValid(list);
    }

    /**
     * 切割数组返回对应List集合
     *
     * @param <T>   泛型
     * @param array 数组
     * @param num   切割间距
     * @return 返回结果
     */
    public static <T> List<T[]> splitArrayEachNum(T[] array, int num) {
        List<T[]> list = new LinkedList<>();
        int length = array.length;
        int cycle = length / num;
        int left = length % num;
        for (int i = 0; i < cycle; i++) {
            T[] data = Arrays.copyOfRange(array, num * i, num * (i + 1));
            list.add(data);
        }
        if (left > 0) {
            T[] data = Arrays.copyOfRange(array, num * cycle, num * cycle + left);
            list.add(data);
        }
        return list;
    }


    /**
     * 切割List返回对应List集合
     *
     * @param <T>  泛型
     * @param list 原集合
     * @param num  切割间距
     * @return 返回结果
     */
    public static <T> List<List<T>> splitListEachNum(List<T> list, int num) {
        List<List<T>> result = new LinkedList<>();
        int length = list.size();
        int cycle = length / num;
        int left = length % num;
        for (int i = 0; i < cycle; i++) {
            List<T> data = list.subList(num * i, num * (i + 1));
            result.add(data);
        }
        if (left > 0) {
            List<T> data = list.subList(num * cycle, num * cycle + left);
            result.add(data);
        }
        return result;
    }

    public static String convertListToStr(List<String> strList) {
        if (!isValid(strList)) {
            return null;
        }
        StringBuilder res = new StringBuilder();
        for (String s : strList) {
            res.append("'").append(s).append("',");
        }
        return res.toString().substring(0,res.length()-1);
    }


}
