package com.ruoyi.laboratory.service;

import java.util.List;
import com.ruoyi.laboratory.domain.Member;

/**
 * 成员Service接口
 *
 * @author 张森
 * @date 2025-01-04
 */
public interface IMemberService
{
    /**
     * 查询成员
     *
     * @param id 成员主键
     * @return 成员
     */
    public Member selectMemberById(String id);

    /**
     * 查询成员列表
     *
     * @param member 成员
     * @return 成员集合
     */
    public List<Member> selectMemberList(Member member);

    /**
     * 新增成员
     *
     * @param member 成员
     * @return 结果
     */
    public int insertMember(Member member);

    /**
     * 修改成员
     *
     * @param member 成员
     * @return 结果
     */
    public int updateMember(Member member);

    /**
     * 批量删除成员
     *
     * @param ids 需要删除的成员主键集合
     * @return 结果
     */
    public int deleteMemberByIds(String[] ids);

    /**
     * 删除成员信息
     *
     * @param id 成员主键
     * @return 结果
     */
    public int deleteMemberById(String id);

  /**
   * 删除成员信息
   *
   * @param email 登录账号
   * @param password 密码
   * @return 结果
   */
    public Member login(String email, String password);
}
