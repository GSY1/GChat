package com.example.gsy.gchat.bean;

/**
 * Created by GSY on 2019/4/21.
 * 作用：Message类
 */

public class Message {

    private String context;
    private String clientID;
    private String time;
    private int Type;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Message{" +
                "context='" + context + '\'' +
                ", clientID='" + clientID + '\'' +
                ", time='" + time + '\'' +
                ", Type=" + Type +
                '}';
    }
}
