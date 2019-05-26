package com.example.gsy.gchat.login;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.gsy.gchat.MainActivity;
import com.example.gsy.gchat.R;
import com.example.gsy.gchat.bean.User;
import com.example.gsy.gchat.bean.UserResponse;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.security.AccessController.getContext;

public class LoginActivity extends AppCompatActivity {

    private final static String TAG = "LoginActivityLog";

    private EditText EmailText;
    private EditText PasswordText;
    private Button LoginButton;
    private TextView SignupLink;
    private Dialog progressDialog;
    private ImageView userLogo;
    private String username;
    private String password;
    private String LoginUrl = "";
    private User user;
    private UserResponse userResponse;
    private int responseCode;
    private String responseInfo;
    private Boolean TokenTime = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EmailText = (EditText) findViewById(R.id.input_email);
        PasswordText = (EditText) findViewById(R.id.input_password);
        LoginButton = (Button) findViewById(R.id.btn_login);
        SignupLink = (TextView) findViewById(R.id.link_signup);
        userLogo = (ImageView) findViewById(R.id.UserLogo);
        RequestOptions options = new RequestOptions();
        Glide.with(LoginActivity.this).load(R.drawable.logo)
                .apply(options.circleCrop())
                .into(userLogo);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = EmailText.getText().toString();
                password = PasswordText.getText().toString();
                if(null!=username && null!=password && !username.equals("") && !password.equals("")){
                    new LoginTask().execute();
                }else{
                    Toast.makeText(LoginActivity.this,"请输入正确的用户名和密码",Toast.LENGTH_SHORT).show();
                }
            }
        });

        SignupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }


    class LoginTask extends AsyncTask<Void,Integer,Boolean>{

        @Override
        protected void onPreExecute() {
           progressDialog = new ProgressDialog(LoginActivity.this,R.style.AppTheme);
           progressDialog.setCancelable(false);
            progressDialog.show();
        }



        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                login();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
             Toast.makeText(LoginActivity.this,responseInfo,Toast.LENGTH_SHORT).show();
             EmailText.setText("");
             PasswordText.setText("");
             finish();
        }


    }



    private void login() throws IOException {
        OkHttpClient client = new OkHttpClient();
        user.setName(username);
        user.setPassword(password);
        user.setNote(1);//登陆note为1
        Gson gson = new Gson();
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain;charset=utf-8"), gson.toJson(user));
        Request request = new Request
                .Builder()
                .url(LoginUrl)
                .post(requestBody)
                .build();
        Response response = client.newCall(request).execute();
          if (response.isSuccessful()){
              Gson gson1 = new Gson();
              userResponse = gson1.fromJson(response.body().toString(),UserResponse.class);
              responseCode = userResponse.getNote();
              responseInfo = userResponse.getInfo();
              User user1 = new User();
              user1.setImages(userResponse.getImage());
              user1.setName(userResponse.getUserName());
                   if (responseCode != 0){
                       if (responseCode == 2){
                           TokenTime = true;
                       }else if(responseCode == 1){
                           TokenTime = false;
                           SharedPreferences.Editor editor = getSharedPreferences("Flag",MODE_PRIVATE).edit();
                           editor.putString("loginuser",gson.toJson(user1));
                           editor.putString("islogin","true");
                           editor.commit();
                       }
                   }
          }
    }

}
