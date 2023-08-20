package com.hq.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.hq.common.core.domain.AjaxResult;
import com.hq.common.core.redis.RedisCache;
import com.hq.common.enums.WxPayErrorEnum;
import com.hq.common.utils.DateUtils;
import com.hq.system.config.HQPayConfig;
import com.hq.system.domain.TransactionOrder;
import com.hq.system.service.ITransactionOrderService;
import com.hq.system.service.IWXPayService;
import com.hq.system.utils.MD5Util;
import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.util.AesUtil;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.exception.HttpException;
import com.wechat.pay.java.core.exception.MalformedMessageException;
import com.wechat.pay.java.core.exception.ServiceException;
import com.wechat.pay.java.service.payments.model.Transaction;
import com.wechat.pay.java.service.payments.nativepay.NativePayService;
import com.wechat.pay.java.service.payments.nativepay.model.Amount;
import com.wechat.pay.java.service.payments.nativepay.model.PrepayRequest;
import com.wechat.pay.java.service.payments.nativepay.model.PrepayResponse;
import com.wechat.pay.java.service.payments.nativepay.model.QueryOrderByOutTradeNoRequest;
import com.wechat.pay.java.service.refund.RefundService;
import com.wechat.pay.java.service.refund.model.CreateRequest;
import com.wechat.pay.java.service.refund.model.Refund;
import me.chanjar.weixin.common.error.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 订单支付实现.
 *
 * @author: mall
 * @date: 2022-06-25 22:07
 */
@Service
public class WXPayServiceImpl implements IWXPayService {

    Logger logger = LoggerFactory.getLogger(WXPayServiceImpl.class);
    //成功标识
    private final static String SUCCESS = "SUCCESS";
    //付款码支付
    private final static String TRADE_TYPE = "MICROPAY";

    @Autowired
    ITransactionOrderService transactionOrderService;

    @Autowired
    RedisCache redisCache;

    @Autowired
    HQPayConfig hqConfig;

    //商户api证书私钥文件的私钥串，apiclient_key.pem文本形式打开即可获取，不需要前后缀
    static String s = "商户api证书私钥文件的私钥串";


    @Override
    public AjaxResult orderPay(String json) throws Exception {
        /*Map map = JSON.parseObject(json);

        //构建基础config
        Config config =
                new RSAAutoCertificateConfig.Builder()
                        .merchantId(hqConfig.getMerchantId())
                        .privateKeyFromPath(hqConfig.getPrivateKeyPath())
                        .merchantSerialNumber(hqConfig.getMerchantSerialNumber())
                        .apiV3Key(hqConfig.getApiV3key())
                        .build();
        MicrNativePayService service = new NativePayService.Builder().config(config).build();
        // request.setXxx(val)设置所需参数，具体参数可见Request定义
        PrepayRequest request = new PrepayRequest();
        Amount amount = new Amount();
        //支付金额
        amount.setTotal(100);//map.get("total_fee")
        request.setAmount(amount);
        request.setAppid("wxa9d9651ae******");
        request.setMchid("190000****");
        request.setDescription("测试商品标题");
        request.setNotifyUrl("https://notify_url");
        request.setOutTradeNo("out_trade_no_001");
        WXPay wxpay = new WXPay(config);*/
        // 调用下单方法，得到应答
        //PrepayResponse response = service.prepay(request);
        // 使用微信扫描 code_url 对应的二维码，即可体验Native支付
        //System.out.println(response.getCodeUrl());
        //json 转 SortedMap
        //微信支付接口
        /*WXPay wxpay = new WXPay(config);
        //基本请求参数
        SortedMap<String, Object> data = getSystemParameter(config);
        //商品描述
        data.put("body", map.get("body"));
        //TODO 先这样生成，后端生成
        String outTradeNo = DateUtils.dateTimeDetailed();
        //订单号
        data.put("out_trade_no", outTradeNo);
        //订单金额 单位 分
        data.put("total_fee", map.get("total_fee"));
        //TODO 订单优惠金额
        //终端ip地址 自动售货机
        data.put("spbill_create_ip", map.get("spbill_create_ip"));
        //扫码支付付款码，设备读取用户微信中的条码或者二维码信息,用户付款码规则：18位纯数字，前缀以10、11、12、13、14、15开头）
        //可能需要前端做简单校验
        data.put("auth_code", map.get("auth_code"));
        //签名
        String signTmp = getSignString(data, config.getKey());
        //签名算法
        data.put("sign", signTmp);
        //临时交易前半部分保存到缓存中
        //redisCache.setCacheMap(outTradeNo, map);
        Map<String, String> resp;
        try {
            //调用微信支付接口
            resp = wxpay.unifiedOrder((Map) data);
            //通讯错误
            if(!SUCCESS.equals(resp.get("return_code"))){
                throw new WxPayException(resp.get("return_msg"));
            }
            //业务错误
            if(!SUCCESS.equals(resp.get("result_code"))){
                //根据错误码返回错误信息
                return AjaxResult.success(noticeOfPaymentResult(resp.get("err_code")));
            }
            //交易类型错误
            if(!TRADE_TYPE.equals(resp.get("trade_type"))){
                return AjaxResult.error("交易类型错误");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return AjaxResult.error(e.getMessage());
        }
        //交易成功之后，保存数据
        //商品ID
        resp.put("commodity_id", map.get("commodity_id").toString());
        //机器ID
        resp.put("spbill_create_ip",map.get("spbill_create_ip").toString());
        addDransaction(resp);
        return AjaxResult.success();*/
        return null;
    }

