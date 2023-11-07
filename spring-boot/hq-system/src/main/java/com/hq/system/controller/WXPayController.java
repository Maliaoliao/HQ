package com.hq.system.controller;

import com.hq.common.core.domain.AjaxResult;
import com.hq.system.config.HQPayConfig;
import com.hq.system.domain.TransactionOrder;
import com.hq.system.service.IWXPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.util.ArrayList;

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

    @PostMapping("/createorder")
    public String createOrderByNative(@RequestBody TransactionOrder transactionOrder) throws Exception {
        wxPayService.createOrderByNative(transactionOrder);
        return "123";
    }

    /**
     * 微信支付回掉函数
     */
    @PostMapping("/callback")
    public void wxPaycallBack (HttpServletRequest request, HttpServletResponse response) throws GeneralSecurityException, IOException {

        wxPayService.paycallBack(request, response);
    }
    public static void main(String[] args) {
        //1 -1
        String s = "abcdefg";
        System.out.println(s.indexOf(4));
        //2 8
        LocalDate l = LocalDate.now();
        System.out.println(l.getMonthValue());
        //3 死循环
        //System.out.println(add(10));
        //
        String str = "Aa100";
        System.out.println(str.matches("^[A-Za-z\\d]$"));

        // 10.100
        BigDecimal decimal=new BigDecimal("010.100");
        System.out.println(decimal);

        //redis 自增2的指令 incrby 2
        //redis 持久化有哪几种方式：rdb aof
        //mysql 查询当前正在执行 sql 的命令是：show processlist
        //mysql 自增主键每次都会增长 1吗？
        //

    }

    public static Integer add(Integer v){
        v = v / 2 - 1;
        if(v < -10){
            return v;
        }
        return add(v);
    }

}
