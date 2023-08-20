package com.hq.system.controller;

import com.hq.common.annotation.Log;
import com.hq.common.core.controller.BaseController;
import com.hq.common.core.domain.AjaxResult;
import com.hq.common.core.page.TableDataInfo;
import com.hq.common.enums.BusinessType;
import com.hq.common.utils.poi.ExcelUtil;
import com.hq.system.domain.TransactionOrder;
import com.hq.system.service.ITransactionOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 【请填写功能名称】Controller
 *
 * @author mall
 * @date 2022-12-27
 */
@RestController
@RequestMapping("/system/order")
public class TransactionOrderController extends BaseController {
    @Autowired
    private ITransactionOrderService transactionOrderService;

/**
 * 查询【请填写功能名称】列表
 */
@PreAuthorize("@ss.hasPermi('system:order:list')")
@GetMapping("/list")
        public TableDataInfo list(TransactionOrder transactionOrder) {
        startPage();
        List<TransactionOrder> list = transactionOrderService.selectTransactionOrderList(transactionOrder);
        return getDataTable(list);
    }
    
    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:order:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TransactionOrder transactionOrder) {
        List<TransactionOrder> list = transactionOrderService.selectTransactionOrderList(transactionOrder);
        ExcelUtil<TransactionOrder> util = new ExcelUtil<TransactionOrder>(TransactionOrder. class);
        return util.exportExcel(list, "order");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    //@PreAuthorize("@ss.hasPermi('system:order:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") Long orderId) {
        return AjaxResult.success(transactionOrderService.selectTransactionOrderById(orderId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:order:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TransactionOrder transactionOrder) {
        return toAjax(transactionOrderService.insertTransactionOrder(transactionOrder));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:order:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TransactionOrder transactionOrder) {
        return toAjax(transactionOrderService.updateTransactionOrder(transactionOrder));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:order:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds) {
        return toAjax(transactionOrderService.deleteTransactionOrderByIds(orderIds));
    }

    /**
     * 退货
     */
    //@PreAuthorize("@ss.hasPermi('system:order:remove')")
    @Log(title = "退货", businessType = BusinessType.RETURNGOODS)
    @PutMapping("/returngoods")
    public AjaxResult returnGoods(@RequestBody TransactionOrder transactionOrder) throws Exception {
        return toAjax(transactionOrderService.returnGoodsTransactionOrderById(transactionOrder));
    }
}
