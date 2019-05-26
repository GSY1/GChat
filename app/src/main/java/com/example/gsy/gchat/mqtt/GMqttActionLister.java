package com.example.gsy.gchat.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

/**
 * Created by GSY on 2019/4/26.
 * 作用：监听Mqtt活动
 */

public class GMqttActionLister implements IMqttActionListener {

    private final int SUCCESS = 1;//连接服务器成功
    private final int FAILURE = 2;//连接服务器失败
    private boolean isConnect;
    private int state;

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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getSUCCESS() {
        return SUCCESS;
    }

    public int getFAILURE() {
        return FAILURE;
    }

    public boolean isConnect() {
        return isConnect;
    }

    public void setConnect(boolean connect) {
        isConnect = connect;
    }

    @Override
    public String toString() {
        return "GMqttActionLister{" +
                "SUCCESS=" + SUCCESS +
                ", FAILURE=" + FAILURE +
                ", isConnect=" + isConnect +
                '}';
    }
}
