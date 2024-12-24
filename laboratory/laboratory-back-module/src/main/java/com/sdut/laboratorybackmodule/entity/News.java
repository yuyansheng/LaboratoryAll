package com.sdut.laboratorybackmodule.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
public class News {
    private int id;
    private Timestamp updateTime;
    private String image;
    private String title;
    private Timestamp releaseTime;
    private String content;
    private int authorId;
    private String intro;
    private int readTimes;
    private boolean isDelete;

    // Constructors
    public News(int id, Timestamp updateTime, String image, String title, Timestamp releaseTime, String content, int authorId, String intro, int readTimes, boolean isDelete) {
        this.id = id;
        this.updateTime = updateTime;
        this.image = image;
        this.title = title;
        this.releaseTime = releaseTime;
        this.content = content;
        this.authorId = authorId;
        this.intro = intro;
        this.readTimes = readTimes;
        this.isDelete = isDelete;
    }

    // toString() method (optional for debugging)
    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", updateTime=" + updateTime +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", releaseTime=" + releaseTime +
                ", content='" + content + '\'' +
                ", authorId=" + authorId +
                ", intro='" + intro + '\'' +
                ", readTimes=" + readTimes +
                ", isDelete=" + isDelete +
                '}';
    }
}
