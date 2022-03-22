package com.hq.system.mapper;

import java.util.List;

import com.hq.system.domain.KitchenMaterial;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author wxy
 * @date 2021-11-26
 */
public interface KitchenMaterialMapper {
    /**
     * 查询【请填写功能名称】
     *
     * @param materialId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public KitchenMaterial selectKitchenMaterialById(Long materialId);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param kitchenMaterial 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<KitchenMaterial> selectKitchenMaterialList(KitchenMaterial kitchenMaterial);

    /**
     * 新增【请填写功能名称】
     *
     * @param kitchenMaterial 【请填写功能名称】
     * @return 结果
     */
    public int insertKitchenMaterial(KitchenMaterial kitchenMaterial);

    /**
     * 修改【请填写功能名称】
     *
     * @param kitchenMaterial 【请填写功能名称】
     * @return 结果
     */
    public int updateKitchenMaterial(KitchenMaterial kitchenMaterial);

    /**
     * 删除【请填写功能名称】
     *
     * @param materialId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteKitchenMaterialById(Long materialId);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param materialIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteKitchenMaterialByIds(Long[] materialIds);
    }
