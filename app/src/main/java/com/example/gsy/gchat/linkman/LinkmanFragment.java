package com.example.gsy.gchat.linkman;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gsy.gchat.R;
import com.example.gsy.gchat.bean.Group;
import com.example.gsy.gchat.bean.User;

import java.util.List;


public class LinkmanFragment extends Fragment {


    private Button LinkmanFriendButton;
    private Button LinkmanGroupButton;
    private RecyclerView LinkmanRecyclerView;
    private List<User> users;
    private List<Group> groups;
    private GroupAdapter groupAdapter;
    private FriendAdapter friendAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
             View view = inflater.inflate(R.layout.fragment_linkman, null);
                 LinkmanFriendButton = (Button) view.findViewById(R.id.btn_friend);
                 LinkmanGroupButton = (Button) view.findViewById(R.id.btn_group);
                 LinkmanRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_Linkman);
                    return view;
    }




}
