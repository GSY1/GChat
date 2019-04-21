package com.example.gsy.gchat.mqtt;

import android.media.MediaExtractor;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.eclipse.paho.android.service.BuildConfig;
import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttToken;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttAck;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


/**
 * Created by GSY on 2019/4/22.
 */

public class MqttConfig   {

   /* MQTT参数配置*/
    private static String host = "192.168.0.13";
    private static String port = "1883";
    private static String username = "";
    private static String password = "";
    private static String clientID =  "GSY123";
    private int countType = 0;

   /* Mqtt状态信息*/
    private boolean isConnect = false;
  /* Mqtt支持类  */
    private MqttAsyncClient mqttClient = null;
  /* Mqtt回调函数*/
  private MqttLister mqttLister;

  private  Handler handler = new  Handler(new  Handler.Callback() {
      @Override
      public boolean handleMessage(Message message) {

          switch (message.arg1){

              case 1://链接
                  if (BuildConfig.DEBUG){
                      Log.i("MqttTest","connection");
                  }
                 mqttLister.Connection();
                break;

              case 2://连接失败
                  if (BuildConfig.DEBUG){
                      Log.i("MqttTest","fail");
                  }
                  mqttLister.Fail();
                  break;

              case 3://
                  if (BuildConfig.DEBUG){
                      Log.i("MqttTest","lost");
                  }
                  mqttLister.Lost();
                  break;

              case 4:// 接收消息
                  if (BuildConfig.DEBUG){
                      Log.i("MqttTest","receive");
                  }
                  mqttLister.Receiver((String) message.obj);
                  break;

              case 5:// 发送消息
                  if (BuildConfig.DEBUG){
                      Log.i("MqttTest","send");
                  }
                  mqttLister.Send();
                  break;
          }

          return true;
      }
  })  ;

    //自带监听类，判断MQTT活动变化
    private IMqttActionListener mqttActionListener = new IMqttActionListener() {
        @Override
        public void onSuccess(IMqttToken asyncActionToken) {
                isConnect = true;
                Message msg = new Message();
                msg.arg1 = 1;
               handler.sendMessage(msg);
        }

        @Override
        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    isConnect = false;
                    Message msg = new Message();
                    msg.arg1 = 2;
                    handler.sendMessage(msg);
        }
    };

    //自带的监听回传类

    private MqttCallback mqttCallback = new MqttCallback() {
        @Override
        public void connectionLost(Throwable cause) {
               isConnect = false;
               Message msg = new Message();
               msg.arg1 = 3;
               handler.sendMessage(msg);
        }

        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            Message msg = new Message();
            msg.arg1 = 4;
            msg.obj = new String(message.getPayload());
            handler.sendMessage(msg);
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken token) {
                  Message msg = new Message();
                 msg.arg1 = 5;
                handler.sendMessage(msg);
        }
    };

    public MqttConfig(MqttLister lister){
        mqttLister = lister;
    }

    public void SettingMqtt(String host,String port,String username,String password,String clientID){
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.clientID = clientID;
    }


    //进行MQTT连接

    public void connectMqtt(){

        try {
            mqttClient  = new MqttAsyncClient("tcp://"+this.host+":"+this.port ,
                    "ClientID"+this.clientID, new MemoryPersistence());
            mqttClient.connect(getOptions(),null,mqttActionListener);
            mqttClient.setCallback(mqttCallback);
        }catch (Exception e){
                e.printStackTrace();
        }

    }
  //断开连接
    public void disConnect(){
        try {
            mqttClient.disconnect();
            mqttClient = null;
            isConnect = false;
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    //断开重连
    public void restartConnect(){
        disConnect();
        connectMqtt();
    }


    //发送数据
    public void pubMsg(String Topic,String message,int Qos){

        if (!isConnect){
            Log.i("MqttTest","没有连接到服务器");
            return;
        }
            try{

                mqttClient.publish(Topic,message.getBytes(),Qos,false);

            }catch (Exception e){
                e.printStackTrace();
            }
    }

    // 订阅一个Topic
    public void subTopic(String Topic,int Qos){

        if (!isConnect){
            Log.i("MqttTest","没有连接到服务器");
            return;
        }
        try {

            mqttClient.subscribe(Topic,Qos);

        }catch (Exception e){
            e.printStackTrace();
        }

    }





    //设置MQTT的连接信息
    private MqttConnectOptions getOptions(){
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(false);
        if(this.username!=null&&this.username.length()>0&&this.password!=null&&this.password.length()>0){
            mqttConnectOptions.setUserName(username);
            mqttConnectOptions.setPassword(password.toCharArray());
        }
        mqttConnectOptions.setConnectionTimeout(10);
        mqttConnectOptions.setKeepAliveInterval(20);
        return mqttConnectOptions;
    }

    public boolean isConnect(){
        return isConnect;
    }

}
