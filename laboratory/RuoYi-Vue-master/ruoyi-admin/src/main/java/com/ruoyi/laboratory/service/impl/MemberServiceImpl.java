package com.ruoyi.laboratory.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.laboratory.mapper.MemberMapper;
import com.ruoyi.laboratory.domain.Member;
import com.ruoyi.laboratory.service.IMemberService;

/**
 * 成员Service业务层处理
 *
 * @author 张森
 * @date 2025-01-04
 */
@Service
public class MemberServiceImpl implements IMemberService
{
    @Autowired
    private MemberMapper memberMapper;

    /**
     * 查询成员
     *
     * @param id 成员主键
     * @return 成员
     */
    @Override
    public Member selectMemberById(String id)
    {
        return memberMapper.selectMemberById(id);
    }

    /**
     * 查询成员列表
     *
     * @param member 成员
     * @return 成员
     */
    @Override
    public List<Member> selectMemberList(Member member)
    {
        return memberMapper.selectMemberList(member);
    }

    /**
     * 新增成员
     *
     * @param member 成员
     * @return 结果
     */
    @Override
    public int insertMember(Member member)
    {
        return memberMapper.insertMember(member);
    }

    /**
     * 修改成员
     *
     * @param member 成员
     * @return 结果
     */
    @Override
    public int updateMember(Member member)
    {
        return memberMapper.updateMember(member);
    }

    /**
     * 批量删除成员
     *
     * @param ids 需要删除的成员主键
     * @return 结果
     */
    @Override
    public int deleteMemberByIds(String[] ids)
    {
        return memberMapper.deleteMemberByIds(ids);
    }

    /**
     * 删除成员信息
     *
     * @param id 成员主键
     * @return 结果
     */
    @Override
    public int deleteMemberById(String id)
    {
        return memberMapper.deleteMemberById(id);
    }

   @Override
   public Member login(String email, String password) {
     System.out.println("sfsddsfs"+email+" "+password);
     Member member = memberMapper.findByEmailAndPassword(email, password);
     if (member == null) {

       return null;
     }
     return member;
    }
}
