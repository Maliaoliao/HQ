package com.hq.system.mapper;

import java.util.List;

import com.hq.system.domain.Kitchen;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author wxy
 * @date 2021-11-25
 */
public interface KitchenMapper {
    /**
     * 查询【请填写功能名称】
     *
     * @param kitchenId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public Kitchen selectKitchenById(Long kitchenId);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param kitchen 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Kitchen> selectKitchenList(Kitchen kitchen);

    /**
     * 新增【请填写功能名称】
     *
     * @param kitchen 【请填写功能名称】
     * @return 结果
     */
    public int insertKitchen(Kitchen kitchen);

    /**
     * 修改【请填写功能名称】
     *
     * @param kitchen 【请填写功能名称】
     * @return 结果
     */
    public int updateKitchen(Kitchen kitchen);

    /**
     * 删除【请填写功能名称】
     *
     * @param kitchenId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteKitchenById(Long kitchenId);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param kitchenIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteKitchenByIds(Long[] kitchenIds);
    }
