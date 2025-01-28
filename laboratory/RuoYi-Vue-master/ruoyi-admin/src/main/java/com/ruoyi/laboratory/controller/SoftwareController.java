package com.ruoyi.laboratory.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.laboratory.domain.News;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.laboratory.domain.Software;
import com.ruoyi.laboratory.service.ISoftwareService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 软件Controller
 *
 * @author 张森
 * @date 2025-01-04
 */
@RestController
@RequestMapping("/laboratory/software")
public class SoftwareController extends BaseController
{
    @Autowired
    private ISoftwareService softwareService;

    /**
     * 查询软件列表
     */
    @Anonymous
    @GetMapping("/list")
    public TableDataInfo list(Software software)
    {
        startPage();
        List<Software> list = softwareService.selectSoftwareList(software);
        return getDataTable(list);
    }

  @Anonymous
  @PutMapping("/update/downloadTimes")
  public void updateDownloadTimes(@RequestParam("downloadTimes") String downloadTimes, @RequestParam("id") String id) {
    Software software = softwareService.selectSoftwareById(id);
    software.setDownloadTimes(downloadTimes);
    softwareService.updateSoftware(software);
  }

    /**
     * 导出软件列表
     */
    @PreAuthorize("@ss.hasPermi('laboratory:software:export')")
    @Log(title = "软件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Software software)
    {
        List<Software> list = softwareService.selectSoftwareList(software);
        ExcelUtil<Software> util = new ExcelUtil<Software>(Software.class);
        util.exportExcel(response, list, "软件数据");
    }

    /**
     * 获取软件详细信息
     */
    @PreAuthorize("@ss.hasPermi('laboratory:software:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(softwareService.selectSoftwareById(id));
    }

    /**
     * 新增软件
     */
    @PreAuthorize("@ss.hasPermi('laboratory:software:add')")
    @Log(title = "软件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Software software)
    {
        return toAjax(softwareService.insertSoftware(software));
    }

    /**
     * 修改软件
     */
    @PreAuthorize("@ss.hasPermi('laboratory:software:edit')")
    @Log(title = "软件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Software software)
    {
        return toAjax(softwareService.updateSoftware(software));
    }

    /**
     * 删除软件
     */
    @PreAuthorize("@ss.hasPermi('laboratory:software:remove')")
    @Log(title = "软件", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(softwareService.deleteSoftwareByIds(ids));
    }
}
