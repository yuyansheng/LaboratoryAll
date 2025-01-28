package com.ruoyi.laboratory.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 团队对象 team
 *
 * @author 张森
 * @date 2025-01-04
 */
public class Team extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 团队id */
    private String id;

    /** 简介 */
    @Excel(name = "简介")
    private String intro;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 领导 */
    @Excel(name = "领导")
    private String teamLeaderId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setIntro(String intro)
    {
        this.intro = intro;
    }

    public String getIntro()
    {
        return intro;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
    public void setTeamLeaderId(String teamLeaderId)
    {
        this.teamLeaderId = teamLeaderId;
    }

    public String getTeamLeaderId()
    {
        return teamLeaderId;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("intro", getIntro())
            .append("email", getEmail())
            .append("teamLeaderId", getTeamLeaderId())
            .append("name", getName())
            .toString();
    }
}
