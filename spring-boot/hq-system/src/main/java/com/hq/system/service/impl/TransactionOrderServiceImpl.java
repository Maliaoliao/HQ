package com.hq.system.service.impl;

import com.hq.common.utils.DateUtils;

import com.hq.system.domain.TransactionOrder;
import com.hq.system.mapper.TransactionOrderMapper;
import com.hq.system.service.ITransactionOrderService;
import com.hq.system.service.IWXPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author mall
 * @date 2022-12-27
 */
@Service
public class TransactionOrderServiceImpl implements ITransactionOrderService {
    @Autowired
    private TransactionOrderMapper transactionOrderMapper;

    @Autowired
    IWXPayService iwxPayService;


    /**
     * 查询【请填写功能名称】
     *
     * @param orderId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public TransactionOrder selectTransactionOrderById(Long orderId) {
        return transactionOrderMapper.selectTransactionOrderById(orderId);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param transactionOrder 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TransactionOrder> selectTransactionOrderList(TransactionOrder transactionOrder) {
        return transactionOrderMapper.selectTransactionOrderList(transactionOrder);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param transactionOrder 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertTransactionOrder(TransactionOrder transactionOrder) {
        transactionOrder.setCreateTime(DateUtils.getNowDate());
        return transactionOrderMapper.insertTransactionOrder(transactionOrder);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param transactionOrder 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateTransactionOrder(TransactionOrder transactionOrder) {
        transactionOrder.setUpdateTime(DateUtils.getNowDate());
        return transactionOrderMapper.updateTransactionOrder(transactionOrder);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param orderIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTransactionOrderByIds(Long[] orderIds) {
        return transactionOrderMapper.deleteTransactionOrderByIds(orderIds);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param orderId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTransactionOrderById(Long orderId) {
        return transactionOrderMapper.deleteTransactionOrderById(orderId);
    }

    @Override
    public int returnGoodsTransactionOrderById(TransactionOrder t) throws Exception {

        int res = -1;
        Map<String, String> map = iwxPayService.orderRefund(t.getTransactionId(), t.getTotalFee(), t.getTotalFee(), "客户对餐品不满意");
        if("SUCCESS".equals(map.get("return_code"))){
            //退款成功，修改数据
            TransactionOrder transaction = new TransactionOrder();
            transaction.setOrderId(t.getOrderId());
            transaction.setIsSubscribe("Y");
            transaction.setRemark("客户对餐品不满意");
            res = transactionOrderMapper.updateTransactionOrder(transaction);
        }
        System.out.println(res);
        return res;
    }
}
