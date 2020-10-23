package com.example.libyaproject.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.libyaproject.Absy;
import com.example.libyaproject.Conn;
import com.example.libyaproject.Loading;
import com.example.libyaproject.Models.UserModel;
import com.example.libyaproject.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class LoginActivity extends Activity {

    UserModel user;

    EditText username,password;
    TextView forgetpassword;
    Button btn_login;
    String result;
    Intent intent,fpIntent;
    Thread thread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Loading loading = new Loading(this);


        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        forgetpassword = findViewById(R.id.forgetpassword);
        btn_login = findViewById(R.id.btn_login);

        intent = new Intent(this , MainActivity.class);
        fpIntent = new Intent(this,ForgetPasswordActivity.class);


        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                loading.startLoading();
                  thread=new Thread(){
                    @Override
                    public void run() {
                        if(Absy.isInternetWorking()){
                        String data = "";

                        try {
                            data = URLEncoder.encode("username", "UTF-8")
                                    + "=" + URLEncoder.encode(username.getText().toString().trim(), "UTF-8");
                            data += "&" + URLEncoder.encode("password", "UTF-8")
                                    + "=" + URLEncoder.encode(password.getText().toString().trim(), "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }


                        result = Conn.sendHttpRequest(data, Absy.url + "andriod/login.php");
                        loading.closeLoading();
                        if(result.equals("Account Not Found"))
                            Snackbar.make(v, "الحساب غير موجود", Snackbar.LENGTH_LONG).show();
                        else if(result.equals("Wrong Username/Password"))
                            Snackbar.make(v, "خطا في الحساب او كلمة المرور", Snackbar.LENGTH_LONG).show();
                        else {
                                user = UserModel.fromjson(result);
                                intent.putExtra("user",user);
                                loading.closeLoading();
                                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
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

        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(fpIntent);
            }
        });
    }

}