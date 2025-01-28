package com.ruoyi.laboratory.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.laboratory.mapper.TeamMemberMapper;
import com.ruoyi.laboratory.domain.TeamMember;
import com.ruoyi.laboratory.service.ITeamMemberService;

/**
 * 团队成员Service业务层处理
 *
 * @author 张森
 * @date 2025-01-04
 */
@Service
public class TeamMemberServiceImpl implements ITeamMemberService
{
    @Autowired
    private TeamMemberMapper teamMemberMapper;

    /**
     * 查询团队成员
     *
     * @param teamId 团队成员主键
     * @return 团队成员
     */
    @Override
    public TeamMember selectTeamMemberByTeamId(String teamId)
    {
        return teamMemberMapper.selectTeamMemberByTeamId(teamId);
    }

    /**
     * 查询团队成员列表
     *
     * @param teamMember 团队成员
     * @return 团队成员
     */
    @Override
    public List<TeamMember> selectTeamMemberList(TeamMember teamMember)
    {
        return teamMemberMapper.selectTeamMemberList(teamMember);
    }

    /**
     * 新增团队成员
     *
     * @param teamMember 团队成员
     * @return 结果
     */
    @Override
    public int insertTeamMember(TeamMember teamMember)
    {
        return teamMemberMapper.insertTeamMember(teamMember);
    }

    /**
     * 修改团队成员
     *
     * @param teamMember 团队成员
     * @return 结果
     */
    @Override
    public int updateTeamMember(TeamMember teamMember)
    {
        return teamMemberMapper.updateTeamMember(teamMember);
    }

    /**
     * 批量删除团队成员
     *
     * @param teamIds 需要删除的团队成员主键
     * @return 结果
     */
    @Override
    public int deleteTeamMemberByTeamIds(String[] teamIds)
    {
        return teamMemberMapper.deleteTeamMemberByTeamIds(teamIds);
    }

    /**
     * 删除团队成员信息
     *
     * @param teamId 团队成员主键
     * @return 结果
     */
    @Override
    public int deleteTeamMemberByTeamId(String teamId)
    {
        return teamMemberMapper.deleteTeamMemberByTeamId(teamId);
    }
}
