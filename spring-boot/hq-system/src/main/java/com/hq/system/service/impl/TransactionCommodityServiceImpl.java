package com.hq.system.service.impl;

import java.util.List;
                                                                                                        import com.hq.common.utils.DateUtils;
            import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
    import com.hq.system.mapper.TransactionCommodityMapper;
import com.hq.system.domain.TransactionCommodity;
import com.hq.system.service.ITransactionCommodityService;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author wxy
 * @date 2021-10-28
 */
@Service
public class TransactionCommodityServiceImpl implements ITransactionCommodityService {
    @Autowired
    private TransactionCommodityMapper transactionCommodityMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param commodityId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public TransactionCommodity selectTransactionCommodityById(Long commodityId) {
        return transactionCommodityMapper.selectTransactionCommodityById(commodityId);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param transactionCommodity 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<TransactionCommodity> selectTransactionCommodityList(TransactionCommodity transactionCommodity) {
        return transactionCommodityMapper.selectTransactionCommodityList(transactionCommodity);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param transactionCommodity 【请填写功能名称】
     * @return 结果
     */
            @Override
    public int insertTransactionCommodity(TransactionCommodity transactionCommodity) {
                                                                                                                                                                                transactionCommodity.setCreateTime(DateUtils.getNowDate());
                                                                                return transactionCommodityMapper.insertTransactionCommodity(transactionCommodity);
            }

    /**
     * 修改【请填写功能名称】
     *
     * @param transactionCommodity 【请填写功能名称】
     * @return 结果
     */
            @Override
    public int updateTransactionCommodity(TransactionCommodity transactionCommodity) {
                                                                                                                                                                                                                        transactionCommodity.setUpdateTime(DateUtils.getNowDate());
                                    return transactionCommodityMapper.updateTransactionCommodity(transactionCommodity);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param commodityIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
            @Override
    public int deleteTransactionCommodityByIds(Long[] commodityIds) {
                return transactionCommodityMapper.deleteTransactionCommodityByIds(commodityIds);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param commodityId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteTransactionCommodityById(Long commodityId) {
                return transactionCommodityMapper.deleteTransactionCommodityById(commodityId);
    }
    }
