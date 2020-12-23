package com.example.libyaproject.Fragments;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.libyaproject.Utils;
import com.example.libyaproject.Conn;
import com.example.libyaproject.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class NewsFragment extends Fragment {

    String result,data;
    ListView listView;
    ArrayList<String> oldNews,newNews;
    ArrayAdapter<String> adapter;
    Timer timer;
    Activity activity;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.news_fragment, container, false);
        listView = v.findViewById(R.id.listview);
        activity =new Activity();
        oldNews =new ArrayList();
        newNews =new ArrayList();
        timer = new Timer();

        oldNews.addAll(Utils.read(getContext()));

        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, oldNews);
        listView.setAdapter(adapter);
        timer.schedule(new TimerTask() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                if(Utils.isInternetWorking()){
                try {
                    data = URLEncoder.encode("searchtext", "UTF-8")
                            + "=" + URLEncoder.encode("", "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                result = Conn.sendHttpRequest(data, Utils.url+"andriod/GetNews.php");
                newNews = Utils.arrayjsonTOarraylist(result);
                if (newNews != null){
                        if(!oldNews.equals(newNews)){
                            String CHANNEL_ID="MYCHANNEL";
                            NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,"name", NotificationManager.IMPORTANCE_LOW);
                            Notification notification=new Notification.Builder(getContext(),CHANNEL_ID)
                                    .setContentText("تم اضافة مستجدات جديدة")
                                    .setContentTitle("مستجدات")
                                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                                    .setChannelId(CHANNEL_ID)
                                    .setSmallIcon(android.R.drawable.sym_action_chat)
                                    .build();

                            NotificationManager notificationManager=(NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                            notificationManager.createNotificationChannel(notificationChannel);
                            notificationManager.notify(1,notification);
                        }
                        Utils.write(getContext(),newNews);
                        activity.runOnUiThread(()->{
                        oldNews.clear();
                        oldNews.addAll(newNews);
                        adapter.notifyDataSetChanged();
                        }
                    );
                }
            }
                else
                Snackbar.make(v,"NO INTERNET", Snackbar.LENGTH_INDEFINITE).show();

            }
        },5000,10000);

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}