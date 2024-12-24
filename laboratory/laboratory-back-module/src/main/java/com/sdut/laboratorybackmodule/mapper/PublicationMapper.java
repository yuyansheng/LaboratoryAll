package com.sdut.laboratorybackmodule.mapper;

import com.sdut.laboratorybackmodule.entity.Publication;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PublicationMapper {

    // 插入出版物
    void insertPublication(Publication publication);

    // 根据ID查询出版物
    Publication getPublicationById(Long id);

    // 查询所有出版物
    List<Publication> getAllPublications();

    // 更新出版物
    void updatePublication(Publication publication);

    // 删除出版物
    void deletePublication(Long id);
}