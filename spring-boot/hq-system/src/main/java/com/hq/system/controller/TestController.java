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
import com.hq.system.domain.Test;
import com.hq.system.service.ITestService;
import com.hq.common.utils.poi.ExcelUtil;
import com.hq.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author wxy
 * @date 2021-10-28
 */
@RestController
@RequestMapping("/system/test")
public class TestController extends BaseController {
    @Autowired
    private ITestService testService;

/**
 * 查询【请填写功能名称】列表
 */
@PreAuthorize("@ss.hasPermi('system:test:list')")
@GetMapping("/list")
        public TableDataInfo list(Test test) {
        startPage();
        List<Test> list = testService.selectTestList(test);
        return getDataTable(list);
    }
    
    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:test:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Test test) {
        List<Test> list = testService.selectTestList(test);
        ExcelUtil<Test> util = new ExcelUtil<Test>(Test. class);
        return util.exportExcel(list, "test");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:test:query')")
    @GetMapping(value = "/{test}")
    public AjaxResult getInfo(@PathVariable("test") Long test) {
        return AjaxResult.success(testService.selectTestById(test));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:test:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Test test) {
        return toAjax(testService.insertTest(test));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:test:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Test test) {
        return toAjax(testService.updateTest(test));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:test:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tests}")
    public AjaxResult remove(@PathVariable Long[] tests) {
        return toAjax(testService.deleteTestByIds(tests));
    }
}
