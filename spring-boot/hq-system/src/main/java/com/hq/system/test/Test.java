package com.hq.system.test;

import com.alibaba.fastjson.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * description.
 *
 * @author: mall
 * @date: 2021-10-29 14:53
 **/
public class Test {/**
 * 获取指定年月有多少个bai工作日）
 * @param year
 * @param month
 */
public static int countWorkDay(int year,int month) {
    Calendar c = Calendar.getInstance();
    c.set(Calendar.YEAR, year);
    // 月份是从0开始du计算，所以需要减去1
    c.set(Calendar.MONTH,month-1);
    // 当月最后一天的日期dao
    int max = c.getActualMaximum(Calendar.DAY_OF_MONTH);
    // 开始日期为1号
    int start =1;
    // 计数
    int count = 0;
    while(start <= max){
        c.set(Calendar.DAY_OF_MONTH,start);
        if(isWorkDay(c)){
            count++;
        }
        start++;
    }
    return count;
}
    // 判断是否工作日（未排除法定节假日，由于涉及到农历节日，处理很麻烦）
    public static boolean isWorkDay(Calendar c){
        // 获取星期,1~7,其中1代表星期日，2代表星期一 ... 7代表星期六
        int week = c.get(Calendar.DAY_OF_WEEK);
        // 不是周六和周日的都认为是工作日
        return week != Calendar.SUNDAY  && week != Calendar.SATURDAY;
}

    public static void main(String[] args) {
        /*String res = "true";
        if(!res.equals("true")){
            System.out.println(1);
            return;
        }
        System.out.println(2);*/

        JSONObject json = new JSONObject();
        json.put("userid", 1);
        json.put("phone", 2);
        Map<String,String> map = new HashMap<>();
        map.put("A","A");
        json.put("order",map);
        System.out.println(json.toJSONString());
    }

    public Map<Object, int[]> getList(){


        Map<String, Object> map = new HashMap<>();
        Map<Object, int[]> o = new HashMap<>();
        o.put(1, new int[]{1, 2});
        o.put(2, new int[]{1, 2});
        o.put(3, new int[]{1, 2});
        o.put(4, new int[]{1, 2});
        o.put(5, new int[]{1, 2});
        o.put(6, new int[]{1, 2});
        o.put(6, new int[]{1, 2});
        o.put(7, new int[]{1, 2});
        o.put(8, new int[]{1, 2});
        o.put(9, new int[]{1, 2});
        o.put(10, new int[]{1, 2});
        o.put(11, new int[]{1, 2});
        o.put(12, new int[]{1, 2});
        map.put("2022",o);
        return o;
    }



}
