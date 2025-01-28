package com.ruoyi.laboratory.service;

import java.util.List;

import com.ruoyi.laboratory.domain.Member;
import com.ruoyi.laboratory.domain.Team;
import org.apache.ibatis.annotations.Param;

/**
 * 团队Service接口
 *
 * @author 张森
 * @date 2025-01-04
 */
public interface ITeamService
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
     * 批量删除团队
     *
     * @param ids 需要删除的团队主键集合
     * @return 结果
     */
    public int deleteTeamByIds(String[] ids);

    /**
     * 删除团队信息
     *
     * @param id 团队主键
     * @return 结果
     */
    public int deleteTeamById(String id);

  /**获取团队成员信息
   *
   * @param teamId 团队主键
   * @return 团队成员
   */

  public List<Member> findAllMembersByTeamId(@Param("teamId") Long teamId);
}
