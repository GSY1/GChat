package com.example.gsy.gchat.mqtt;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.gsy.gchat.bean.Message;

import org.eclipse.paho.android.service.BuildConfig;

/**
 * Created by GSY on 2019/4/28.
 */

public class MqttServiceTest extends Service {

    private   String TAG = "MqttService";
    private static MqttMethod mqttMethod;
    private Context context;
    private static int state;
    private Message ReceiveMessage;
    private String SendMessage;
    private static String Topic;


    public MqttServiceTest(){
        super();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        if (BuildConfig.DEBUG){
            Log.i(TAG,"onCreate");
        }
        if (mqttMethod == null){
            mqttMethod = new MqttMethod(this.context);
        }
        mqttMethod.Connect();
        state = mqttMethod.getState();
        Log.i(TAG,"state is:"+state);
    }

    public static void servierstart(Context context,String topic){
        Topic = topic;
        Intent startIntent = new Intent(context,MqttServiceTest.class);
        context.startService(startIntent);
        if (mqttMethod == null){
            mqttMethod = new MqttMethod(context);
        }
        mqttMethod.Connect();
        state = mqttMethod.getState();
    }

    public  void start(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                init();
            }
        }).start();
    }


    public void init (){
        switch (state){
            case 1:
                mqttMethod.subTopic(Topic,0);
                break;
            case 2:
                Toast.makeText(this.context,"连接不到服务器",Toast.LENGTH_SHORT).show();
                mqttMethod.Connect();
                break;
            case 3:
                mqttMethod.disConnect();
                Toast.makeText(this.context,"断开连接",Toast.LENGTH_SHORT).show();
                break;
            case 4:
                mqttMethod.ReceiveMsg();
                ReceiveMessage = mqttMethod.getMessage();
                break;
            case 5:
                mqttMethod.SendMsg(Topic,SendMessage,0);
                break;
            default:
                break;
        }
    }

    public String getSendMessage() {
        return SendMessage;
    }

    public void setSendMessage(String sendMessage) {
        SendMessage = sendMessage;
        new Thread(new Runnable() {
            @Override
            public void run() {
                mqttMethod.SendMsg(Topic,SendMessage,0);
                Log.i("MqttSendMessageTest","赋值成功:"+SendMessage);
            }
        }).start();

    }

    public Message getReceiveMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mqttMethod.ReceiveMsg();
                ReceiveMessage = mqttMethod.getMessage();
            }
        }).start();
        return ReceiveMessage;
    }

    public void setReceiveMessage(Message receiveMessage) {
        ReceiveMessage = receiveMessage;
    }

    public static MqttMethod getMqttMethod(){
        return mqttMethod;
    }

    @Override
    public String toString() {
        return "MqttServiceTest{" +
                "mqttMethod=" + mqttMethod +
                ", context=" + context +
                ", state=" + state +
                ", ReceiveMessage=" + ReceiveMessage +
                ", SendMessage='" + SendMessage + '\'' +
                ", Topic='" + Topic + '\'' +
                '}';
    }
}
