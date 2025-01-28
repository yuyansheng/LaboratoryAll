package com.ruoyi.laboratory.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.laboratory.mapper.PublicationMapper;
import com.ruoyi.laboratory.domain.Publication;
import com.ruoyi.laboratory.service.IPublicationService;

/**
 * 出版物Service业务层处理
 *
 * @author 张森
 * @date 2025-01-04
 */
@Service
public class PublicationServiceImpl implements IPublicationService
{
    @Autowired
    private PublicationMapper publicationMapper;

    /**
     * 查询出版物
     *
     * @param id 出版物主键
     * @return 出版物
     */
    @Override
    public Publication selectPublicationById(String id)
    {
        return publicationMapper.selectPublicationById(id);
    }

    /**
     * 查询出版物列表
     *
     * @param publication 出版物
     * @return 出版物
     */
    @Override
    public List<Publication> selectPublicationList(Publication publication)
    {
        return publicationMapper.selectPublicationList(publication);
    }

    /**
     * 新增出版物
     *
     * @param publication 出版物
     * @return 结果
     */
    @Override
    public int insertPublication(Publication publication)
    {
        return publicationMapper.insertPublication(publication);
    }

    /**
     * 修改出版物
     *
     * @param publication 出版物
     * @return 结果
     */
    @Override
    public int updatePublication(Publication publication)
    {
        return publicationMapper.updatePublication(publication);
    }

    /**
     * 批量删除出版物
     *
     * @param ids 需要删除的出版物主键
     * @return 结果
     */
    @Override
    public int deletePublicationByIds(String[] ids)
    {
        return publicationMapper.deletePublicationByIds(ids);
    }

    /**
     * 删除出版物信息
     *
     * @param id 出版物主键
     * @return 结果
     */
    @Override
    public int deletePublicationById(String id)
    {
        return publicationMapper.deletePublicationById(id);
    }
}
