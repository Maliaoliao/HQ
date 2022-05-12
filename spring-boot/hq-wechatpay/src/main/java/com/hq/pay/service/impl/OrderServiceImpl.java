package com.hq.pay.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hq.pay.api.ApiList;
import com.hq.pay.service.OrderService;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.List;

/**
 * 订单生成.
 *
 * @author: mall
 * @date: 2022-05-10 11:23
 **/
public class OrderServiceImpl implements OrderService {


    //商户号
    private final static String merchantId = "";
    //商户API证书的证书序列号。
    private final static String merchantSerialNumber = "";
    //商户API私钥，如何加载商户API私钥请看常见问题。
    private final static PrivateKey merchantPrivateKey = null;
    //微信支付平台证书。你也可以使用后面章节提到的“定时更新平台证书功能”，而不需要关心平台证书的来龙去脉
    private final static List<X509Certificate>  wechatpayCertificates = null;

    @Override
    public String placeAnOrder() throws IOException {


        //...
        /*WechatPayHttpClientBuilder builder = WechatPayHttpClientBuilder.create()
                .withMerchant(merchantId, merchantSerialNumber, merchantPrivateKey)
                .withWechatPay(wechatpayCertificates);
        // ... 接下来，你仍然可以通过builder设置各种参数，来配置你的HttpClient

        // 通过WechatPayHttpClientBuilder构造的HttpClient，会自动的处理签名和验签
        CloseableHttpClient httpClient = builder.build();
        HttpPost httpPost = new HttpPost(ApiList.PLACE_AN_ORDER);
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Content-type","application/json; charset=utf-8");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.put("mchid","1900009191")
                .put("appid", "wxd678efh567hg6787")
                .put("description", "Image形象店-深圳腾大-QQ公仔")
                .put("notify_url", "https://www.weixin.qq.com/wxpay/pay.php")
                .put("out_trade_no", "1217752501201407033233368018");
        rootNode.putObject("amount")
                .put("total", 1);
        rootNode.putObject("payer")
                .put("openid", "oUpF8uMuAJO_M2pxb1Q9zNjWeS6o");

        objectMapper.writeValue(bos, rootNode);

        httpPost.setEntity(new StringEntity(bos.toString("UTF-8"), "UTF-8"));

        CloseableHttpResponse response = httpClient.execute(httpPost);

        String bodyAsString = EntityUtils.toString(response.getEntity());
        return "";*/
        return ";";
    }


    @Before
    public void setup() throws IOException {
        /*// 加载商户私钥（privateKey：私钥字符串）
        PrivateKey merchantPrivateKey = PemUtil
                .loadPrivateKey(new ByteArrayInputStream(privateKey.getBytes("utf-8")));

        // 加载平台证书（mchId：商户号,mchSerialNo：商户证书序列号,apiV3Key：V3密钥）
        AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
                new WechatPay2Credentials(merchantId, new PrivateKeySigner(mchSerialNo, merchantPrivateKey)),apiV3Key.getBytes("utf-8"));

        // 初始化httpClient
        httpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(mchId, mchSerialNo, merchantPrivateKey)
                .withValidator(new WechatPay2Validator(verifier)).build();*/
    }

    @After
    public void after() throws IOException {
        //httpClient.close();
    }
}
