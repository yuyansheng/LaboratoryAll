package com.sdut.laboratorybackmodule.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
public class Publication {
    private Long id;
    private String intro;
    private String downloadUrl;
    private String author;
    private String name;
    private Timestamp releaseDate;
    private String periodicalName;
}
