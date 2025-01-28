package com.ruoyi.laboratory.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 出版物对象 publication
 *
 * @author 张森
 * @date 2025-01-04
 */
public class Publication extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 出版物id */
    private String id;

    /** 简介 */
    @Excel(name = "简介")
    private String intro;

    /** 出版物地址 */
    @Excel(name = "出版物地址")
    private String downloadUrl;

    /** 作者 */
    @Excel(name = "作者")
    private String author;

    /** 出版物名称 */
    @Excel(name = "出版物名称")
    private String name;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date releaseDate;

    /** 期刊名 */
    @Excel(name = "期刊名")
    private String periodicalName;

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
    public void setDownloadUrl(String downloadUrl)
    {
        this.downloadUrl = downloadUrl;
    }

    public String getDownloadUrl()
    {
        return downloadUrl;
    }
    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getAuthor()
    {
        return author;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setReleaseDate(Date releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    public Date getReleaseDate()
    {
        return releaseDate;
    }
    public void setPeriodicalName(String periodicalName)
    {
        this.periodicalName = periodicalName;
    }

    public String getPeriodicalName()
    {
        return periodicalName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("intro", getIntro())
            .append("downloadUrl", getDownloadUrl())
            .append("author", getAuthor())
            .append("name", getName())
            .append("releaseDate", getReleaseDate())
            .append("periodicalName", getPeriodicalName())
            .toString();
    }
}
