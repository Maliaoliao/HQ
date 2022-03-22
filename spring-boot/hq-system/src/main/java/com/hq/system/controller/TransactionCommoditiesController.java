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
import com.hq.system.domain.TransactionCommodities;
import com.hq.system.service.ITransactionCommoditiesService;
import com.hq.common.utils.poi.ExcelUtil;
import com.hq.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author wxy
 * @date 2021-10-28
 */
@RestController
@RequestMapping("/system/commodities")
public class TransactionCommoditiesController extends BaseController {
    @Autowired
    private ITransactionCommoditiesService transactionCommoditiesService;

/**
 * 查询【请填写功能名称】列表
 */
@PreAuthorize("@ss.hasPermi('system:commodities:list')")
@GetMapping("/list")
        public TableDataInfo list(TransactionCommodities transactionCommodities) {
        startPage();
        List<TransactionCommodities> list = transactionCommoditiesService.selectTransactionCommoditiesList(transactionCommodities);
        return getDataTable(list);
    }
    
    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:commodities:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TransactionCommodities transactionCommodities) {
        List<TransactionCommodities> list = transactionCommoditiesService.selectTransactionCommoditiesList(transactionCommodities);
        ExcelUtil<TransactionCommodities> util = new ExcelUtil<TransactionCommodities>(TransactionCommodities. class);
        return util.exportExcel(list, "commodities");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:commodities:query')")
    @GetMapping(value = "/{commodityId}")
    public AjaxResult getInfo(@PathVariable("commodityId") Long commodityId) {
        return AjaxResult.success(transactionCommoditiesService.selectTransactionCommoditiesById(commodityId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:commodities:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TransactionCommodities transactionCommodities) {
        return toAjax(transactionCommoditiesService.insertTransactionCommodities(transactionCommodities));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:commodities:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TransactionCommodities transactionCommodities) {
        return toAjax(transactionCommoditiesService.updateTransactionCommodities(transactionCommodities));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:commodities:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @DeleteMapping("/{commodityIds}")
    public AjaxResult remove(@PathVariable Long[] commodityIds) {
        return toAjax(transactionCommoditiesService.deleteTransactionCommoditiesByIds(commodityIds));
    }
}
