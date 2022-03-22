package com.hq.system.service.impl;

import java.util.List;
                                                                                            import com.hq.common.utils.DateUtils;
            import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
    import com.hq.system.mapper.TransactionCommoditiesMapper;
import com.hq.system.domain.TransactionCommodities;
import com.hq.system.service.ITransactionCommoditiesService;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author wxy
 * @date 2021-10-28
 */
@Service
public class TransactionCommoditiesServiceImpl implements ITransactionCommoditiesService {
    @Autowired
    private TransactionCommoditiesMapper transactionCommoditiesMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param commodityId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public TransactionCommodities selectTransactionCommoditiesById(Long commodityId) {
        return transactionCommoditiesMapper.selectTransactionCommoditiesById(commodityId);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param transactionCommodities 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TransactionCommodities> selectTransactionCommoditiesList(TransactionCommodities transactionCommodities) {
        return transactionCommoditiesMapper.selectTransactionCommoditiesList(transactionCommodities);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param transactionCommodities 【请填写功能名称】
     * @return 结果
     */
            @Override
    public int insertTransactionCommodities(TransactionCommodities transactionCommodities) {
                                                                                                                                                            transactionCommodities.setCreateTime(DateUtils.getNowDate());
                                                                                return transactionCommoditiesMapper.insertTransactionCommodities(transactionCommodities);
            }

    /**
     * 修改【请填写功能名称】
     *
     * @param transactionCommodities 【请填写功能名称】
     * @return 结果
     */
            @Override
    public int updateTransactionCommodities(TransactionCommodities transactionCommodities) {
                                                                                                                                                                                                    transactionCommodities.setUpdateTime(DateUtils.getNowDate());
                                    return transactionCommoditiesMapper.updateTransactionCommodities(transactionCommodities);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param commodityIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
            @Override
    public int deleteTransactionCommoditiesByIds(Long[] commodityIds) {
                return transactionCommoditiesMapper.deleteTransactionCommoditiesByIds(commodityIds);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param commodityId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTransactionCommoditiesById(Long commodityId) {
                return transactionCommoditiesMapper.deleteTransactionCommoditiesById(commodityId);
    }
    }
