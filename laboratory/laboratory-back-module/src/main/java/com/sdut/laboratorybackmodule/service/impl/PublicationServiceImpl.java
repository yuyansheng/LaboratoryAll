package com.sdut.laboratorybackmodule.service.impl;

import com.sdut.laboratorybackmodule.entity.Publication;
import com.sdut.laboratorybackmodule.mapper.PublicationMapper;
import com.sdut.laboratorybackmodule.service.IPublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationServiceImpl implements IPublicationService {
    @Autowired
    PublicationMapper publicationMapper;

    // 插入出版物
    @Override
    public void insertPublication(Publication publication) {
        publicationMapper.insertPublication(publication);
    }

    // 根据ID获取出版物
    @Override
    public Publication getPublicationById(Long id) {
        return publicationMapper.getPublicationById(id);
    }

    // 获取所有出版物
    @Override
    public List<Publication> getAllPublications() {
        return publicationMapper.getAllPublications();
    }

    // 更新出版物
    @Override
    public void updatePublication(Publication publication) {
        publicationMapper.updatePublication(publication);
    }

    // 删除出版物
    @Override
    public void deletePublication(Long id) {
        publicationMapper.deletePublication(id);
    }
}