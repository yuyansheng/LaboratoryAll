package com.sdut.laboratorybackmodule.mapper;

import com.sdut.laboratorybackmodule.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    List<Member> selectAllMembers();

    Member findByEmailAndPassword(String email, String password);

    Member findById(int id);

    List<Member> getAllMembers();

    List<Member> findMembersByName(String name);

    Member getMemberById(int id);

    void insertMember(Member member);

    void updateMember(Member member);

    void deleteMember(int id);
}
