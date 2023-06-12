package com.example.heartreader;

public class User {

    private String name, nickname, phone, userimg;

    public User(String name, String nickname, String phone, String userimg) {
        this.name = name;
        this.nickname = nickname;
        this.phone = phone;
        this.userimg = userimg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserimg() {
        return userimg;
    }

    public void setUserimg(String userimg) {
        this.userimg = userimg;
    }

    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", userimg='" + userimg + '\'' +
                '}';
    }
}
