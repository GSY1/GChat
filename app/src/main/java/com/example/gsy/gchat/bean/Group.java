package com.example.gsy.gchat.bean;

import java.util.List;

/**
 * Created by GSY on 2019/5/10.
 */

public class Group {
    private int clientId;
    private String name;
    private String lable;
    private int number;
    private List<Integer> memberListId;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Integer> getMemberListId() {
        return memberListId;
    }

    public void setMemberListId(List<Integer> memberListId) {
        this.memberListId = memberListId;
    }

    @Override
    public String toString() {
        return "Group{" +
                "clientId=" + clientId +
                ", name='" + name + '\'' +
                ", lable='" + lable + '\'' +
                ", number=" + number +
                ", memberListId=" + memberListId +
                '}';
    }
}
