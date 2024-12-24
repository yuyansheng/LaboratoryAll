package com.sdut.laboratorybackmodule.service;

import com.sdut.laboratorybackmodule.entity.Member;
import com.sdut.laboratorybackmodule.mapper.MemberMapper;

import java.util.List;

public interface IMemberService {

    Member login(String email, String password);

    List<Member> getAllMembers();

    Member getMemberById(int id);

    void insertMember(Member member);

    void updateMember(Member member);

    void deleteMember(int id);
}
