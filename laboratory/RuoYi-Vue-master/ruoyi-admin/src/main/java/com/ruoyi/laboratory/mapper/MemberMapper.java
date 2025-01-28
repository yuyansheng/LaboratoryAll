package com.ruoyi.laboratory.mapper;

import java.util.List;
import com.ruoyi.laboratory.domain.Member;
import org.apache.ibatis.annotations.Param;

/**
 * 成员Mapper接口
 *
 * @author 张森
 * @date 2025-01-04
 */
public interface MemberMapper
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
     * 删除成员
     *
     * @param id 成员主键
     * @return 结果
     */
    public int deleteMemberById(String id);

    /**
     * 批量删除成员
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMemberByIds(String[] ids);

  Member findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
