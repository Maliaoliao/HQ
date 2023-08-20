package com.hq.system.utils;

import java.security.MessageDigest;
import java.util.Random;
import java.util.SortedMap;

/**
 * md5加解密工具类.
 *
 * @author: mall
 * @date: 2022 -12-12 15:12
 */
public class MD5Util {

    /**
     * 生成md5 32为码.
     *
     * @param inStr the in str
     * @return the string
     */
    public String md532(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            //todo 不知道写什么
            return "md5 解析失败";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }

        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            } else {
                hexValue.append(Integer.toHexString(val));
            }
        }
        return hexValue.toString();
    }


    /**
     * 创建待加密字段（带key）.
     *
     * @param parameters the parameters
     * @param key        the key
     * @return the string
     */
    public String createSign(SortedMap<String, Object> parameters, String key){
        StringBuffer b = new StringBuffer();
        parameters.forEach((k,v)->{
            b.append(k).append("=").append(v).append("&");
        });
        //拼接商户密钥
        b.append("key").append("=").append(key);
        return b.toString();
    }

    /**
     * 产生16位随机数.
     *
     * @param c the 位数
     * @return the string
     */
    public String getNoncestr(int c){
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        // 指定多少位数循环随机取字符拼接
        for (int i = 0; i < c; ++i) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
