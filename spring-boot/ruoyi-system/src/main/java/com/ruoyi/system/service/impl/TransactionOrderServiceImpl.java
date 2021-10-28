package com.ruoyi.system.service.impl;

import java.util.List;
                                                                                                                    import com.ruoyi.common.utils.DateUtils;
            import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
    import com.ruoyi.system.mapper.TransactionOrderMapper;
import com.ruoyi.system.domain.TransactionOrder;
import com.ruoyi.system.service.ITransactionOrderService;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author mall
 * @date 2021-10-26
 */
@Service
public class TransactionOrderServiceImpl implements ITransactionOrderService {
    @Autowired
    private TransactionOrderMapper transactionOrderMapper;

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
    }
