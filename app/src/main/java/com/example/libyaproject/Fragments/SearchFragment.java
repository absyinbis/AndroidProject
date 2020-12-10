package com.example.libyaproject.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.libyaproject.Absy;
import com.example.libyaproject.Activitys.CarStolenActivity;
import com.example.libyaproject.Activitys.WantedActivity;
import com.example.libyaproject.Conn;
import com.example.libyaproject.Loading;
import com.example.libyaproject.Models.CarStolenModel;
import com.example.libyaproject.Models.WantedModel;
import com.example.libyaproject.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class SearchFragment extends Fragment {
    String data,result;
    WantedModel wanted;
    CarStolenModel carStolen;
    EditText searchText;
    Button carstolenButton;
    Button wantedButton;
    Thread thread;
    Intent wantedIntent;
    Intent carStolenIntent;
    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.search_fragment, container, false);
        Loading loading = new Loading(getActivity());

        searchText = v.findViewById(R.id.text);
        carstolenButton = v.findViewById(R.id.carstolen);
        wantedButton = v.findViewById(R.id.wanted);

        wantedIntent = new Intent(getActivity(), WantedActivity.class);
        carStolenIntent = new Intent(getActivity(), CarStolenActivity.class);

        carstolenButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loading.startLoading();
                        thread = new Thread(){
                            @Override
                            public void run() {
                                if(Absy.isInternetWorking()){
                                try {
                                    data = URLEncoder.encode("searchtext", "UTF-8")
                                            + "=" + URLEncoder.encode(searchText.getText().toString().trim(), "UTF-8");
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                result = Conn.sendHttpRequest(data, Absy.url+"andriod/CheckCarStolen.php");
                                loading.closeLoading();
                                carStolen = CarStolenModel.fromjson(result);
                                carStolenIntent.putExtra("carstolen",carStolen);
                                startActivity(carStolenIntent);
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

        wantedButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(TextUtils.isEmpty(searchText.getText())){
                            Snackbar.make(v, "الرجاء ادخال جميع الحقول", Snackbar.LENGTH_LONG).show();
                            return;
                        }
                        loading.startLoading();

                        thread = new Thread() {
                            @Override
                            public void run() {
                                if(Absy.isInternetWorking()){
                                try {
                                    data = URLEncoder.encode("searchtext", "UTF-8")
                                            + "=" + URLEncoder.encode(searchText.getText().toString().trim(), "UTF-8");
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                                result = Conn.sendHttpRequest(data,Absy.url+"andriod/Wanted.php");
                                loading.closeLoading();
                                wanted = WantedModel.fromjson(result);
                                if (wanted == null)
                                    Snackbar.make(v,"الرقم الوطني غير صحيح", Snackbar.LENGTH_SHORT).show();
                                else {
                                    wantedIntent.putExtra("wanted",wanted);
                                    startActivity(wantedIntent);
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


        return v;
    }
}