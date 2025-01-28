package com.ruoyi.laboratory.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.laboratory.domain.Style;
import com.ruoyi.laboratory.service.IStyleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * StyleController
 *
 * @author ruoyi
 * @date 2025-01-08
 */
@RestController
@RequestMapping("/laboratory/style")
public class StyleController extends BaseController
{
    @Autowired
    private IStyleService styleService;

    /**
     * 查询Style列表
     */
    @Anonymous
    @GetMapping("/list")
    public TableDataInfo list(Style style)
    {
        startPage();
        startOrderBy();
        List<Style> list = styleService.selectStyleList(style);
        return getDataTable(list);
    }

    /**
     * 导出Style列表
     */
    @PreAuthorize("@ss.hasPermi('laboratory:style:export')")
    @Log(title = "Style", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Style style)
    {
        List<Style> list = styleService.selectStyleList(style);
        ExcelUtil<Style> util = new ExcelUtil<Style>(Style.class);
        util.exportExcel(response, list, "Style数据");
    }

    /**
     * 获取Style详细信息
     */
    @PreAuthorize("@ss.hasPermi('laboratory:style:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(styleService.selectStyleById(id));
    }

    /**
     * 新增Style
     */
    @PreAuthorize("@ss.hasPermi('laboratory:style:add')")
    @Log(title = "Style", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Style style)
    {
        return toAjax(styleService.insertStyle(style));
    }

    /**
     * 修改Style
     */
    @PreAuthorize("@ss.hasPermi('laboratory:style:edit')")
    @Log(title = "Style", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Style style)
    {
        return toAjax(styleService.updateStyle(style));
    }

    /**
     * 删除Style
     */
    @PreAuthorize("@ss.hasPermi('laboratory:style:remove')")
    @Log(title = "Style", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(styleService.deleteStyleByIds(ids));
    }
}
