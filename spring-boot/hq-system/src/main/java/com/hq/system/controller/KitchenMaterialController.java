package com.hq.system.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hq.common.annotation.Log;
import com.hq.common.core.controller.BaseController;
import com.hq.common.core.domain.AjaxResult;
import com.hq.common.enums.BusinessType;
import com.hq.system.domain.KitchenMaterial;
import com.hq.system.service.IKitchenMaterialService;
import com.hq.common.utils.poi.ExcelUtil;
import com.hq.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author wxy
 * @date 2021-11-26
 */
@RestController
@RequestMapping("/system/material")
public class KitchenMaterialController extends BaseController {
    @Autowired
    private IKitchenMaterialService kitchenMaterialService;

/**
 * 查询【请填写功能名称】列表
 */
@PreAuthorize("@ss.hasPermi('system:material:list')")
@GetMapping("/list")
        public TableDataInfo list(KitchenMaterial kitchenMaterial) {
        startPage();
        List<KitchenMaterial> list = kitchenMaterialService.selectKitchenMaterialList(kitchenMaterial);
        return getDataTable(list);
    }
    
    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:material:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(KitchenMaterial kitchenMaterial) {
        List<KitchenMaterial> list = kitchenMaterialService.selectKitchenMaterialList(kitchenMaterial);
        ExcelUtil<KitchenMaterial> util = new ExcelUtil<KitchenMaterial>(KitchenMaterial. class);
        return util.exportExcel(list, "material");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:material:query')")
    @GetMapping(value = "/{materialId}")
    public AjaxResult getInfo(@PathVariable("materialId") Long materialId) {
        return AjaxResult.success(kitchenMaterialService.selectKitchenMaterialById(materialId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:material:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody KitchenMaterial kitchenMaterial) {
        return toAjax(kitchenMaterialService.insertKitchenMaterial(kitchenMaterial));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:material:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody KitchenMaterial kitchenMaterial) {
        return toAjax(kitchenMaterialService.updateKitchenMaterial(kitchenMaterial));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:material:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @DeleteMapping("/{materialIds}")
    public AjaxResult remove(@PathVariable Long[] materialIds) {
        return toAjax(kitchenMaterialService.deleteKitchenMaterialByIds(materialIds));
    }
}
