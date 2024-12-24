package com.sdut.laboratorybackmodule.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {
    private int id;
    private String intro;
    private String imageUrl;
    private String email;
    private int role;
    private String password;
    private String name;
    private String contactWay;

    public Member(int id, String intro, String imageUrl, String email, int role, String password, String name, String contactWay) {
        this.id = id;
        this.intro = intro;
        this.imageUrl = imageUrl;
        this.email = email;
        this.role = role;
        this.password = password;
        this.name = name;
        this.contactWay = contactWay;
    }

    // toString() method (optional for debugging)
    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", intro='" + intro + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", contactWay='" + contactWay + '\'' +
                '}';
    }
}