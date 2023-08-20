package com.hq.system.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * 继承WXPayConfig，用于获取微信商户信息.
 *
 * @author: mall
 * @date: 2022-06-25 22:10
 */
@Component
public class HQPayConfig{

    /** 商户号 */
    @Value("${wx.miniapp.mchid}")
    public String merchantId;
    /** 商户API私钥路径 */
    @Value("${wx.miniapp.key}")
    public String privateKeyPath;

    /** 商户证书序列号 */
    @Value("${wx.miniapp.mchid}")
    public String merchantSerialNumber;
    /** 商户APIV3密钥 */
    @Value("${wx.miniapp.key}")
    public String apiV3key;
}