    @Override
    public Map<String, String> orderQuery(String transactionId) throws Exception {

        /*//json 转 SortedMap
        //商户基础配置
        HQPayConfig config = new HQPayConfig();
        //微信支付接口
        WXPay wxpay = new WXPay(config);
        SortedMap<String, Object> data = getSystemParameter(config);
        //微信订单号
        data.put("out_trade_no", transactionId);
        //每个接口的最后一个参数都是签名
        String sign = getSignString(data, config.getKey());
        //签名算法
        data.put("sign", sign);

        Map returnMap = new HashMap();
        try {
            Map resp = wxpay.orderQuery((Map) data);
            if (SUCCESS.equals(resp.get("return_code"))) {
                //接口通信情况正常
                if (SUCCESS.equals(resp.get("result_code"))) {
                    if (SUCCESS.equals(resp.get("trade_state"))) {
                        //交易成功
                        returnMap = resp;
                        returnMap.put("state", "1");
                    }
                } else {
                    returnMap.put("err_code", resp.get("err_code"));
                    returnMap.put("err_code_des", resp.get("err_code_des"));
                    returnMap.put("state", "0");
                }
            } else {
                returnMap.put("state", "0");
                returnMap.put("return_msg", resp.get("return_msg"));
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return returnMap;*/
        return null;

    }

