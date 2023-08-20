package com.hq.system.controller;

import com.hq.common.core.domain.AjaxResult;
import com.hq.system.config.HQPayConfig;
import com.hq.system.service.IWXPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 微信支付订单创建. 为什么 大哥，端口号没输对！！！！！！！！！！
 *
 * @author: mall
 * @date: 2022 -12-29 20:11
 */
@RestController
@RequestMapping("/wxapi")
public class WXPayController {


    //为什么，多模块下，入口类也需要注入，子类才可以被自动注入
    @Autowired
    IWXPayService wxPayService;
    @Autowired
    HQPayConfig config;

    /**
     * 微信支付接口.所有支付请求都是通过这个方法
     *
     * @param json the json
     * @throws Exception the exception
     */
    @PostMapping("/transactionOrder")
    public AjaxResult addtransactionOrder(@RequestParam(value = "json") String json) throws Exception {
        return AjaxResult.success(wxPayService.orderPay(json));
    }

    /**
     * Addtransaction order string.
     *
     * @return the string
     * @throws Exception the exception
     */
    @GetMapping("/transactionOrder")
    public String addtransactionOrder() throws Exception {
       // System.out.println(config.getKey());
        System.out.println("123123123");
        return "123";
    }

    @GetMapping("/createorder")
    public String createOrderByNative() throws Exception {
        wxPayService.createOrderByNative("");
        return "123";
    }


}
