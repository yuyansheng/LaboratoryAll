package com.ruoyi.laboratory.service;

import java.util.List;
import com.ruoyi.laboratory.domain.TeamMember;

/**
 * 团队成员Service接口
 *
 * @author 张森
 * @date 2025-01-04
 */
public interface ITeamMemberService
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
     * 批量删除团队成员
     *
     * @param teamIds 需要删除的团队成员主键集合
     * @return 结果
     */
    public int deleteTeamMemberByTeamIds(String[] teamIds);

    /**
     * 删除团队成员信息
     *
     * @param teamId 团队成员主键
     * @return 结果
     */
    public int deleteTeamMemberByTeamId(String teamId);
}
