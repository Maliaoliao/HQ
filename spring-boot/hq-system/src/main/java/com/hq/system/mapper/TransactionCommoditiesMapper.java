package com.hq.system.mapper;

import java.util.List;

import com.hq.system.domain.TransactionCommodities;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author wxy
 * @date 2021-10-28
 */
public interface TransactionCommoditiesMapper {
    /**
     * 查询【请填写功能名称】
     *
     * @param commodityId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public TransactionCommodities selectTransactionCommoditiesById(Long commodityId);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param transactionCommodities 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<TransactionCommodities> selectTransactionCommoditiesList(TransactionCommodities transactionCommodities);

    /**
     * 新增【请填写功能名称】
     *
     * @param transactionCommodities 【请填写功能名称】
     * @return 结果
     */
    public int insertTransactionCommodities(TransactionCommodities transactionCommodities);

    /**
     * 修改【请填写功能名称】
     *
     * @param transactionCommodities 【请填写功能名称】
     * @return 结果
     */
    public int updateTransactionCommodities(TransactionCommodities transactionCommodities);

    /**
     * 删除【请填写功能名称】
     *
     * @param commodityId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteTransactionCommoditiesById(Long commodityId);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param commodityIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTransactionCommoditiesByIds(Long[] commodityIds);
    }
