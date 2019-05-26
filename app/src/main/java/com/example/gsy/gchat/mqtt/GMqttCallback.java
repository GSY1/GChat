package com.example.gsy.gchat.mqtt;

import android.content.Context;
import android.os.Message;

import com.google.gson.Gson;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Created by GSY on 2019/4/26.
 * 作用：Mqtt回调类
 */

public class GMqttCallback implements MqttCallback {


      private final int SUCCESS = 1;//连接服务器成功
      private final int FAILURE = 2;//连接服务器失败
      private final int LOST = 3;    //断开连接
      private final int RECEIVE = 4; //接收数据
      private final int SEND = 5; //发送数据
      private boolean isConnect;
      private String message;
      private int state;
      private IMqttActionListener mqttActionListener = new IMqttActionListener() {
        @Override
        public void onSuccess(IMqttToken asyncActionToken) {
            state = SUCCESS;
            isConnect = true;
        }

        @Override
        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
            state = FAILURE;
            isConnect = false;
        }
    };


    @Override
    public void connectionLost(Throwable cause) {
               state = LOST;
               isConnect = false;
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
              state  = RECEIVE;
              this.message = message.toString();
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
               state = SEND;
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getLOST() {
        return LOST;
    }

    public int getRECEIVE() {
        return RECEIVE;
    }

    public int getSEND() {
        return SEND;
    }

    public boolean isConnect() {
        return isConnect;
    }

    public void setConnect(boolean connect) {
        isConnect = connect;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public IMqttActionListener getMqttActionListener() {
        return mqttActionListener;
    }

    public void setMqttActionListener(IMqttActionListener mqttActionListener) {
        this.mqttActionListener = mqttActionListener;
    }

    @Override
    public String toString() {
        return "GMqttCallback{" +
                "LOST=" + LOST +
                ", RECEIVE=" + RECEIVE +
                ", SEND=" + SEND +
                ", isConnect=" + isConnect +
                ", message='" + message + '\'' +
                '}';
    }
}
