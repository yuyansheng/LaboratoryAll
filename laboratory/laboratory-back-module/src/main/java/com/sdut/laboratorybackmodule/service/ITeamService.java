package com.sdut.laboratorybackmodule.service;

import com.sdut.laboratorybackmodule.entity.Member;
import com.sdut.laboratorybackmodule.entity.Team;

import java.util.List;

public interface ITeamService {

    public Long saveTeam(Team team);

    public void updateTeam(Team team);

    public void deleteTeamById(Long id);

    public Team findTeamById(Long id) ;

    List<Team> findAllTeams() ;

    List<Member> findAllMembersByTeamId(Long id);
}
