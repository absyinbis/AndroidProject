package com.example.libyaproject.Activitys;

import android.app.Activity;
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

public class ChangePhoneNumberActivity extends Activity {

    EditText phoneNumber;
    Button ChangePhoneNumber;
    Thread thread;
    String data,result;

    UserModel user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phonenumber);

        phoneNumber = findViewById(R.id.phonenumber);
        ChangePhoneNumber = findViewById(R.id.changephonenumber);

        user = (UserModel) getIntent().getSerializableExtra("user");


        ChangePhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(phoneNumber.getText())){
                    Snackbar.make(view, "الرجاء ادخال جميع الحقول", Snackbar.LENGTH_LONG).show();
                    return;
                }
                thread = new Thread() {
                    @Override
                    public void run() {
                        if(phoneNumber.length() == 10) {
                            try {
                                data = URLEncoder.encode("phonenumber", "UTF-8")
                                        + "=" + URLEncoder.encode(phoneNumber.getText().toString().trim(), "UTF-8");
                                data += "&" + URLEncoder.encode("access", "UTF-8")
                                        + "=" + URLEncoder.encode(user.access.trim(), "UTF-8");
                                data += "&" + URLEncoder.encode("id", "UTF-8")
                                        + "=" + URLEncoder.encode(user.id.trim(), "UTF-8");
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            result = Conn.sendHttpRequest(data, Absy.url + "andriod/ChangePhoneNumber.php");
                            Snackbar.make(view, "لقد تم تغير رقم الهاتف", Snackbar.LENGTH_LONG).show();
                        }
                        else
                            Snackbar.make(view, "الرجاء ادخال رقم الهاتف بصيغة الصحيحة", Snackbar.LENGTH_LONG).show();
                    }
                };
                thread.start();
            }
        });


    }
}
