package com.ruoyi.laboratory.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.laboratory.mapper.ResearchMapper;
import com.ruoyi.laboratory.domain.Research;
import com.ruoyi.laboratory.service.IResearchService;

/**
 * 研究Service业务层处理
 *
 * @author 张森
 * @date 2025-01-04
 */
@Service
public class ResearchServiceImpl implements IResearchService
{
    @Autowired
    private ResearchMapper researchMapper;

    /**
     * 查询研究
     *
     * @param id 研究主键
     * @return 研究
     */
    @Override
    public Research selectResearchById(String id)
    {
        return researchMapper.selectResearchById(id);
    }

    /**
     * 查询研究列表
     *
     * @param research 研究
     * @return 研究
     */
    @Override
    public List<Research> selectResearchList(Research research)
    {
        return researchMapper.selectResearchList(research);
    }

    /**
     * 新增研究
     *
     * @param research 研究
     * @return 结果
     */
    @Override
    public int insertResearch(Research research)
    {
        return researchMapper.insertResearch(research);
    }

    /**
     * 修改研究
     *
     * @param research 研究
     * @return 结果
     */
    @Override
    public int updateResearch(Research research)
    {
        return researchMapper.updateResearch(research);
    }

    /**
     * 批量删除研究
     *
     * @param ids 需要删除的研究主键
     * @return 结果
     */
    @Override
    public int deleteResearchByIds(String[] ids)
    {
        return researchMapper.deleteResearchByIds(ids);
    }

    /**
     * 删除研究信息
     *
     * @param id 研究主键
     * @return 结果
     */
    @Override
    public int deleteResearchById(String id)
    {
        return researchMapper.deleteResearchById(id);
    }
}
