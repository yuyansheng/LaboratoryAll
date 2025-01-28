package com.ruoyi.laboratory.mapper;

import java.util.List;

import com.ruoyi.laboratory.domain.Member;
import com.ruoyi.laboratory.domain.TeamMember;
import org.apache.ibatis.annotations.Param;

/**
 * 团队成员Mapper接口
 *
 * @author 张森
 * @date 2025-01-04
 */
public interface TeamMemberMapper
{
    /**
     * 查询团队成员
     *
     * @param teamId 团队成员主键
     * @return 团队成员
     */
    public TeamMember selectTeamMemberByTeamId(String teamId);

    /**
     * 查询团队成员列表
     *
     * @param teamMember 团队成员
     * @return 团队成员集合
     */
    public List<TeamMember> selectTeamMemberList(TeamMember teamMember);

    /**
     * 新增团队成员
     *
     * @param teamMember 团队成员
     * @return 结果
     */
    public int insertTeamMember(TeamMember teamMember);

    /**
     * 修改团队成员
     *
     * @param teamMember 团队成员
     * @return 结果
     */
    public int updateTeamMember(TeamMember teamMember);

    /**
     * 删除团队成员
     *
     * @param teamId 团队成员主键
     * @return 结果
     */
    public int deleteTeamMemberByTeamId(String teamId);

    /**
     * 批量删除团队成员
     *
     * @param teamIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeamMemberByTeamIds(String[] teamIds);




}
