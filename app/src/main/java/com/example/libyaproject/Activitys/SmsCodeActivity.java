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
import com.example.libyaproject.Loading;
import com.example.libyaproject.Models.UserModel;
import com.example.libyaproject.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SmsCodeActivity extends Activity {

    EditText code;
    Button verify;
    UserModel user;
    Thread thread;
    String data,result;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_code);
        Loading loading = new Loading(this);

        code = findViewById(R.id.code);
        verify = findViewById(R.id.verify);

        user = (UserModel) getIntent().getSerializableExtra("user");

        intent = new Intent(this,ChangePasswordActivity.class);
        intent.putExtra("user",user);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(code.getText())){
                    Snackbar.make(v, "الرجاء ادخال جميع الحقول", Snackbar.LENGTH_LONG).show();
                    return;
                }
                loading.startLoading();
                thread = new Thread(){
                    @Override
                    public void run() {
                        if(Absy.isInternetWorking()){
                        try {
                            data = URLEncoder.encode("code", "UTF-8")
                                    + "=" + URLEncoder.encode(code.getText().toString().trim(), "UTF-8");
                            data += "&" + URLEncoder.encode("rq", "UTF-8")
                                    + "=" + URLEncoder.encode(user.rq, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        result = Conn.sendHttpRequest(data, Absy.url+"andriod/CheckCode.php");
                        if(result.equals("yes"))
                            startActivity(intent);
                        else
                            Snackbar.make(v,"الرقم الذي ادخلته غير صحيح", Snackbar.LENGTH_LONG).show();
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