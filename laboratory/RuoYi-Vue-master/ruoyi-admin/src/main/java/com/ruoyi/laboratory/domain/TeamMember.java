package com.ruoyi.laboratory.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 团队成员对象 team_member
 *
 * @author 张森
 * @date 2025-01-04
 */
public class TeamMember extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 团队id */
    @Excel(name = "团队id")
    private String teamId;

    /** 成员id */
    @Excel(name = "成员id")
    private String memberId;

    public void setTeamId(String teamId)
    {
        this.teamId = teamId;
    }

    public String getTeamId()
    {
        return teamId;
    }
    public void setMemberId(String memberId)
    {
        this.memberId = memberId;
    }

    public String getMemberId()
    {
        return memberId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("teamId", getTeamId())
            .append("memberId", getMemberId())
            .toString();
    }
}
