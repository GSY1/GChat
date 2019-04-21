package com.example.gsy.gchat.mqtt;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.compat.BuildConfig;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GSY on 2019/4/22.
 */

public class MqttService extends Service implements MqttLister {

    private  static MqttConfig mqttConfig;
    private static List<MqttLister>  mqttListerList = new ArrayList<>();


    public void start(Context context){
        Intent startIntent = new Intent(context,MqttService.class);
        context.startActivity(startIntent);
    }

    public void stop(Context context){
        Intent stopIntent = new Intent(context,MqttService.class);
        context.startActivity(stopIntent);
    }



    @Nullable


    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) Log.i("MqttTest", "onCreate: ");
        if (mqttConfig==null) {
            mqttConfig=new MqttConfig(this);
        }
        mqttConfig.connectMqtt();
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        mqttConfig.disConnect();
        mqttConfig=null;
        mqttListerList.clear();
    }

    public static MqttConfig getMqttConfig(){
        return mqttConfig;
    }

    public static void addMqttLister(MqttLister lister){
        if (! mqttListerList.contains(lister)){
            mqttListerList.add(lister);
        }
    }

    public static void reMoveMqttLister(MqttLister lister){
        mqttListerList.remove(lister);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void Connection() {
           for (MqttLister mqttLister : mqttListerList){
               mqttLister.Connection();
           }
    }

    @Override
    public void Fail() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                mqttConfig.connectMqtt();
                try {
                    Thread.sleep(2000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

        for (MqttLister mqttLister : mqttListerList){
            mqttLister.Fail();
        }

    }

    @Override
    public void Lost() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mqttConfig.connectMqtt();
                try {
                    Thread.sleep(2000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

        for (MqttLister mqttLister : mqttListerList){
            mqttLister.Lost();
        }
    }

    @Override
    public void Receiver(String message) {

        for (MqttLister mqttLister : mqttListerList){
            mqttLister.Receiver(message);
        }
    }

    @Override
    public void Send() {
        for (MqttLister mqttLister : mqttListerList){
            mqttLister.Send();
        }
    }
}
