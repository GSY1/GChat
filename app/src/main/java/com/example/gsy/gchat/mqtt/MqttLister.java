package com.example.gsy.gchat.mqtt;

/**
 * Created by GSY on 2019/4/22.
 */

public interface MqttLister {

    void Connection();//连接成功
    void Fail();//连接失败
    void Lost();//丢失链接
    void Receiver(String message);//接收消息
    void Send();//发送消息
}
