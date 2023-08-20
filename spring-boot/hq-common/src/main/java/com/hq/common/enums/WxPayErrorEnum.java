package com.hq.common.enums;

/**
 * 微信支付错误码.
 *
 * @author: mall
 * @date: 2023-01-06 13:33
 **/
public enum WxPayErrorEnum {

    /**
     * 系统错误，直接抛出异常
     */
    ORDERCLOSED(0, "支付确认失败", "订单已关闭", "该订单已关", "商户订单号异常，请重新下单支付"),
    PARAM_ERROR(0, "支付确认失败", "参数错误", "请求参数未按指引进行填写", "请根据接口返回的详细信息检查您的程序"),
    ORDERPAID(0, "支付确认失败", "订单已支付", "订单号重复", "请确认该订单号是否重复支付，如果是新订单，请使用新订单号提交"),
    NOAUTH(0, "支付确认失败", "商户无权限", "商户没有开通被扫支付权限", "请开通商户号权限。请联系产品或商务申请"),
    XML_FORMAT_ERROR(0, "支付确认失败", "XML格式错误", "XML格式错误", "请检查XML参数格式是否正确"),
    REQUIRE_POST_METHOD(0, "支付确认失败", "请使用post方法", "未使用post传递参数", "请检查请求参数是否通过post方法提交"),
    SIGNERROR(0, "支付确认失败", "签名错误", "参数签名结果不正确", "请检查签名参数和方法是否都符合签名算法要求"),
    LACK_PARAMS(0, "支付确认失败", "缺少参数", "缺少必要的请求参数", "请检查参数是否齐全"),
    NOT_UTF8(0, "支付确认失败", "编码格式错误", "未使用指定编码格式", "请使用UTF-8编码格式"),
    BUYER_MISMATCH(0, "支付确认失败", "支付账号错误", "暂不支持同一笔订单更换支付方", "请确认支付方是否相同"),
    APPID_NOT_EXIST(0, "支付确认失败", "APPID不存在", "参数中缺少APPID", "请检查APPID是否正确"),
    MCHID_NOT_EXIST(0, "支付确认失败", "MCHID不存在", "参数中缺少MCHID", "请检查MCHID是否正确"),
    OUT_TRADE_NO_USED(0, "支付确认失败", "商户订单号重复", "同一笔交易不能多次提交", "请核实商户订单号是否重复提交"),
    APPID_MCHID_NOT_MATCH(0, "支付确认失败", "appid和mch_id不匹配", "appid和mch_id不匹配", "请确认appid和mch_id是否匹配"),
    INVALID_REQUEST(0, "支付确认失败", "无效请求", "商户系统异常导致，商户权限异常、重复请求支付、证书错误、频率限制等", "请确认商户系统是否正常，是否具有相应支付权限，确认证书是否正确，控制频率"),
    TRADE_ERROR(0, "支付确认失败", "交易错误", "业务错误导致交易失败、用户账号异常、风控、规则限制等", "请确认账号是否存在异常"),

    /**
     * 支付结果未知，需要调用查询接口再做判断
     */
    SYSTEMERROR(1, "支付结果未知", "接口返回错误", "系统超时", "请立即调用被扫订单结果查询API，查询当前订单状态，并根据订单的状态决定下一步的操作。"),
    BANKERROR(1, "支付结果未知", "银行系统异常", "银行端超时", "请立即调用被扫订单结果查询API，查询当前订单的不同状态，决定下一步的操作。"),
    USERPAYING(1, "支付结果未知", "用户支付中，需要输入密码", "该笔交易因为业务规则要求，需要用户输入支付密码。", "等待5秒，然后调用被扫订单结果查询API，查询当前订单的不同状态，决定下一步的操作。"),

    /*
     * 提示用户重新操作，二维码无效等
     */
    AUTHCODEEXPIRE(2, "支付确认失败", "二维码已过期，请用户在微信上刷新后再试", "用户的条码已经过期", "请收银员提示用户，请用户在微信上刷新条码，然后请收银员重新扫码。 直接将错误展示给收银员"),
    NOTENOUGH(2, "支付确认失败", "余额不足", "用户的零钱余额不足", "请收银员提示用户更换当前支付的卡，然后请收银员重新扫码。建议：商户系统返回给收银台的提示为“用户余额不足.提示用户换卡支付”"),
    NOTSUPORTCARD(2, "支付确认失败", "不支持卡类型", "用户使用卡种不支持当前支付形式", "请用户重新选择卡种 建议：商户系统返回给收银台的提示为“该卡不支持当前支付，提示用户换卡支付或绑新卡支付”"),
    ORDERREVERSED(2, "支付确认失败", "订单已撤销", "当前订单已经被撤销", "当前订单状态为“订单已撤销”，请提示用户重新支付"),
    AUTH_CODE_ERROR(2, "支付确认失败", "付款码参数错误", "请求参数未按指引进行填写", "每个二维码仅限使用一次，请刷新再试"),
    AUTH_CODE_INVALID(2, "支付确认失败", "付款码检验错误", "收银员扫描的不是微信支付的条码", "请扫描微信支付被扫条码/二维码");


    //处理类型 0:系统错误，直接抛出异常 1:未知结果，需要调用查询接口再做判断 2:提示用户重新操作，二维码无效等
    public int pStatus;
    public String paymentResult;
    public String errorCode;
    public String reason;
    public String solution;

    public String getPaymentResult() {
        return paymentResult;
    }

    public void setPaymentResult(String paymentResult) {
        this.paymentResult = paymentResult;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public int getpStatus() {
        return pStatus;
    }

    public void setpStatus(int pStatus) {
        this.pStatus = pStatus;
    }

    WxPayErrorEnum(int pStatus, String paymentResult, String errorCode, String reason, String solution) {
        this.pStatus = pStatus;
        this.paymentResult = paymentResult;
        this.errorCode = errorCode;
        this.reason = reason;
        this.solution = solution;
    }
}
