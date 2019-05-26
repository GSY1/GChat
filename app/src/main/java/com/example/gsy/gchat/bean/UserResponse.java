package com.example.gsy.gchat.bean;

import java.util.Arrays;

/**
 * Created by GSY on 2019/5/8.
 */

public class UserResponse {
    private int note;
    private byte[] image;
    private String info;
    private String userName;

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "note=" + note +
                ", image=" + Arrays.toString(image) +
                ", info='" + info + '\'' +
                '}';
    }
}
