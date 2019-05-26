package com.example.gsy.gchat.bean;

/**
 * Created by GSY on 2019/4/21.
 * 作用：Message类
 * isReciver作用：如果是true的话就是接收数据，如果是false的话就是发送数据
 */

public class Message {

    private String context;
    private String clientID;
    private String time;
    private boolean isReciver;





    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public boolean isReciver() {
        return isReciver;
    }

    public void setReciver(boolean reciver) {
        isReciver = reciver;
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
                ", isReciver=" + isReciver +
                '}';
    }
}
