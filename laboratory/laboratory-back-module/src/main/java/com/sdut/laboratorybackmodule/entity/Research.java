package com.sdut.laboratorybackmodule.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
public class Research {
    private Long id; // 主键
    private String intro; // 研究简介
    private String url; // 研究项目地址
    private Timestamp beginTimes; // 研究开始时间
    private String name; // 研究项目名称
}
