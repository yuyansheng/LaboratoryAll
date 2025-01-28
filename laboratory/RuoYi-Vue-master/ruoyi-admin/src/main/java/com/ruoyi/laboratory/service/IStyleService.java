package com.ruoyi.laboratory.service;

import java.util.List;
import com.ruoyi.laboratory.domain.Style;

/**
 * StyleService接口
 * 
 * @author ruoyi
 * @date 2025-01-08
 */
public interface IStyleService 
{
    /**
     * 查询Style
     * 
     * @param id Style主键
     * @return Style
     */
    public Style selectStyleById(String id);

    /**
     * 查询Style列表
     * 
     * @param style Style
     * @return Style集合
     */
    public List<Style> selectStyleList(Style style);

    /**
     * 新增Style
     * 
     * @param style Style
     * @return 结果
     */
    public int insertStyle(Style style);

    /**
     * 修改Style
     * 
     * @param style Style
     * @return 结果
     */
    public int updateStyle(Style style);

    /**
     * 批量删除Style
     * 
     * @param ids 需要删除的Style主键集合
     * @return 结果
     */
    public int deleteStyleByIds(String[] ids);

    /**
     * 删除Style信息
     * 
     * @param id Style主键
     * @return 结果
     */
    public int deleteStyleById(String id);
}
