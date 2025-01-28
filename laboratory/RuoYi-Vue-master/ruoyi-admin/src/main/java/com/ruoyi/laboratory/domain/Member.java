package com.ruoyi.laboratory.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 成员对象 member
 *
 * @author 张森
 * @date 2025-01-04
 */
public class Member extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 成员id */
    private String id;

    /** 简介 */
    @Excel(name = "简介")
    private String intro;

    /** 头像 */
    @Excel(name = "头像")
    private String image;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 角色 */
    @Excel(name = "角色")
    private String role;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contactWay;

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
    public void setImage(String image)
    {
        this.image = image;
    }

    public String getImage()
    {
        return image;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }
    public void setRole(String role)
    {
        this.role = role;
    }

    public String getRole()
    {
        return role;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setContactWay(String contactWay)
    {
        this.contactWay = contactWay;
    }

    public String getContactWay()
    {
        return contactWay;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("intro", getIntro())
            .append("image", getImage())
            .append("email", getEmail())
            .append("role", getRole())
            .append("password", getPassword())
            .append("name", getName())
            .append("contactWay", getContactWay())
            .toString();
    }
}
