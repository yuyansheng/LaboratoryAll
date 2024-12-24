package com.sdut.laboratorybackmodule.service.impl;

import com.sdut.laboratorybackmodule.entity.News;
import com.sdut.laboratorybackmodule.mapper.NewsMapper;
import com.sdut.laboratorybackmodule.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl  implements INewsService {
    private NewsMapper newsMapper;

    @Autowired
    public void NewsService(NewsMapper newsMapper) {
        this.newsMapper = newsMapper;
    }
    @Override
    public News getNewsById(int id) {
        return newsMapper.getNewsById(id);
    }
    @Override
    public List<News> getAllNews() {
        return newsMapper.getAllNews();
    }
    @Override
    public List<News> getNewsByAuthorId(int authorId) {
        return newsMapper.getNewsByAuthorId(authorId);
    }
    @Override
    public void addNews(News news) {
        newsMapper.insertNews(news);
    }
    @Override
    public void updateNews(News news) {
        newsMapper.updateNews(news);
    }
    @Override
    public void deleteNews(int id) {
        newsMapper.deleteNews(id);
    }

    @Override
    public Integer getNewsCount() {
        return newsMapper.getNewsCount();
    }

    @Override
    public List<News> getPagedNews(int pageSize, int pageNum) {
        int offset = (pageNum - 1) * pageSize;
        return newsMapper.getPagedNews(pageSize, offset);
    }
}
