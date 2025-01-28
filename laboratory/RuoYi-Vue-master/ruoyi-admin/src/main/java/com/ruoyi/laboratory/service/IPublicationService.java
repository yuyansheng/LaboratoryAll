package com.ruoyi.laboratory.service;

import java.util.List;
import com.ruoyi.laboratory.domain.Publication;

/**
 * 出版物Service接口
 *
 * @author 张森
 * @date 2025-01-04
 */
public interface IPublicationService
{
    /**
     * 查询出版物
     *
     * @param id 出版物主键
     * @return 出版物
     */
    public Publication selectPublicationById(String id);

    /**
     * 查询出版物列表
     *
     * @param publication 出版物
     * @return 出版物集合
     */
    public List<Publication> selectPublicationList(Publication publication);

    /**
     * 新增出版物
     *
     * @param publication 出版物
     * @return 结果
     */
    public int insertPublication(Publication publication);

    /**
     * 修改出版物
     *
     * @param publication 出版物
     * @return 结果
     */
    public int updatePublication(Publication publication);

    /**
     * 批量删除出版物
     *
     * @param ids 需要删除的出版物主键集合
     * @return 结果
     */
    public int deletePublicationByIds(String[] ids);

    /**
     * 删除出版物信息
     *
     * @param id 出版物主键
     * @return 结果
     */
    public int deletePublicationById(String id);
}
