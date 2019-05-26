package com.example.gsy.gchat.mqtt;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.gsy.gchat.bean.Message;

import org.eclipse.paho.android.service.BuildConfig;

/**
 * Created by GSY on 2019/5/14.
 * 作用：创建一个服务用于连接服务端和接收和发送数据
 */

public class MqttService extends Service {

    private final String TAG = "MqttServiceLog";
    private MqttMethod mqttMethod;
    private Message ReceiveMessage;
    private String SendMessage;
    private Context context;
    private int state;
    private String Topic;

    public MqttService(Context context){
        super();
        this.context = context;
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
        if (state == 2){
            Toast.makeText(this.context,"连接不到服务器",Toast.LENGTH_SHORT).show();
            mqttMethod.reConnect();
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public String getSendMessage() {
        return SendMessage;
    }

    public void setSendMessage(String sendMessage) {
        SendMessage = sendMessage;
        if (state == 2){
            Toast.makeText(this.context,"连接不到服务器",Toast.LENGTH_SHORT).show();
        }
        mqttMethod.reConnect();//重新连接服务器
        new Thread(new Runnable() {
            @Override
            public void run() {
                mqttMethod.SendMsg(Topic,SendMessage,0);
                Log.i("MqttSendMessageTest","赋值成功:"+SendMessage);
            }
        }).start();
    }

    public Message getReceiveMessage() {

        if (state == 2){
            mqttMethod.reConnect();//重新连接服务器
        }
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
}
