package com.sdut.laboratorybackmodule.service.impl;

import com.sdut.laboratorybackmodule.entity.Member;
import com.sdut.laboratorybackmodule.entity.Team;
import com.sdut.laboratorybackmodule.mapper.TeamMapper;
import com.sdut.laboratorybackmodule.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements ITeamService {

    @Autowired
    private TeamMapper teamMapper;

    public Long saveTeam(Team team) {
        teamMapper.insertTeam(team);
        return team.getId();
    }

    public void updateTeam(Team team) {
        teamMapper.updateTeam(team);
    }

    public void deleteTeamById(Long id) {
        teamMapper.deleteTeamById(id);
    }

    public Team findTeamById(Long id) {
        return teamMapper.findTeamById(id);
    }

    public List<Team> findAllTeams() {
        return teamMapper.findAllTeams();
    }

    @Override
    public List<Member> findAllMembersByTeamId(Long id) {
        return teamMapper.findAllMembersByTeamId(id);
    }
}
