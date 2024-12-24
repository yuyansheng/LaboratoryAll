package com.sdut.laboratorybackmodule.service;

import com.sdut.laboratorybackmodule.entity.TeamMember;

import java.util.List;

public interface ITeamMemberService {

    public void addTeamMember(TeamMember teamMember);

    public void deleteTeamMember(TeamMember teamMember);

    public List<Long> findMembersByTeamId(Long teamId);

    public List<Long> findTeamsByMemberId(Long memberId) ;
}
