package com.sdut.laboratorybackmodule.service;

import com.sdut.laboratorybackmodule.entity.News;

import java.util.List;

public interface INewsService {

    public News getNewsById(int id);

    public List<News> getAllNews();

    public List<News> getNewsByAuthorId(int authorId);

    public void addNews(News news);

    public void updateNews(News news);

    public void deleteNews(int id);

    Integer getNewsCount();

    public List<News> getPagedNews(int pageSize, int pageNum);
}