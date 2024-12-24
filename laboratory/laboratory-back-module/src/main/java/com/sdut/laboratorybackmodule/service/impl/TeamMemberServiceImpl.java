package com.sdut.laboratorybackmodule.service.impl;

import com.sdut.laboratorybackmodule.entity.TeamMember;
import com.sdut.laboratorybackmodule.mapper.TeamMemberMapper;
import com.sdut.laboratorybackmodule.service.ITeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamMemberServiceImpl implements ITeamMemberService {

    @Autowired
    private TeamMemberMapper teamMemberMapper;

    public void addTeamMember(TeamMember teamMember) {
        teamMemberMapper.addTeamMember(teamMember);
    }

    public void deleteTeamMember(TeamMember teamMember) {
        teamMemberMapper.deleteTeamMember(teamMember);
    }

    public List<Long> findMembersByTeamId(Long teamId) {
        return teamMemberMapper.findMembersByTeamId(teamId);
    }

    public List<Long> findTeamsByMemberId(Long memberId) {
        return teamMemberMapper.findTeamsByMemberId(memberId);
    }
}
