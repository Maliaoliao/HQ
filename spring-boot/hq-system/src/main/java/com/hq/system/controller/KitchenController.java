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
import com.hq.system.domain.Kitchen;
import com.hq.system.service.IKitchenService;
import com.hq.common.utils.poi.ExcelUtil;
import com.hq.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author wxy
 * @date 2021-11-25
 */
@RestController
@RequestMapping("/system/kitchen")
public class KitchenController extends BaseController {
    @Autowired
    private IKitchenService kitchenService;

/**
 * 查询【请填写功能名称】列表
 */
@PreAuthorize("@ss.hasPermi('system:kitchen:list')")
@GetMapping("/list")
        public TableDataInfo list(Kitchen kitchen) {
        startPage();
        List<Kitchen> list = kitchenService.selectKitchenList(kitchen);
        return getDataTable(list);
    }
    
    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:kitchen:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Kitchen kitchen) {
        List<Kitchen> list = kitchenService.selectKitchenList(kitchen);
        ExcelUtil<Kitchen> util = new ExcelUtil<Kitchen>(Kitchen. class);
        return util.exportExcel(list, "kitchen");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:kitchen:query')")
    @GetMapping(value = "/{kitchenId}")
    public AjaxResult getInfo(@PathVariable("kitchenId") Long kitchenId) {
        return AjaxResult.success(kitchenService.selectKitchenById(kitchenId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:kitchen:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Kitchen kitchen) {
        return toAjax(kitchenService.insertKitchen(kitchen));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:kitchen:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Kitchen kitchen) {
        return toAjax(kitchenService.updateKitchen(kitchen));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:kitchen:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @DeleteMapping("/{kitchenIds}")
    public AjaxResult remove(@PathVariable Long[] kitchenIds) {
        return toAjax(kitchenService.deleteKitchenByIds(kitchenIds));
    }
}
