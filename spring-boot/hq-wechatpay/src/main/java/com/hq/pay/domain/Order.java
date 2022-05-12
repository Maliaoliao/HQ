package com.hq.pay.domain;

/**
 * 订单信息.
 *
 * @author: mall
 * @date: 2022 -05-12 14:21
 */
public class Order {

    //应用ID
    private String appid;
    //直连商户号
    private String mchid;
    //商品描述
    private String description;
    //商户订单号
    private String out_trade_no;
    //交易结束时间
    private String time_expire;
    //附加数据
    private String attach;
    //notify_url
    private String notify_url;
    //goods_tag
    private String goods_tag;

    //订单金额
    private class amount {

        //总金额
        private int total;
        //货币类型
        private String currency;
    }

    /*//优惠功能
    private class detail {

        private int cost_price;
        private String invoice_id;

        private class goods_detail {
            private String merchant_goods_id;
            private String wechatpay_goods_id;
            private String goods_name;
            private int quantity;
            private int unit_price;
        }
    }

    //场景信息
    private class scene_info {


        private String payer_client_ip;
        private String device_id;

        private class store_info {
            //门店编号
            private String id;
            //门店名称
            private String name;
            //地区编码
            private String area_code;
            //详细地址
            private String address;
        }
    }

    //结算信息
    private class settle_info {
        //是否指定分账
        private boolean profit_sharing;
    }
*/

}
