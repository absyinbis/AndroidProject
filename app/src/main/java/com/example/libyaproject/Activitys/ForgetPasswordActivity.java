package com.example.libyaproject.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.libyaproject.Absy;
import com.example.libyaproject.Conn;
import com.example.libyaproject.Loading;
import com.example.libyaproject.Models.UserModel;
import com.example.libyaproject.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ForgetPasswordActivity extends Activity {

    EditText username;
    Button send;
    Thread thread;
    Intent intent;
    String data,result;
    UserModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        Loading loading = new Loading(this);

        username = findViewById(R.id.username);
        send = findViewById(R.id.send);
        intent = new Intent(this,SmsCodeActivity.class);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.startLoading();
                thread = new Thread(){
                    @Override
                    public void run() {
                        if(Absy.isInternetWorking()){
                        try {
                            data = URLEncoder.encode("username", "UTF-8")
                                    + "=" + URLEncoder.encode(username.getText().toString().trim(), "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        result = Conn.sendHttpRequest(data,Absy.url+"andriod/ForgotPassword.php");

                        if(result.equals("Not Found"))
                            Snackbar.make(v,"الحساب غير موجود", Snackbar.LENGTH_LONG).show();
                        else
                        {
                            user = UserModel.fromjson(result);
                            intent.putExtra("user",user);
                            startActivity(intent);
                        }
                        }
                        else{
                            Snackbar.make(v,"NO INTERNET", Snackbar.LENGTH_LONG).show();
                            loading.closeLoading();
                        }
                    }
                };
                thread.start();
            }
        });


    }
}