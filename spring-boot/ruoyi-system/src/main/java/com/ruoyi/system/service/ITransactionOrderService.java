package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.TransactionOrder;

/**
 * 【请填写功能名称】Service接口
 *
 * @author mall
 * @date 2021-10-26
 */
public interface ITransactionOrderService {
    /**
     * 查询【请填写功能名称】
     *
     * @param orderId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public TransactionOrder selectTransactionOrderById(Long orderId);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param transactionOrder 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TransactionOrder> selectTransactionOrderList(TransactionOrder transactionOrder);

    /**
     * 新增【请填写功能名称】
     *
     * @param transactionOrder 【请填写功能名称】
     * @return 结果
     */
    public int insertTransactionOrder(TransactionOrder transactionOrder);

    /**
     * 修改【请填写功能名称】
     *
     * @param transactionOrder 【请填写功能名称】
     * @return 结果
     */
    public int updateTransactionOrder(TransactionOrder transactionOrder);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param orderIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTransactionOrderByIds(Long[] orderIds);

    /**
     * 删除【请填写功能名称】信息
     *
     * @param orderId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTransactionOrderById(Long orderId);
}