    @Override
    public Map<String, String> orderRefund(String transactionId, Long totalFee, Long refundFee, String refundDesc) throws Exception {
        /*//获取配置文件
        HQPayConfig config = new HQPayConfig();
        WXPay wxPay = new WXPay(config);
        SortedMap<String, Object> data = getSystemParameter(config);
        //微信支付订单号
        data.put("transaction_id", transactionId);
        //商户退单号，由商户生成 todo 使用其他工具生成随机数
        data.put("out_refund_no", "2016090910595900000012");
        //订单金额 单位 分 用户支付金额
        data.put("total_fee", totalFee);
        //TODO 退款金额  需要判断退款金额小于支付金额
        data.put("refund_fee", refundFee);
        //退款描述
        data.put("refund_desc", refundDesc);
        String signTmp = getSignString(data, config.getKey());
        //签名算法
        data.put("sign", signTmp);
        //退款接口
        //Map<String, String> resp = new HashMap();
        wxPay.refund((Map)data);*/
        /*resp.put("return_code", "SUCCESS");
        //表示退款请求成功
        SUCCESS.equals(resp.get("return_code"));
        return resp;*/
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


    /**
     * 拼接一些常用的系统参数.
     *
     * @param config the config
     * @return the sorted map
     */
    /*public SortedMap<String, Object> getSystemParameter(WXPayConfig config) {
        //返回一些默认的系统参数
        SortedMap<String, Object> sortedMap = new TreeMap<>();
        MD5Util md = new MD5Util();
        //产生16位随机数
        String nonceStr = md.getNoncestr(16).toUpperCase();
        //公众号ID
        sortedMap.put("appid", config.getAppID());
        //商户号
        sortedMap.put("mch_id", config.getMchID());
        //随机字符串
        sortedMap.put("nonce_str", nonceStr);
        return sortedMap;
    }*/

    /**
     * 生成签名(每个接口的最后一个参数都是签名).
     *
     * @param sortedMap the sorted map
     * @param key       the key
     * @return the string
     */
    public String getSignString(SortedMap<String, Object> sortedMap, String key) {
        return new MD5Util().createSign(sortedMap, key).toUpperCase();
    }


    /**
     * 自动校验.
     *
     * @param checkMap  需要校验的字符 k,字符，v 校验内容
     * @param resultMap 原始map
     * @return 未通过校验的字符串信息 ，为空则是校验通过
     */
    public String getCode(Map<String, Object> checkMap, Map<String, String> resultMap) {
        AtomicReference<String> code = new AtomicReference<>("");
        checkMap.forEach((k, v) -> {
            if (!v.equals(resultMap.get(k))) {
                code.set(k);
                return;
            }
        });
        return code.get();
    }


    /**
     * 保存交易数据.
     */
    public void addDransaction(Map<String, String> map) throws ParseException {

        //请求过来的数据暂时保存在缓存中
        TransactionOrder transactionOrder = new TransactionOrder();
        //https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_10&index=1 详细字段解释参阅
        transactionOrder.setOpenid(map.get("openid"));
        transactionOrder.setIsSubscribe(map.get("is_subscribe"));

        transactionOrder.setTradeType(map.get("trade_type"));

        transactionOrder.setBankType(map.get("bank_type"));

        transactionOrder.setFeeType(map.get("fee_type"));

        transactionOrder.setTotalFee(Long.parseLong(map.get("total_fee")));

        transactionOrder.setSettlementTotalFee(Long.parseLong(map.get("settlement_total_fee")));

        transactionOrder.setCouponFee(Long.parseLong(map.get("coupon_fee")));

        transactionOrder.setCashFeeType(map.get("cash_fee_type"));

        transactionOrder.setCashFee(Long.parseLong(map.get("cash_fee")));

        transactionOrder.setTransactionId(map.get("transaction_id"));

        transactionOrder.setOutTradeNo(map.get("out_trade_no"));

        transactionOrder.setUpdateTime(new Date());

        transactionOrder.setAttach(map.get("attach"));

        transactionOrder.setTimeEnd(DateUtils.parseDate(map.get("time_end"), "yyyyMMddHHmmss"));

        transactionOrder.setPromotionDetail(map.get("promotion_detail"));

        transactionOrder.setCommodityId(Long.parseLong(map.get("commodity_id")));

        transactionOrder.setSpbillCreateIp(Long.parseLong(map.get("spbill_create_ip")));
        transactionOrder.setIsReceipt("N");
        transactionOrder.setUpdateBy("sysadmin");
        transactionOrder.setCreateBy("sysadmin");

        transactionOrderService.insertTransactionOrder(transactionOrder);
    }

    //1、支付结果未知，调用订单查询api查询支付状态，然后在做出对应的处理
        /*SYSTEMERROR
        BANKERROR
        USERPAYING*/
    //2、请求参数有误，检查参数问题
    //PARAM_ERROR
    //3、二维码有误，重新扫码

    //4、其他错误类型

    //5、订单问题
    //支付结果通知
    public Map<String, Object> noticeOfPaymentResult(String errorCode) throws Exception {

        Map<String, Object> map = new HashMap<>();

        WxPayErrorEnum wxPayErrorEnum = WxPayErrorEnum.valueOf(errorCode);

        map.put("pStatus", wxPayErrorEnum.getpStatus());

        //0:系统错误，直接抛出异常 1:未知结果，需要调用查询接口再做判断 2:提示用户重新操作，二维码无效等
        switch (wxPayErrorEnum.getpStatus()) {
            case 0:
                StringBuffer message = new StringBuffer();
                message.append(wxPayErrorEnum.getPaymentResult()).append(",").append(wxPayErrorEnum.getReason()).append(",").append(wxPayErrorEnum.getSolution());
                throw new WxErrorException(message.toString());
            case 1:
                //查询，返回支付结果
                orderQuery("123123");
            case 2:
                switch (errorCode) {
                    case "AUTHCODEEXPIRE":
                        map.put("errCode", "支付二维码已过期，请您刷新后再尝试");
                    case "NOTENOUGH":
                        map.put("errCode", "余额不足，请您尝试换卡支付");
                    case "NOTSUPORTCARD":
                        //不支持的卡类型
                        map.put("errCode", "该卡不支持当前支付，请您换卡支付或绑新卡支付");
                    case "ORDERREVERSED":
                        map.put("errCode", "该订单已撤销，请您重新下单");
                    case "AUTH_CODE_ERROR":
                        //二维码有问题，请重新扫码
                        map.put("errCode", "二维码已经被扫过，请您刷新尝试");
                    case "AUTH_CODE_INVALID":
                        //二维码有问题，请重新扫码
                        map.put("errCode", "请您出示正确的微信付款码");
                }
        }
        return map;
    }


    @Override
    public String createOrderByNative(String json) {
        //Map map = JSON.parseObject(json);
        //初始化配置
        Config config =
                new RSAAutoCertificateConfig.Builder()
                        .merchantId(hqConfig.merchantId)
                        // 使用 com.wechat.pay.java.core.util 中的函数从本地文件中加载商户私钥，商户私钥会用来生成请求的签名
                        .privateKeyFromPath(hqConfig.privateKeyPath)
                        .merchantSerialNumber(hqConfig.merchantSerialNumber)
                        .apiV3Key(hqConfig.apiV3key)
                        .build();

        // 初始化服务
        NativePayService nativePayService = new NativePayService.Builder().config(config).build();

        PrepayResponse res = new PrepayResponse();

        PrepayRequest request = new PrepayRequest();

        //应用ID
        request.setAppid("wxd678efh567hg6787");
        //商户直连号
        request.setMchid("1230000109");
        //商品描述
        request.setDescription("便当-青椒肉丝套餐");
        //订单号
        String outTradeNo = DateUtils.dateTimeDetailed();
        request.setOutTradeNo(outTradeNo);
        //回掉接口
        request.setNotifyUrl("https://xxxx");
        //支付数据
        Amount amount = new Amount();
        //订单金额，分
        amount.setTotal(100);
        try {
            res = nativePayService.prepay(request);
        } catch (HttpException e) { // 发送HTTP请求失败
            logger.error(e.getMessage(), e.getHttpRequest());
            // 调用e.getHttpRequest()获取请求打印日志或上报监控，更多方法见HttpException定义
        } catch (ServiceException e) { // 服务返回状态小于200或大于等于300，例如500
            logger.error(e.getMessage(), e.getResponseBody());
            // 调用e.getResponseBody()获取返回体打印日志或上报监控，更多方法见ServiceException定义
        } catch (MalformedMessageException e) { // 服务返回成功，返回体类型不合法，或者解析返回体失败
            logger.error(e.getMessage(), e.getMessage());
            // 调用e.getMessage()获取信息打印日志或上报监控，更多方法见MalformedMessageException定义
        }
        return res.getCodeUrl();
    }

    @Override
    public String paycallBack(HttpServletRequest request, HttpServletResponse response) throws IOException, GeneralSecurityException {
        String characterEncoding = request.getCharacterEncoding();
        System.out.println("characterEncoding=" + characterEncoding);
        //从请求头获取验签字段
        String Timestamp = request.getHeader("Wechatpay-Timestamp");
        String Nonce = request.getHeader("Wechatpay-Nonce");
        String Signature = request.getHeader("Wechatpay-Signature");
        String Serial = request.getHeader("Wechatpay-Serial");


        System.out.println("开始读取请求头的信息");
        //请求头
        System.out.println("Wechatpay-Timestamp=" + Timestamp);
        System.out.println("Wechatpay-Nonce=" + Nonce);
        System.out.println("Wechatpay-Signature=" + Signature);
        System.out.println("Wechatpay-Serial=" + Serial);

        System.out.println("=================");

        //加载平台证书,官方的sdk，s为商户api证书私钥
        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(new ByteArrayInputStream(s.getBytes("utf-8")));
        //加载官方自动更新证书
        AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
                //商户平台查看                            //不是API密钥
                new WechatPay2Credentials("商户号", new PrivateKeySigner("商户api证书序列号", merchantPrivateKey)), "APIv3密钥".getBytes("utf-8"));


        //读取请求体的信息
        System.out.println("开始读取请求体的信息");
        ServletInputStream inputStream = request.getInputStream();
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s;
        //读取回调请求体
        while ((s = bufferedReader.readLine()) != null) {
            stringBuffer.append(s);
        }


        String s1 = stringBuffer.toString();
        System.out.println("请求体" + s1);
        Map requestMap = (Map) JSON.parse(s1);


        //开始按照验签进行拼接
        String id = requestMap.get("id").toString();
        System.out.println("id=" + id);


        String resource = String.valueOf(requestMap.get("resource"));
        System.out.println("resource=" + resource);
        Map requestMap2 = (Map) JSON.parse(resource);

        String associated_data = requestMap2.get("associated_data").toString();
        String nonce = requestMap2.get("nonce").toString();
        String ciphertext = requestMap2.get("ciphertext").toString();


        //按照文档要求拼接验签串
        String VerifySignature = Timestamp + "\n" + Nonce + "\n" + s1 + "\n";
        System.out.println("拼接后的验签串=" + VerifySignature);

        //使用官方验签工具进行验签
        boolean verify1 = verifier.verify(Serial, VerifySignature.getBytes(), Signature);
        System.out.println("官方工具验签=" + verify1);


        //使用自己写的验签方法进行验签
        //赋一个默认值﻿
        boolean verify = false;
        try {
            verify = verify(VerifySignature, verifier.getValidCertificate(), Signature, Serial);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

        }
        System.out.println("验签方法进行验签=" + verify);

        //判断验签的结果
        System.out.println("=======判断验签结果=======");
        if (verify == false) {
            System.out.println("验签失败，应答接口");
            Map map = new HashMap<>();
            //响应接口

            //设置状态码
            response.setStatus(500);
            return "{" +
                    '"' + "code" + '"' + ":" + '"' + "FAIL" + '"' + " " +
                    '"' + "message" + '"' + ":" + '"' + "失败" + '"' +
                    "}";
        }
        System.out.println("验签成功后，开始进行解密");
        //解密，如果这里报错，就一定是APIv3密钥错误
        AesUtil aesUtil = new AesUtil("APIv3密钥".getBytes());
        String aes = aesUtil.decryptToString(associated_data.getBytes(), nonce.getBytes(), ciphertext);

        //
        System.out.println("解密后=" + aes);

        //TODO trade_state SUCCESS 支付成功之后出餐
        //如何通知
        Map map = new HashMap<>();
        //响应接口
        map.put("code", "SUCCESS");
        map.put("message", "成功");

        //设置状态码
        response.setStatus(200);
        return "{" +
                '"' + "code" + '"' + ":" + '"' + "SUCCESS" + '"' + " " +
                '"' + "message" + '"' + ":" + '"' + "成功" + '"' +
                "}";
    }

