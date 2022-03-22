package com.hq.system.mapper;

import java.util.List;

import com.hq.system.domain.TransactionCommodity;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author wxy
 * @date 2021-10-28
 */
public interface TransactionCommodityMapper {
    /**
     * 查询【请填写功能名称】
     *
     * @param commodityId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public TransactionCommodity selectTransactionCommodityById(Long commodityId);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param transactionCommodity 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TransactionCommodity> selectTransactionCommodityList(TransactionCommodity transactionCommodity);

    /**
     * 新增【请填写功能名称】
     *
     * @param transactionCommodity 【请填写功能名称】
     * @return 结果
     */
    public int insertTransactionCommodity(TransactionCommodity transactionCommodity);

    /**
     * 修改【请填写功能名称】
     *
     * @param transactionCommodity 【请填写功能名称】
     * @return 结果
     */
    public int updateTransactionCommodity(TransactionCommodity transactionCommodity);

    /**
     * 删除【请填写功能名称】
     *
     * @param commodityId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTransactionCommodityById(Long commodityId);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param commodityIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTransactionCommodityByIds(Long[] commodityIds);
    }
