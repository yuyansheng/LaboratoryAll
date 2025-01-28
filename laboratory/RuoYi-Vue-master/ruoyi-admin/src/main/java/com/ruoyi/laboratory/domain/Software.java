package com.ruoyi.laboratory.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 软件对象 software
 *
 * @author 张森
 * @date 2025-01-04
 */
public class Software extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 姓名 */
    private String id;

    /** 简介 */
    @Excel(name = "简介")
    private String intro;

    /** 软件下载地址 */
    @Excel(name = "软件下载地址")
    private String downloadUrl;

    /** 阅读文档下载地址 */
    @Excel(name = "阅读文档下载地址")
    private String documentUrl;

    /** 软件名 */
    @Excel(name = "软件名")
    private String name;

    /** 下载次数 */
    @Excel(name = "下载次数")
    private String downloadTimes;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date releaseDate;

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
    public void setDocumentUrl(String documentUrl)
    {
        this.documentUrl = documentUrl;
    }

    public String getDocumentUrl()
    {
        return documentUrl;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setDownloadTimes(String downloadTimes)
    {
        this.downloadTimes = downloadTimes;
    }

    public String getDownloadTimes()
    {
        return downloadTimes;
    }
    public void setReleaseDate(Date releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    public Date getReleaseDate()
    {
        return releaseDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("intro", getIntro())
            .append("downloadUrl", getDownloadUrl())
            .append("documentUrl", getDocumentUrl())
            .append("name", getName())
            .append("downloadTimes", getDownloadTimes())
            .append("releaseDate", getReleaseDate())
            .toString();
    }
}
