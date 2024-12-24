package com.sdut.laboratorybackmodule.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Team {
    private Long id; // 主键，使用Long类型来对应Int(10) unsigned
    private String intro; // 团队简介
    private String email; // 团队邮箱地址
    private Long teamLeaderId; // 团队领导id
    private String name; // 团队名称

}
