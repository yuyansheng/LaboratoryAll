package com.ruoyi.laboratory.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.laboratory.mapper.NewsMapper;
import com.ruoyi.laboratory.domain.News;
import com.ruoyi.laboratory.service.INewsService;

/**
 * 新闻Service业务层处理
 *
 * @author 张森
 * @date 2025-01-04
 */
@Service
public class NewsServiceImpl implements INewsService
{
    @Autowired
    private NewsMapper newsMapper;

    /**
     * 查询新闻
     *
     * @param id 新闻主键
     * @return 新闻
     */
    @Override
    public News selectNewsById(String id)
    {
        return newsMapper.selectNewsById(id);
    }

    /**
     * 查询新闻列表
     *
     * @param news 新闻
     * @return 新闻
     */
    @Override
    public List<News> selectNewsList(News news)
    {
        return newsMapper.selectNewsList(news);
    }

    /**
     * 新增新闻
     *
     * @param news 新闻
     * @return 结果
     */
    @Override
    public int insertNews(News news)
    {
        return newsMapper.insertNews(news);
    }

    /**
     * 修改新闻
     *
     * @param news 新闻
     * @return 结果
     */
    @Override
    public int updateNews(News news)
    {
        news.setUpdateTime(DateUtils.getNowDate());
        return newsMapper.updateNews(news);
    }

    /**
     * 批量删除新闻
     *
     * @param ids 需要删除的新闻主键
     * @return 结果
     */
    @Override
    public int deleteNewsByIds(String[] ids)
    {
        return newsMapper.deleteNewsByIds(ids);
    }

    /**
     * 删除新闻信息
     *
     * @param id 新闻主键
     * @return 结果
     */
    @Override
    public int deleteNewsById(String id)
    {
        return newsMapper.deleteNewsById(id);
    }
}
