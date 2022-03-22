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
import com.hq.system.domain.TransactionCommodity;
import com.hq.system.service.ITransactionCommodityService;
import com.hq.common.utils.poi.ExcelUtil;
import com.hq.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author wxy
 * @date 2021-10-28
 */
@RestController
@RequestMapping("/system/commodity")
public class TransactionCommodityController extends BaseController {
    @Autowired
    private ITransactionCommodityService transactionCommodityService;

/**
 * 查询【请填写功能名称】列表
 */
@PreAuthorize("@ss.hasPermi('system:commodity:list')")
@GetMapping("/list")
        public TableDataInfo list(TransactionCommodity transactionCommodity) {
        startPage();
        List<TransactionCommodity> list = transactionCommodityService.selectTransactionCommodityList(transactionCommodity);
        return getDataTable(list);
    }
    
    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:commodity:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TransactionCommodity transactionCommodity) {
        List<TransactionCommodity> list = transactionCommodityService.selectTransactionCommodityList(transactionCommodity);
        ExcelUtil<TransactionCommodity> util = new ExcelUtil<TransactionCommodity>(TransactionCommodity. class);
        return util.exportExcel(list, "commodity");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:commodity:query')")
    @GetMapping(value = "/{commodityId}")
    public AjaxResult getInfo(@PathVariable("commodityId") Long commodityId) {
        return AjaxResult.success(transactionCommodityService.selectTransactionCommodityById(commodityId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:commodity:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TransactionCommodity transactionCommodity) {
        return toAjax(transactionCommodityService.insertTransactionCommodity(transactionCommodity));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:commodity:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TransactionCommodity transactionCommodity) {
        return toAjax(transactionCommodityService.updateTransactionCommodity(transactionCommodity));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:commodity:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @DeleteMapping("/{commodityIds}")
    public AjaxResult remove(@PathVariable Long[] commodityIds) {
        return toAjax(transactionCommodityService.deleteTransactionCommodityByIds(commodityIds));
    }
}
