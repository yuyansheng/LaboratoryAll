package com.ruoyi.laboratory.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 研究对象 research
 *
 * @author 张森
 * @date 2025-01-04
 */
public class Research extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 研究id */
    private String id;

    /** 简介 */
    @Excel(name = "简介")
    private String intro;

    /** 研究项目地址 */
    @Excel(name = "研究项目地址")
    private String url;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date beginTimes;

    /** 研究名 */
    @Excel(name = "研究名")
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
    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }
    public void setBeginTimes(Date beginTimes)
    {
        this.beginTimes = beginTimes;
    }

    public Date getBeginTimes()
    {
        return beginTimes;
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
            .append("url", getUrl())
            .append("beginTimes", getBeginTimes())
            .append("name", getName())
            .toString();
    }
}
