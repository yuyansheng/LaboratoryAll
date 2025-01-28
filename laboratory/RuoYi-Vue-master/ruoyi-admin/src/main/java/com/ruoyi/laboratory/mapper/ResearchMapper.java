package com.ruoyi.laboratory.mapper;

import java.util.List;
import com.ruoyi.laboratory.domain.Research;

/**
 * 研究Mapper接口
 *
 * @author 张森
 * @date 2025-01-04
 */
public interface ResearchMapper
{
    /**
     * 查询研究
     *
     * @param id 研究主键
     * @return 研究
     */
    public Research selectResearchById(String id);

    /**
     * 查询研究列表
     *
     * @param research 研究
     * @return 研究集合
     */
    public List<Research> selectResearchList(Research research);

    /**
     * 新增研究
     *
     * @param research 研究
     * @return 结果
     */
    public int insertResearch(Research research);

    /**
     * 修改研究
     *
     * @param research 研究
     * @return 结果
     */
    public int updateResearch(Research research);

    /**
     * 删除研究
     *
     * @param id 研究主键
     * @return 结果
     */
    public int deleteResearchById(String id);

    /**
     * 批量删除研究
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteResearchByIds(String[] ids);
}
