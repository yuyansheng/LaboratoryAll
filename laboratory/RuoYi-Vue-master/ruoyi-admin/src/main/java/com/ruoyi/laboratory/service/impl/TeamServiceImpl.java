package com.ruoyi.laboratory.service.impl;

import java.util.List;

import com.ruoyi.laboratory.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.laboratory.mapper.TeamMapper;
import com.ruoyi.laboratory.domain.Team;
import com.ruoyi.laboratory.service.ITeamService;

/**
 * 团队Service业务层处理
 *
 * @author 张森
 * @date 2025-01-04
 */
@Service
public class TeamServiceImpl implements ITeamService
{
    @Autowired
    private TeamMapper teamMapper;

    /**
     * 查询团队
     *
     * @param id 团队主键
     * @return 团队
     */
    @Override
    public Team selectTeamById(String id)
    {
        return teamMapper.selectTeamById(id);
    }

    /**
     * 查询团队列表
     *
     * @param team 团队
     * @return 团队
     */
    @Override
    public List<Team> selectTeamList(Team team)
    {
        return teamMapper.selectTeamList(team);
    }

    /**
     * 新增团队
     *
     * @param team 团队
     * @return 结果
     */
    @Override
    public int insertTeam(Team team)
    {
        return teamMapper.insertTeam(team);
    }

    /**
     * 修改团队
     *
     * @param team 团队
     * @return 结果
     */
    @Override
    public int updateTeam(Team team)
    {
        return teamMapper.updateTeam(team);
    }

    /**
     * 批量删除团队
     *
     * @param ids 需要删除的团队主键
     * @return 结果
     */
    @Override
    public int deleteTeamByIds(String[] ids)
    {
        return teamMapper.deleteTeamByIds(ids);
    }

    /**
     * 删除团队信息
     *
     * @param id 团队主键
     * @return 结果
     */
    @Override
    public int deleteTeamById(String id)
    {
        return teamMapper.deleteTeamById(id);
    }


    /**
    获取团队成员
     @param id 团队主键
     @return 团队成员
     */
    @Override
    public List<Member> findAllMembersByTeamId(Long id) {
     return teamMapper.findAllMembersByTeamId(id);
    }
}
