package com.hq.system.wxerror;


import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * description.
 *
 * @author: mall
 * @date: 2023-01-06 12:31
 **/
public class WxpayError {

    /*private static Map<String, Function<String, String>> functionMap = new HashMap<>();

    public String getCheckResult(String key, String str) {
        Function<String, String> function = functionMap.get(key);
        if (function != null) {
            return function.apply(str);
        }
        return "error";
    }

    static {
        ErrorType errorType = new ErrorType();
        functionMap.put("one",bankBusiness::one);
        functionMap.put("two",bankBusiness::two);
        functionMap.put("three",bankBusiness::three);
    }

    private static class ErrorType{

        //支付结果未知
        public String one(String str) {
            return "第一次执行" + str;
        }

        public String two(String str) {
            return "第二次执行" + str;
        }

        public String three(String str) {
            return "第三次执行" + str;
        }
    }*/
}