    //验签方法，非微信官方的，微信支付官方的基本也查不到，封装的方式不一样，下面也有
    public boolean verify(String srcData, X509Certificate certificate, String sign, String Serial) throws Exception {
        //先验证证书序列号是否正确
        if (certificate.getSerialNumber().toString(16).toUpperCase().equals(Serial)) {
            Signature sha256withRSA = Signature.getInstance("SHA256withRSA");
            sha256withRSA.initVerify(certificate.getPublicKey());
            sha256withRSA.update(srcData.getBytes());
            return sha256withRSA.verify(Base64Utils.decodeFromString(sign));
        } else {
            return false;
        }

    }

    @Override
    public void selectOrder(String tradeNo) {
        //用户下单成功后，可以通过订单id查询是否支付成功

        //Map map = JSON.parseObject(json);
        //初始化配置
        Config config =
                new RSAAutoCertificateConfig.Builder()
                        .merchantId(hqConfig.merchantId)
                        // 使用 com.wechat.pay.java.core.util 中的函数从本地文件中加载商户私钥，商户私钥会用来生成请求的签名
                        .privateKeyFromPath(hqConfig.privateKeyPath)
                        .merchantSerialNumber(hqConfig.merchantSerialNumber)
                        .apiV3Key(hqConfig.apiV3key)
                        .build();

        // 初始化服务
        NativePayService nativePayService = new NativePayService.Builder().config(config).build();
        PrepayResponse res = new PrepayResponse();

        QueryOrderByOutTradeNoRequest request = new QueryOrderByOutTradeNoRequest();

        //商户直连号
        request.setMchid("1230000109");
        //商户订单号,不是在同一事务中
        request.setOutTradeNo(tradeNo);
        try {
            Transaction t = nativePayService.queryOrderByOutTradeNo(request);
        } catch (HttpException e) { // 发送HTTP请求失败
            logger.error(e.getMessage(), e.getHttpRequest());
            // 调用e.getHttpRequest()获取请求打印日志或上报监控，更多方法见HttpException定义
        } catch (ServiceException e) { // 服务返回状态小于200或大于等于300，例如500
            logger.error(e.getMessage(), e.getResponseBody());
            // 调用e.getResponseBody()获取返回体打印日志或上报监控，更多方法见ServiceException定义
        } catch (MalformedMessageException e) { // 服务返回成功，返回体类型不合法，或者解析返回体失败
            logger.error(e.getMessage(), e.getMessage());
            // 调用e.getMessage()获取信息打印日志或上报监控，更多方法见MalformedMessageException定义
        }
    }

