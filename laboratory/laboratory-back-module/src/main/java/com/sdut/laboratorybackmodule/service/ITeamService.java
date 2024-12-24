package com.sdut.laboratorybackmodule.service;

import com.sdut.laboratorybackmodule.entity.Team;

import java.util.List;

public interface ITeamService {

    public void saveTeam(Team team);

    public void updateTeam(Team team);

    public void deleteTeamById(Long id);

    public Team findTeamById(Long id) ;

    public List<Team> findAllTeams() ;
}
