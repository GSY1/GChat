package com.example.gsy.gchat.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.gsy.gchat.R;
import com.example.gsy.gchat.bean.Message;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GSY on 2019/4/21.
 * 作用：聊天界面的Adapter
 */

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

     private List<Message> MsgList = new ArrayList<>();
     private  final int RECVERMSG = 0;
     private  final int SENDMSG = 1;
     private Context context;


    public MessageAdapter(Context context,List<Message> list){
        this.context = context;
        MsgList.addAll(list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         View view ;
                if (viewType == RECVERMSG){
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recviermsg_layout,parent,false);
                      return new ReviverHoldr(view);
                }
        else{
                    view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sendmessage_layout,parent,false);
                    return new SendViewHolder(view);
                }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Message message = MsgList.get(position);
                   if (holder instanceof ReviverHoldr){
                       ReviverHoldr reviverHoldr = (ReviverHoldr) holder;
                       reviverHoldr.ReciverMessage.setText(message.getContext());
                       reviverHoldr.ReciverTime.setText(message.getTime());
                       reviverHoldr.ReciverName.setText(message.getClientID());
                       RequestOptions options = new RequestOptions();
                       Glide.with(context).load(R.drawable.logo)
                               .apply(options.circleCrop())
                               .into(((ReviverHoldr) holder).HeadTheyView);
                   }
                    else {
                       SendViewHolder sendViewHolder = (SendViewHolder) holder;
                       sendViewHolder.sendMessage.setText(message.getContext());
                       sendViewHolder.sentName.setText(message.getClientID());
                       sendViewHolder.sendTime.setText(message.getTime());
                       RequestOptions options = new RequestOptions();
                       Glide.with(context).load(R.drawable.logot)
                               .apply(options.circleCrop())
                               .into(((SendViewHolder) holder).HeadMeView);

                   }

    }



     @Override
     public int getItemViewType(int position){
         Log.i("MqttAdapterLog","position is:"+position);
         if (MsgList==null){
             Log.i("MqttAdapterLog","Msglist is null");
         }
               if (MsgList.get(position).isReciver()){
                   Log.i("MqttAdapterLog","isReciver is:true");
                   return RECVERMSG;
               }else {
                   return SENDMSG;
               }
     }

    @Override
    public int getItemCount() {
        Log.i("Mqttadapter","size is:"+MsgList.size());
        return MsgList.size();
    }

    public int getMessageSize(){
        return MsgList.size();
    }


    public void addMessage(Message message){
        MsgList.add(message);
        notifyDataSetChanged();
    }




    static class ReviverHoldr extends RecyclerView.ViewHolder{

        public   ImageView HeadTheyView;
        public   TextView ReciverMessage;
        public   TextView ReciverTime;
        public   TextView ReciverName;


        public ReviverHoldr(View itemView){
            super(itemView);
            HeadTheyView = (ImageView) itemView.findViewById(R.id.HeadThey);
            ReciverMessage = (TextView) itemView.findViewById(R.id.RecvierMessage);
            ReciverName = (TextView) itemView.findViewById(R.id.ReciverName);
            ReciverTime = (TextView) itemView.findViewById(R.id.RecverTime);
            HeadTheyView = (ImageView) itemView.findViewById(R.id.HeadThey);
        }

        public ImageView getHeadTheyView() {
            return HeadTheyView;
        }

        public void setHeadTheyView(ImageView headTheyView) {
            HeadTheyView = headTheyView;
        }

        public TextView getReciverMessage() {
            return ReciverMessage;
        }

        public void setReciverMessage(TextView reciverMessage) {
            ReciverMessage = reciverMessage;
        }

        public TextView getReciverTime() {
            return ReciverTime;
        }

        public void setReciverTime(TextView reciverTime) {
            ReciverTime = reciverTime;
        }

        public TextView getReciverName() {
            return ReciverName;
        }

        public void setReciverName(TextView reciverName) {
            ReciverName = reciverName;
        }



        @Override
        public String toString() {
            return "ReviverHoldr{" +
                    "HeadTheyView=" + HeadTheyView +
                    ", ReciverMessage=" + ReciverMessage +
                    ", ReciverTime=" + ReciverTime +
                    ", ReciverName=" + ReciverName +
                    '}';
        }
    }




      static class SendViewHolder extends RecyclerView.ViewHolder{

          private ImageView HeadMeView;
          private TextView sendMessage;
          private TextView sendTime;
          private TextView sentName;


          public SendViewHolder(View itemView){
              super(itemView);
              HeadMeView = (ImageView) itemView.findViewById(R.id.HeadMe);
              sendMessage = (TextView) itemView.findViewById(R.id.SendMessage);
              sendTime = (TextView) itemView.findViewById(R.id.SendTime);
              sentName = (TextView) itemView.findViewById(R.id.SendName);

          }

          public ImageView getHeadMeView() {
              return HeadMeView;
          }

          public void setHeadMeView(ImageView headMeView) {
              HeadMeView = headMeView;
          }

          public TextView getSendMessage() {
              return sendMessage;
          }

          public void setSendMessage(TextView sendMessage) {
              this.sendMessage = sendMessage;
          }

          public TextView getSendTime() {
              return sendTime;
          }

          public void setSendTime(TextView sendTime) {
              this.sendTime = sendTime;
          }

          public TextView getSentName() {
              return sentName;
          }

          public void setSentName(TextView sentName) {
              this.sentName = sentName;
          }


          @Override
          public String toString() {
              return "SendViewHolder{" +
                      "HeadMeView=" + HeadMeView +
                      ", sendMessage=" + sendMessage +
                      ", sendTime=" + sendTime +
                      ", sentName=" + sentName +
                      '}';
          }
      }






}
