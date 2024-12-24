package com.sdut.laboratorybackmodule.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Software {
    private Long id; // 主键
    private String intro; // 软件简介
    private String downloadUrl; // 软件下载地址
    private String documentUrl; // 说明文档地址
    private String name; // 软件名称
}
