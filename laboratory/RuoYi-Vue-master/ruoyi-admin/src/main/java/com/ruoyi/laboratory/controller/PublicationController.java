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
import com.ruoyi.laboratory.domain.Publication;
import com.ruoyi.laboratory.service.IPublicationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 出版物Controller
 *
 * @author 张森
 * @date 2025-01-04
 */
@RestController
@RequestMapping("/laboratory/publication")
public class PublicationController extends BaseController
{
    @Autowired
    private IPublicationService publicationService;

    /**
     * 查询出版物列表
     */
    @Anonymous
    @GetMapping("/list")
    public TableDataInfo list(Publication publication)
    {
        startPage();
        List<Publication> list = publicationService.selectPublicationList(publication);
        return getDataTable(list);
    }

    /**
     * 导出出版物列表
     */
    @PreAuthorize("@ss.hasPermi('laboratory:publication:export')")
    @Log(title = "出版物", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Publication publication)
    {
        List<Publication> list = publicationService.selectPublicationList(publication);
        ExcelUtil<Publication> util = new ExcelUtil<Publication>(Publication.class);
        util.exportExcel(response, list, "出版物数据");
    }

    /**
     * 获取出版物详细信息
     */
    @PreAuthorize("@ss.hasPermi('laboratory:publication:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(publicationService.selectPublicationById(id));
    }

    /**
     * 新增出版物
     */
    @PreAuthorize("@ss.hasPermi('laboratory:publication:add')")
    @Log(title = "出版物", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Publication publication)
    {
        return toAjax(publicationService.insertPublication(publication));
    }

    /**
     * 修改出版物
     */
    @PreAuthorize("@ss.hasPermi('laboratory:publication:edit')")
    @Log(title = "出版物", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Publication publication)
    {
        return toAjax(publicationService.updatePublication(publication));
    }

    /**
     * 删除出版物
     */
    @PreAuthorize("@ss.hasPermi('laboratory:publication:remove')")
    @Log(title = "出版物", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(publicationService.deletePublicationByIds(ids));
    }
}
