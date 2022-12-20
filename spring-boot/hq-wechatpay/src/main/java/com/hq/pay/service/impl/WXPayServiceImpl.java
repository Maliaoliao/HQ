package com.hq.pay.service.impl;

import com.github.wxpay.sdk.WXPay;
import com.hq.pay.config.HQPayConfig;
import com.hq.pay.service.WXPayService;
import com.hq.pay.utils.MD5Util;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 订单支付实现.
 *
 * @author: mall
 * @date: 2022-06-25 22:07
 */
public class WXPayServiceImpl implements WXPayService {

    private final static String RETURN_CODE_SUCCESS = "SUCCESS";
    private final static String RESULT_CODE_SUCCESS = "SUCCESS";
    private final static String SUCCESS = "SUCCESS";


    //付款码支付
    private final static String TRADE_TYPE = "MICROPAY";
    @Override
    public Map<String, String> orderPay(String json) throws Exception {
        //json 转 SortedMap
        //商户基础配置
        HQPayConfig config = new HQPayConfig();
        //微信支付接口
        WXPay wxpay = new WXPay(config);
        //请求参数
        SortedMap<String, Object> data = new TreeMap<>();
        MD5Util md = new MD5Util();
        //产生16位随机数
        String nonceStr = md.getNoncestr(16).toUpperCase();
        //微信分配的公众账号ID（企业号corpid即为此appid）
        data.put("appid",config.getAppID());
        //商户号
        data.put("mch_id",config.getMchID());
        //随机字符串
        data.put("nonce_str",nonceStr);
        //商品描述
        data.put("body", "鱼香肉丝盖饭");
        //todo 使用其他工具生成随机数
        data.put("out_trade_no", "2016090910595900000012");
        //订单金额 单位 分
        data.put("total_fee", "168");
        //TODO 订单优惠金额
        //data.put("goods_tag","");
        //终端ip地址
        data.put("spbill_create_ip", "123.12.12.123");
        //扫码支付付款码，设备读取用户微信中的条码或者二维码信息,用户付款码规则：18位纯数字，前缀以10、11、12、13、14、15开头）
        data.put("auth_code","120061098828009406");

        String signTmp = md.createSign(data, config.getKey());
        //将签名转为大写
        String sign = md.md532(signTmp).toUpperCase();
        //签名算法
        data.put("sign", sign);
        try {
            //调用微信支付接口
            Map<String, String> resp = wxpay.unifiedOrder((Map)data);
            if(RETURN_CODE_SUCCESS.equals(resp.get("return_code"))) {
                //接口通信情况正常
                if(RESULT_CODE_SUCCESS.equals(resp.get("result_code"))){
                    if(TRADE_TYPE.equals(resp.get("trade_type"))){
                        //才是交易成功了 将交易数据写到数据库中
                    }
                } else {
                    //TODO 接口通信情况异常 重试或者其他处理方式
                    System.out.println(resp.get("err_code"));
                    System.out.println(resp.get("err_code_des"));
                }
            } else {
                //TODO 接口通信情况异常 重试或者其他处理方式
                System.out.println(resp.get("err_code"));
                System.out.println(resp.get("err_code_des"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, String> orderQuery(String transactionId) throws Exception {

        //json 转 SortedMap
        //商户基础配置
        HQPayConfig config = new HQPayConfig();
        //微信支付接口
        WXPay wxpay = new WXPay(config);
        MD5Util md = new MD5Util();
        SortedMap<String, Object> data = new TreeMap<>();
        String nonceStr = md.getNoncestr(16).toUpperCase();
        //微信分配的公众账号ID（企业号corpid即为此appid）
        data.put("appid",config.getAppID());
        //商户号
        data.put("mch_id",config.getMchID());
        //随机字符串
        data.put("nonce_str",nonceStr);
        //微信订单号
        data.put("out_trade_no", transactionId);

        String signTmp = md.createSign(data, config.getKey());
        //将签名转为大写
        String sign = md.md532(signTmp).toUpperCase();
        //签名算法
        data.put("sign", sign);

        try {
            Map resp = wxpay.orderQuery((Map) data);
            if(SUCCESS.equals(resp.get("return_code"))) {
                //接口通信情况正常
                if(SUCCESS.equals(resp.get("result_code"))){
                    if(SUCCESS.equals(resp.get("trade_state"))){
                        //TODO 展示查询结果
                    }
                } else {
                    //TODO 接口通信情况异常 重试或者其他处理方式
                    System.out.println(resp.get("err_code"));
                    System.out.println(resp.get("err_code_des"));
                }
            } else {
                //TODO 错误信息
                System.out.println(resp.get("return_msg"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    @Override
    public Map<String, String> orderRefund() {
        return null;
    }

    @Override
    public Map<String, String> orderRefundQuery() {
        return null;
    }

    @Override
    public Map<String, String> orderClose() {
        return null;
    }
}
