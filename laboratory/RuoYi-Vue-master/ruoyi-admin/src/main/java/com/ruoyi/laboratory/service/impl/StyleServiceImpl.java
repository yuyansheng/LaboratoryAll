package com.ruoyi.laboratory.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.laboratory.mapper.StyleMapper;
import com.ruoyi.laboratory.domain.Style;
import com.ruoyi.laboratory.service.IStyleService;

/**
 * StyleService业务层处理
 * 
 * @author ruoyi
 * @date 2025-01-08
 */
@Service
public class StyleServiceImpl implements IStyleService 
{
    @Autowired
    private StyleMapper styleMapper;

    /**
     * 查询Style
     * 
     * @param id Style主键
     * @return Style
     */
    @Override
    public Style selectStyleById(String id)
    {
        return styleMapper.selectStyleById(id);
    }

    /**
     * 查询Style列表
     * 
     * @param style Style
     * @return Style
     */
    @Override
    public List<Style> selectStyleList(Style style)
    {
        return styleMapper.selectStyleList(style);
    }

    /**
     * 新增Style
     * 
     * @param style Style
     * @return 结果
     */
    @Override
    public int insertStyle(Style style)
    {
        return styleMapper.insertStyle(style);
    }

    /**
     * 修改Style
     * 
     * @param style Style
     * @return 结果
     */
    @Override
    public int updateStyle(Style style)
    {
        return styleMapper.updateStyle(style);
    }

    /**
     * 批量删除Style
     * 
     * @param ids 需要删除的Style主键
     * @return 结果
     */
    @Override
    public int deleteStyleByIds(String[] ids)
    {
        return styleMapper.deleteStyleByIds(ids);
    }

    /**
     * 删除Style信息
     * 
     * @param id Style主键
     * @return 结果
     */
    @Override
    public int deleteStyleById(String id)
    {
        return styleMapper.deleteStyleById(id);
    }
}
