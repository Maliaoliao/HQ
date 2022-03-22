package com.hq.system.service.impl;

import java.util.List;
                                                                                                                                                                                            import com.hq.common.utils.DateUtils;
            import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
    import com.hq.system.mapper.KitchenMaterialMapper;
import com.hq.system.domain.KitchenMaterial;
import com.hq.system.service.IKitchenMaterialService;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author wxy
 * @date 2021-11-26
 */
@Service
public class KitchenMaterialServiceImpl implements IKitchenMaterialService {
    @Autowired
    private KitchenMaterialMapper kitchenMaterialMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param materialId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public KitchenMaterial selectKitchenMaterialById(Long materialId) {
        return kitchenMaterialMapper.selectKitchenMaterialById(materialId);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param kitchenMaterial 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<KitchenMaterial> selectKitchenMaterialList(KitchenMaterial kitchenMaterial) {
        return kitchenMaterialMapper.selectKitchenMaterialList(kitchenMaterial);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param kitchenMaterial 【请填写功能名称】
     * @return 结果
     */
            @Override
    public int insertKitchenMaterial(KitchenMaterial kitchenMaterial) {
                                                                                                                                                                                                                                                                                                                            kitchenMaterial.setCreateTime(DateUtils.getNowDate());
                                                                                return kitchenMaterialMapper.insertKitchenMaterial(kitchenMaterial);
            }

    /**
     * 修改【请填写功能名称】
     *
     * @param kitchenMaterial 【请填写功能名称】
     * @return 结果
     */
            @Override
    public int updateKitchenMaterial(KitchenMaterial kitchenMaterial) {
                                                                                                                                                                                                                                                                                                                                                                    kitchenMaterial.setUpdateTime(DateUtils.getNowDate());
                                    return kitchenMaterialMapper.updateKitchenMaterial(kitchenMaterial);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param materialIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
            @Override
    public int deleteKitchenMaterialByIds(Long[] materialIds) {
                return kitchenMaterialMapper.deleteKitchenMaterialByIds(materialIds);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param materialId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteKitchenMaterialById(Long materialId) {
                return kitchenMaterialMapper.deleteKitchenMaterialById(materialId);
    }
    }
