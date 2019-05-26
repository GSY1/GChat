package com.example.gsy.gchat;


import android.content.ClipData;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.gsy.gchat.adapter.FragmentAdapter;
import com.example.gsy.gchat.adapter.MessageAdapter;
import com.example.gsy.gchat.bean.Message;
import com.example.gsy.gchat.chat.ChatFriendActivity;
import com.example.gsy.gchat.chat.MessagesFragment;
import com.example.gsy.gchat.dynamic.DynamicFragment;
import com.example.gsy.gchat.linkman.LinkmanFragment;
import com.example.gsy.gchat.login.LoginActivity;
import com.example.gsy.gchat.mqtt.MqttMethod;
import com.example.gsy.gchat.mqtt.MqttServiceTest;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<Fragment> fragments = new ArrayList<>();
    private FragmentAdapter fragmentAdapter;
    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    //private MenuItem olditem;


    //设置导航栏的监听器
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {




            switch (item.getItemId()){

                case R.id.messages:
                    viewPager.setCurrentItem(0);
                    return true;

                case R.id.linkman:
                    viewPager.setCurrentItem(1);
                    return true;

                case R.id.dynamic:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        viewPager = (ViewPager) findViewById(R.id.G_viewPager);

        //设置监听器
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        initFragment();
        initViewPager();
    }

     //添加fragment
    public void initFragment(){
        fragments.add(new MessagesFragment());
        fragments.add(new LinkmanFragment());
        fragments.add(new DynamicFragment());
    }

    //配置ViewPager
    public void initViewPager(){
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}
