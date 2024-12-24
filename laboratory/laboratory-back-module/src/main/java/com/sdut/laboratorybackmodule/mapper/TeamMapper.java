package com.sdut.laboratorybackmodule.mapper;

import com.sdut.laboratorybackmodule.entity.Member;
import com.sdut.laboratorybackmodule.entity.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamMapper {
    void insertTeam(Team team);

    void updateTeam(Team team);

    void deleteTeamById(Long id);

    Team findTeamById(Long id);

    List<Team> findAllTeams();

    List<Member> findAllMembersByTeamId(@Param("teamId") Long teamId);

}
