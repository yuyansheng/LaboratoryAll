package com.ruoyi.laboratory.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * Style对象 style
 * 
 * @author ruoyi
 * @date 2025-01-08
 */
public class Style extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 背景图 */
    @Excel(name = "背景图")
    private String backImage;

    /** Log图片 */
    @Excel(name = "Log图片")
    private String logoImage;

    /** 生效日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生效日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date effectiveDate;

    /** 首页文本 */
    @Excel(name = "首页文本")
    private String homePageText;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setBackImage(String backImage) 
    {
        this.backImage = backImage;
    }

    public String getBackImage() 
    {
        return backImage;
    }
    public void setLogoImage(String logoImage) 
    {
        this.logoImage = logoImage;
    }

    public String getLogoImage() 
    {
        return logoImage;
    }
    public void setEffectiveDate(Date effectiveDate) 
    {
        this.effectiveDate = effectiveDate;
    }

    public Date getEffectiveDate() 
    {
        return effectiveDate;
    }
    public void setHomePageText(String homePageText) 
    {
        this.homePageText = homePageText;
    }

    public String getHomePageText() 
    {
        return homePageText;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("backImage", getBackImage())
            .append("logoImage", getLogoImage())
            .append("effectiveDate", getEffectiveDate())
            .append("homePageText", getHomePageText())
            .toString();
    }
}
