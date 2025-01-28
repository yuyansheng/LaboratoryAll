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
import com.ruoyi.laboratory.domain.Research;
import com.ruoyi.laboratory.service.IResearchService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 研究Controller
 *
 * @author 张森
 * @date 2025-01-04
 */
@RestController
@RequestMapping("/laboratory/research")
public class ResearchController extends BaseController
{
    @Autowired
    private IResearchService researchService;

    /**
     * 查询研究列表
     */
    @Anonymous
    @GetMapping("/list")
    public TableDataInfo list(Research research)
    {
        startPage();
        List<Research> list = researchService.selectResearchList(research);
        return getDataTable(list);
    }

    /**
     * 导出研究列表
     */
    @PreAuthorize("@ss.hasPermi('laboratory:research:export')")
    @Log(title = "研究", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Research research)
    {
        List<Research> list = researchService.selectResearchList(research);
        ExcelUtil<Research> util = new ExcelUtil<Research>(Research.class);
        util.exportExcel(response, list, "研究数据");
    }

    /**
     * 获取研究详细信息
     */
    @PreAuthorize("@ss.hasPermi('laboratory:research:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(researchService.selectResearchById(id));
    }

    /**
     * 新增研究
     */
    @PreAuthorize("@ss.hasPermi('laboratory:research:add')")
    @Log(title = "研究", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Research research)
    {
        return toAjax(researchService.insertResearch(research));
    }

    /**
     * 修改研究
     */
    @PreAuthorize("@ss.hasPermi('laboratory:research:edit')")
    @Log(title = "研究", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Research research)
    {
        return toAjax(researchService.updateResearch(research));
    }

    /**
     * 删除研究
     */
    @PreAuthorize("@ss.hasPermi('laboratory:research:remove')")
    @Log(title = "研究", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(researchService.deleteResearchByIds(ids));
    }
}
