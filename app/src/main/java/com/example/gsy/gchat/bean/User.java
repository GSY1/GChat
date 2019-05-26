package com.example.gsy.gchat.bean;

import java.util.Arrays;

/**
 * Created by GSY on 2019/5/8.
 */

public class User {
    private String name;
    private String password;
    private byte[] images;
    private int note;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getImages() {
        return images;
    }

    public void setImages(byte[] images) {
        this.images = images;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", images=" + Arrays.toString(images) +
                '}';
    }
}
