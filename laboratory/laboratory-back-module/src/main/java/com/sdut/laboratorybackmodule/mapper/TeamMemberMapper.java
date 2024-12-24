package com.sdut.laboratorybackmodule.mapper;

import com.sdut.laboratorybackmodule.entity.Member;
import com.sdut.laboratorybackmodule.entity.TeamMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamMemberMapper {
    void addTeamMember(TeamMember teamMember);

    void deleteTeamMember(TeamMember teamMember);

    List<Long> findMembersByTeamId(Long teamId);

    List<Long> findTeamsByMemberId(Long memberId);
}
