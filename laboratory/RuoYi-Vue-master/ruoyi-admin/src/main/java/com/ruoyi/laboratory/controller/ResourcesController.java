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
import com.ruoyi.laboratory.domain.Resources;
import com.ruoyi.laboratory.service.IResourcesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 资源Controller
 *
 * @author 展示
 * @date 2025-01-04
 */
@RestController
@RequestMapping("/laboratory/resources")
public class ResourcesController extends BaseController
{
    @Autowired
    private IResourcesService resourcesService;

    /**
     * 查询资源列表
     */
    @Anonymous
    @GetMapping("/list")
    public TableDataInfo list(Resources resources)
    {
        startPage();
        List<Resources> list = resourcesService.selectResourcesList(resources);
        return getDataTable(list);
    }

    /**
     * 导出资源列表
     */
    @PreAuthorize("@ss.hasPermi('laboratory:resources:export')")
    @Log(title = "资源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Resources resources)
    {
        List<Resources> list = resourcesService.selectResourcesList(resources);
        ExcelUtil<Resources> util = new ExcelUtil<Resources>(Resources.class);
        util.exportExcel(response, list, "资源数据");
    }

    /**
     * 获取资源详细信息
     */
    @Anonymous
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(resourcesService.selectResourcesById(id));
    }

    /**
     * 新增资源
     */
    @PreAuthorize("@ss.hasPermi('laboratory:resources:add')")
    @Log(title = "资源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Resources resources)
    {
        return toAjax(resourcesService.insertResources(resources));
    }

    /**
     * 修改资源
     */
    @PreAuthorize("@ss.hasPermi('laboratory:resources:edit')")
    @Log(title = "资源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Resources resources)
    {
        return toAjax(resourcesService.updateResources(resources));
    }

    /**
     * 删除资源
     */
    @PreAuthorize("@ss.hasPermi('laboratory:resources:remove')")
    @Log(title = "资源", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(resourcesService.deleteResourcesByIds(ids));
    }
}
