package com.example.libyaproject.Activitys;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.libyaproject.Absy;
import com.example.libyaproject.Conn;
import com.example.libyaproject.Models.UserModel;
import com.example.libyaproject.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ChangePasswordActivity extends Activity {
    UserModel user;
    String data,result;
    EditText password;
    EditText apassword;
    Button changepassword;
    Thread thread;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        password = findViewById(R.id.password);
        apassword = findViewById(R.id.apassword);
        changepassword = findViewById(R.id.changepassword);

        intent = new Intent(this,LoginActivity.class);

        user = (UserModel) getIntent().getSerializableExtra("user");
        System.out.println(user.phonenumber);

        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(password.getText()) || TextUtils.isEmpty(apassword.getText())){
                    Snackbar.make(view, "الرجاء ادخال جميع الحقول", Snackbar.LENGTH_LONG).show();
                    return;
                }
                thread = new Thread(){
                    @Override
                    public void run() {
                        if(password.getText().toString().equals(apassword.getText().toString())) {
                                try {
                                    data = URLEncoder.encode("id", "UTF-8")
                                            + "=" + URLEncoder.encode(user.id.trim(), "UTF-8");
                                    data += "&" + URLEncoder.encode("access", "UTF-8")
                                            + "=" + URLEncoder.encode(user.access.trim(), "UTF-8");
                                    data += "&" + URLEncoder.encode("password", "UTF-8")
                                            + "=" + URLEncoder.encode(password.getText().toString().trim(), "UTF-8");
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                result = Conn.sendHttpRequest(data, Absy.url + "andriod/ChangePassword.php");

                                if (user.rq.equals("1"))
                                    Snackbar.make(view, "لقد تم تغير كلمة المرور", Snackbar.LENGTH_LONG).show();
                                else {
                                    startActivity(intent);
                                    Snackbar.make(view, "لقد تم تغير كلمة المرور", Snackbar.LENGTH_LONG).show();
                                }

                        }else
                            Snackbar.make(view,"كلمات المرور غير متطابقة",Snackbar.LENGTH_LONG).show();
                    }
                };
                thread.start();
            }
        });
    }
}