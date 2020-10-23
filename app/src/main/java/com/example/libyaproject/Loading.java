package com.example.libyaproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class Loading {

    Activity activity;
    AlertDialog alertDialog;

    public Loading(Activity mActvity) {
        activity = mActvity;
    }

    public void startLoading(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading,null));
        builder.setCancelable(true);

        alertDialog = builder.create();
        alertDialog.show();
    }

    public void closeLoading(){
        alertDialog.dismiss();
    }

}
