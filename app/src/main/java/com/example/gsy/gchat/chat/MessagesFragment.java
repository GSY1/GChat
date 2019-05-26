package com.example.gsy.gchat.chat;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.gsy.gchat.R;
import com.example.gsy.gchat.login.LoginActivity;


public class MessagesFragment extends Fragment {

     private Button chatButton;
     private Toolbar MessageBar;
     private ImageView userView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_messages, null);
        chatButton = (Button) view.findViewById(R.id.InChat);
        MessageBar =  (Toolbar) view.findViewById(R.id.MessageBar);
        userView = (ImageView) view.findViewById(R.id.btn_login);
        RequestOptions options = new RequestOptions();
        Glide.with(getContext()).load(R.drawable.logo)
                .apply(options.circleCrop())
                .into(userView);

        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChatFriendActivity.class);
                startActivity(intent);
            }
        });

        userView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }



}
