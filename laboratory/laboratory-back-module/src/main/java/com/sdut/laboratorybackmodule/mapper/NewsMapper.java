package com.sdut.laboratorybackmodule.mapper;

import com.sdut.laboratorybackmodule.entity.News;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NewsMapper {
    News getNewsById(@Param("id") int id);

    List<News> getAllNews();

    List<News> getNewsByAuthorId(@Param("authorId") int authorId);

    void insertNews(News news);

    void updateNews(News news);

    void deleteNews(@Param("id") int id);

    @Select("SELECT COUNT(*) FROM news")
    Integer getNewsCount();

    List<News> getPagedNews(@Param("pageSize") int pageSize, @Param("offset") int offset);
}
