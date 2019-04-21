package com.example.gsy.gchat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gsy.gchat.R;
import com.example.gsy.gchat.bean.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GSY on 2019/4/21.
 * 作用：聊天界面的Adapter
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

     private List<Message> MsgList = new ArrayList<>();
     private  final int RECVERMSG = 0;
     private  final int SENDMSG = 1;


    public MessageAdapter(List<Message> list){
        MsgList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_layout,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Message message = MsgList.get(position);
             if (message.getType() == RECVERMSG){//接受消息
                 holder.responseLayout.setVisibility(View.VISIBLE);
                 holder.sendLayout.setVisibility(View.GONE);
                 holder.responseMsg.setText(message.getContext());
             } else{//发送消息
                 holder.sendLayout.setVisibility(View.VISIBLE);
                 holder.responseLayout.setVisibility(View.GONE);
                 holder.sendMsg.setText(message.getContext());
             }
    }



    @Override
    public int getItemCount() {
        return MsgList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{

          LinearLayout responseLayout;
          LinearLayout sendLayout;

          TextView responseMsg;
          TextView sendMsg;

        public ViewHolder(View itemView) {
            super(itemView);
            responseLayout = (LinearLayout) itemView.findViewById(R.id.response_layout);
            sendLayout = (LinearLayout) itemView.findViewById(R.id.send_layout);
            responseMsg = (TextView) itemView.findViewById(R.id.response_message);
            sendMsg = (TextView) itemView.findViewById(R.id.send_message);
        }
    }



}
