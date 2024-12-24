package com.sdut.laboratorybackmodule.service;

import com.sdut.laboratorybackmodule.entity.Publication;

import java.util.List;

public interface IPublicationService {
    public void insertPublication(Publication publication) ;
    // 根据ID获取出版物
    public Publication getPublicationById(Long id) ;

    // 获取所有出版物
    public List<Publication> getAllPublications() ;

    // 更新出版物
    public void updatePublication(Publication publication);

    // 删除出版物
    public void deletePublication(Long id) ;
}
