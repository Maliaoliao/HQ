package com.hq.system.service.impl;

import java.util.List;
                                                                                                                                                                                import com.hq.common.utils.DateUtils;
            import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
    import com.hq.system.mapper.KitchenMapper;
import com.hq.system.domain.Kitchen;
import com.hq.system.service.IKitchenService;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author wxy
 * @date 2021-11-25
 */
@Service
public class KitchenServiceImpl implements IKitchenService {
    @Autowired
    private KitchenMapper kitchenMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param kitchenId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public Kitchen selectKitchenById(Long kitchenId) {
        return kitchenMapper.selectKitchenById(kitchenId);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param kitchen 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Kitchen> selectKitchenList(Kitchen kitchen) {
        return kitchenMapper.selectKitchenList(kitchen);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param kitchen 【请填写功能名称】
     * @return 结果
     */
            @Override
    public int insertKitchen(Kitchen kitchen) {
                                                                                                                                                                                                                                                                                                        kitchen.setCreateTime(DateUtils.getNowDate());
                                                                                return kitchenMapper.insertKitchen(kitchen);
            }

    /**
     * 修改【请填写功能名称】
     *
     * @param kitchen 【请填写功能名称】
     * @return 结果
     */
            @Override
    public int updateKitchen(Kitchen kitchen) {
                                                                                                                                                                                                                                                                                                                                                kitchen.setUpdateTime(DateUtils.getNowDate());
                                    return kitchenMapper.updateKitchen(kitchen);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param kitchenIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
            @Override
    public int deleteKitchenByIds(Long[] kitchenIds) {
                return kitchenMapper.deleteKitchenByIds(kitchenIds);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param kitchenId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteKitchenById(Long kitchenId) {
                return kitchenMapper.deleteKitchenById(kitchenId);
    }
    }
