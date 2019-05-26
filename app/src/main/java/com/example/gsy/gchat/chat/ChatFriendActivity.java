package com.example.gsy.gchat.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.gsy.gchat.R;
import com.example.gsy.gchat.adapter.MessageAdapter;
import com.example.gsy.gchat.bean.Message;
import com.example.gsy.gchat.mqtt.MqttMethod;
import com.example.gsy.gchat.mqtt.MqttService;
import com.example.gsy.gchat.mqtt.MqttServiceTest;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatFriendActivity extends AppCompatActivity {

    private String Name;
    private String TopicName;

    private Toolbar toolbar;
    private RecyclerView chatRecyclerView;
    private Button sendButton;
    private EditText inputText;
    private List<Message> messageList = new ArrayList<>();
    private MessageAdapter messageAdapter;
    private Gson gson = new Gson();
    private MqttMethod mqttMethod;
    private MqttService mqttService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_friend);
        toolbar = (Toolbar) findViewById(R.id.chatBar);
        chatRecyclerView = (RecyclerView) findViewById(R.id.Message);
        sendButton = (Button) findViewById(R.id.send);
        inputText = (EditText) findViewById(R.id.input);
        setSupportActionBar(toolbar);


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initMqtt();
        initRecyclerView();
       // messageList.add(mqttServiceTest.getReceiveMessage());
        messageAdapter.addMessage(mqttService.getReceiveMessage());
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = inputText.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy:mm:dd HH:mm");
                    Date date = new Date(System.currentTimeMillis());
                    String time = sdf.format(date);

                    Message message = new Message();
                    message.setTime(time);
                    message.setContext(content);
                    message.setClientID("GSY");
                    message.setReciver(false);
                    //发送消息
                    Log.i("MqttMessage","message is:"+messageList.toString());
                    mqttService.setSendMessage(gson.toJson(message));
                    Log.i("MqttMessage","message is:"+"开始发送消息");
                    Log.i("MqttMessage","message is:"+"发送结束");
                    messageAdapter.addMessage(message);

                    int a = messageAdapter.getMessageSize()-1;
                    messageAdapter.notifyItemChanged(a);//刷新消息

                    Log.i("inputTest","刷新到："+a);
                    chatRecyclerView.scrollToPosition(a);//将消息定位到最后一条

                    Log.i("inputTest","定位到："+a);
                    inputText.setText("");
                }
            }
        });


    }

    private void initRecyclerView() {
        messageAdapter = new MessageAdapter(this,messageList);
        chatRecyclerView.setAdapter(messageAdapter);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void initMqtt() {

          mqttService = new MqttService(ChatFriendActivity.this);
       /* Intent intent = new Intent(this, MqttServiceTest.class);
        startService(intent);*/
        Message message = new Message();
        message.setReciver(true);
        message.setClientID("LY");
        message.setContext("GSY");
        message.setTime("2019.05.01");
        messageList.add(message);
        Message message1 = new Message();
        message1.setReciver(false);
        message1.setClientID("GSY");
        message1.setContext("GSY154622222222222222222222222222222222222651651561616516516516516516516516513516516516516516516515123131351651651616516516165165165165165165161651651616565465161651651651651651651651651651651651651651656565161651651651651616161616161616161616161616161616516165165165165465161616161616161156165611652");
        message1.setTime("2019.05.01");
        messageList.add(message1);
        Message message2 = new Message();
        message2.setReciver(false);
        message2.setClientID("GSY");
        message2.setContext("GSY");
        message2.setTime("2019.05.01");
        messageList.add(message2);
    }
}


