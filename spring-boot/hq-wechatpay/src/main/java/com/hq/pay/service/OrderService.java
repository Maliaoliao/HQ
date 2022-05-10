package com.hq.pay.service;

/**
 * 微信下单服务.
 *
 * @author: mall
 * @date: 2022 -05-10 10:47
 */
public interface OrderService {


    /**
     * 获取微信支付二维码.
     *
     * @return the string
     */
    String placeAnOrder();

}
