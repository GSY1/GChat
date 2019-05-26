package com.example.gsy.gchat.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.gsy.gchat.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText register_name;
    private EditText register_password;
    private AppCompatButton btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        register_name = (EditText) findViewById(R.id.register_name);
        register_password = (EditText) findViewById(R.id.register_password);
        btn_register = (AppCompatButton) findViewById(R.id.btn_register);

        btn_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:

                break;
        }
    }


}
