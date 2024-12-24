package com.sdut.laboratorybackmodule.service.impl;

import com.sdut.laboratorybackmodule.entity.Member;
import com.sdut.laboratorybackmodule.mapper.MemberMapper;
import com.sdut.laboratorybackmodule.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements IMemberService {
    @Autowired
    MemberMapper memberMapper;

    @Override
    public Member login(String email, String password) {
        Member member = memberMapper.findByEmailAndPassword(email, password);
        if (member == null) {

            return null;
        }
        return member;
    }

    @Override
    public List<Member> getAllMembers() {
        return memberMapper.getAllMembers();
    }

    @Override
    public Member getMemberById(int id) {
        return memberMapper.getMemberById(id);
    }

    @Override
    public void insertMember(Member member) {
        memberMapper.insertMember(member);
    }

    @Override
    public void updateMember(Member member) {
        memberMapper.updateMember(member);
    }

    @Override
    public void deleteMember(int id) {
        memberMapper.deleteMember(id);
    }

}