    @Override
    public void refund() {
        //用户下单成功后，可以通过订单id查询是否支付成功
        //Map map = JSON.parseObject(json);
        //初始化配置
        Config config =
                new RSAAutoCertificateConfig.Builder()
                        .merchantId(hqConfig.merchantId)
                        // 使用 com.wechat.pay.java.core.util 中的函数从本地文件中加载商户私钥，商户私钥会用来生成请求的签名
                        .privateKeyFromPath(hqConfig.privateKeyPath)
                        .merchantSerialNumber(hqConfig.merchantSerialNumber)
                        .apiV3Key(hqConfig.apiV3key)
                        .build();

        // 初始化服务
        RefundService service = new RefundService.Builder().config(config).build();
        //退货信息
        CreateRequest request = new CreateRequest();
        //订单号
        request.setTransactionId("xxxxxx");
        //退款单号
        request.setOutTradeNo("xxxxxx");
        com.wechat.pay.java.service.refund.model.Amount amount = new com.wechat.pay.java.service.refund.model.Amount();
        //退款金额
        amount.setRefund(111L);
        //原订单金额
        amount.setTotal(111L);
        //退款币种
        amount.setCurrency("CNY");
        try {
            Refund response = service.create(request);
            //退款状态 退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往商户平台-交易中心，手动处理此笔退款。
            //枚举值：
            //SUCCESS：退款成功
            //CLOSED：退款关闭
            //PROCESSING：退款处理中
            //ABNORMAL：退款异常
            //示例值：SUCCESS
            response.getStatus();
        } catch (HttpException e) { // 发送HTTP请求失败
            // 调用e.getHttpRequest()获取请求打印日志或上报监控，更多方法见HttpException定义
        } catch (ServiceException e) { // 服务返回状态小于200或大于等于300，例如500
            // 调用e.getResponseBody()获取返回体打印日志或上报监控，更多方法见ServiceException定义
        } catch (MalformedMessageException e) { // 服务返回成功，返回体类型不合法，或者解析返回体失败
            // 调用e.getMessage()获取信息打印日志或上报监控，更多方法见MalformedMessageException定义
        }

    }


}