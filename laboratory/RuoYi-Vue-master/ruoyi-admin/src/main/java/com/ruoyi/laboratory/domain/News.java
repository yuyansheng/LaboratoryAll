package com.ruoyi.laboratory.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 新闻对象 news
 *
 * @author 张森
 * @date 2025-01-04
 */
public class News extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 新闻id */
    private String id;

    /** 封面 */
    @Excel(name = "封面")
    private String image;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date releaseTime;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 作者 */
    @Excel(name = "作者")
    private String authorId;

    /** 简介 */
    @Excel(name = "简介")
    private String intro;

    /** 阅读次数 */
    @Excel(name = "阅读次数")
    private String readTimes;

    /** 删除 */
    @Excel(name = "删除")
    private Integer isDelete;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setImage(String image)
    {
        this.image = image;
    }

    public String getImage()
    {
        return image;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setReleaseTime(Date releaseTime)
    {
        this.releaseTime = releaseTime;
    }

    public Date getReleaseTime()
    {
        return releaseTime;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setAuthorId(String authorId)
    {
        this.authorId = authorId;
    }

    public String getAuthorId()
    {
        return authorId;
    }
    public void setIntro(String intro)
    {
        this.intro = intro;
    }

    public String getIntro()
    {
        return intro;
    }
    public void setReadTimes(String readTimes)
    {
        this.readTimes = readTimes;
    }

    public String getReadTimes()
    {
        return readTimes;
    }
    public void setIsDelete(Integer isDelete)
    {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete()
    {
        return isDelete;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("updateTime", getUpdateTime())
            .append("image", getImage())
            .append("title", getTitle())
            .append("releaseTime", getReleaseTime())
            .append("content", getContent())
            .append("authorId", getAuthorId())
            .append("intro", getIntro())
            .append("readTimes", getReadTimes())
            .append("isDelete", getIsDelete())
            .toString();
    }
}
