package com.example.gsy.gchat.mqtt;

import android.content.Context;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.UUID;

/**
 * Created by GSY on 2019/4/26.
 *
 */

public class MqttMethod  {

    private String host = "tcp://192.168.73.1:61613";
    private String port;
    private String name = "admin";
    private String password = "root";
    private String clientID = UUID.randomUUID().toString();
    private String Topic = "test/topic";
    private Context context;
    private GMqttCallback callback ;
    private GMqttActionLister actonLister ;
    private MqttAsyncClient client;
    private int state;
    private boolean isConnect;
    private com.example.gsy.gchat.bean.Message message;
    private Gson gson = new Gson();


    public MqttMethod (Context context){
        this.context = context;
    }

    //得到Mqtt服务端与客服端之间的连接状态
    public void initMqtt(){
        state = callback.getState();
        isConnect = callback.isConnect();
    }






   //连接服务器
    public void Connect(){
        try {
            actonLister = new GMqttActionLister();
            callback = new GMqttCallback();
            client = new MqttAsyncClient(this.host,this.clientID,new MemoryPersistence());
            client.connect(getOptions(),null,actonLister);
            client.setCallback(callback);
            state = callback.getState();
            isConnect = callback.isConnect();
            Log.i("MqttConnect","连接服务器");
        }catch (Exception e){
            e.printStackTrace();
            Log.i("MqttConnect","Connectino fanlar");
        }

    }


    //断开Mqtt连接
    public void disConnect(){
        try{
            client.close();
            client = null;
            isConnect = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //断开重连

    public void reConnect(){
        disConnect();
        Connect();
    }


    //往Mqtt服务器发送数据1

    public void SendMsg(String topic,String Msg,int Qos){
        if (!isConnect){
            Log.i("MqttSendMsg","没有连接Mqtt服务器");
            return;
        }
        try {
            client.publish(topic,Msg.getBytes(),Qos,false);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //往Mqtt服务器发送数据1
    public void SendMsg(String topic,byte[] Msg,int Qos){
        if (!isConnect){
            Log.i("MqttSendMsg","没有连接Mqtt服务器");
            return;
        }
        try {
            client.publish(topic,Msg,Qos,false);

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    /**
     * 向Mqtt服务器订阅某一个Topic
     */
    public void subTopic(String Topic, int Qos){
        if(!isConnect){
            Log.d("MqttSub","Mqtt连接未打开");
            return;
        }
        try {
            client.subscribe(Topic,Qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    //从Mqtt服务器收到数据
    public void ReceiveMsg(){
              if (state == 4 ){
                 message = gson.fromJson(callback.getMessage(), com.example.gsy.gchat.bean.Message.class);
                  if (message.getClientID().equals(clientID)){
                      message.setReciver(true);
                  }
              }
    }


   //配置MqttConnectOpsions信息
    private MqttConnectOptions getOptions(){
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(false);
        if(this.name!=null&&this.name.length()>0&&this.password!=null&&this.password.length()>0){
            mqttConnectOptions.setUserName(name);
            mqttConnectOptions.setPassword(password.toCharArray());
        }
        mqttConnectOptions.setConnectionTimeout(10);
        mqttConnectOptions.setKeepAliveInterval(20);
        return mqttConnectOptions;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isConnect() {
        return isConnect;
    }

    public void setConnect(boolean connect) {
        isConnect = connect;
    }

    public com.example.gsy.gchat.bean.Message getMessage() {
        return message;
    }

    public void setMessage(com.example.gsy.gchat.bean.Message message) {
        this.message = message;
    }
}
