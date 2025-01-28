package com.ruoyi.laboratory.mapper;

import java.util.List;

import com.ruoyi.laboratory.domain.Member;
import com.ruoyi.laboratory.domain.Team;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * 团队Mapper接口
 *
 * @author 张森
 * @date 2025-01-04
 */
public interface TeamMapper
{
    /**
     * 查询团队
     *
     * @param id 团队主键
     * @return 团队
     */
    public Team selectTeamById(String id);

    /**
     * 查询团队列表
     *
     * @param team 团队
     * @return 团队集合
     */
    public List<Team> selectTeamList(Team team);

    /**
     * 新增团队
     *
     * @param team 团队
     * @return 结果
     */
    public int insertTeam(Team team);

    /**
     * 修改团队
     *
     * @param team 团队
     * @return 结果
     */
    public int updateTeam(Team team);

    /**
     * 删除团队
     *
     * @param id 团队主键
     * @return 结果
     */
    @Transactional
    public int deleteTeamById(String id);

    /**
     * 批量删除团队
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeamByIds(String[] ids);
    /**
     * 批量删除团队成员
     *
     * @param teamId 查找的teamid
     * @return 团队成员
     */
    public List<Member> findAllMembersByTeamId(@Param("teamId") Long teamId);

}
