package com.ruoyi.laboratory.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.laboratory.domain.Member;
import org.apache.ibatis.annotations.Update;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.laboratory.domain.News;
import com.ruoyi.laboratory.service.INewsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 新闻Controller
 *
 * @author 张森
 * @date 2025-01-04
 */
@RestController
@RequestMapping("/laboratory/news")
public class NewsController extends BaseController
{
    @Autowired
    private INewsService newsService;

    /**
     * 查询新闻列表
     */
//    @PreAuthorize("@ss.hasPermi('laboratory:news:list')")
    @Anonymous
    @GetMapping("/list")
    public TableDataInfo list(News news)
    {
        startPage();
        startOrderBy();
        List<News> list = newsService.selectNewsList(news);
        return getDataTable(list);
    }

    @Anonymous
    @PutMapping("/update/views")
    public void updateViews(@RequestParam("views") String views,@RequestParam("newsId") String newsId) {
      News news = newsService.selectNewsById(newsId);
      news.setReadTimes( views);
      newsService.updateNews(news);
    }
    /**
     * 导出新闻列表
     */
    @PreAuthorize("@ss.hasPermi('laboratory:news:export')")
    @Log(title = "新闻", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, News news)
    {
        List<News> list = newsService.selectNewsList(news);
        ExcelUtil<News> util = new ExcelUtil<News>(News.class);
        util.exportExcel(response, list, "新闻数据");
    }

    /**
     * 获取新闻详细信息
     */
    @Anonymous
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(newsService.selectNewsById(id));
    }

    /**
     * 新增新闻
     */
    @PreAuthorize("@ss.hasPermi('laboratory:news:add')")
    @Log(title = "新闻", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody News news)
    {
        return toAjax(newsService.insertNews(news));
    }

    /**
     * 修改新闻
     */
    @PreAuthorize("@ss.hasPermi('laboratory:news:edit')")
    @Log(title = "新闻", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody News news)
    {
        return toAjax(newsService.updateNews(news));
    }

    /**
     * 删除新闻
     */
    @PreAuthorize("@ss.hasPermi('laboratory:news:remove')")
    @Log(title = "新闻", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(newsService.deleteNewsByIds(ids));
    }
}
