package com.hq.system.service;

import com.hq.common.core.domain.AjaxResult;
import com.hq.system.domain.TransactionOrder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;

/**
 * 微信支付相关接口.
 *
 * @author: mall
 * @date: 2022 -06-25 21:57
 */
@Component
public interface IWXPayService {


    /**
     * 读取用户二维码，用于支付订单
     *
     * @param json the json
     * @return map map
     * @throws Exception the exception
     */
    AjaxResult orderPay(String json) throws Exception;

    /**
     * 该接口提供所有微信支付订单的查询功能.
     *
     * @param transactionId 微信的订单号
     * @return the map
     * @throws Exception the exception
     */
    Map<String, String> orderQuery(String transactionId) throws Exception;

    /**
     * 退款.
     *
     * @param transactionId the 订单号
     * @param totalFee      the 订单总金额
     * @param refundFee     the 退款金额
     * @param refundDesc    the 退款原因
     * @return the map
     * @throws Exception the exception
     */
    Map<String, String> orderRefund(String transactionId, Long totalFee, Long refundFee, String refundDesc) throws Exception;

    /**
     * 退款查询.
     *
     * @return the map
     */
    Map<String, String> orderRefundQuery();

    /**
     * 订单关闭.
     *
     * @return the map
     */
    Map<String, String> orderClose();


    /**
     * Native 预下单，返回支付码，用于用户完成支付
     * @return
     */
    String createOrderByNative(TransactionOrder transactionOrder);

    /**
     * 用户支付通知
     *
     * @return
     */
    String paycallBack(HttpServletRequest request, HttpServletResponse response) throws IOException, GeneralSecurityException;

    /**
     * 订单查询呢
     */
    void selectOrder(String selectOrder);

    /**
     * 退款
     */
    void refund();
}
