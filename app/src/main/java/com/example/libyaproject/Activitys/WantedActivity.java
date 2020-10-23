package com.example.libyaproject.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.libyaproject.Models.WantedModel;
import com.example.libyaproject.R;

public class WantedActivity extends Activity {
    TextView name,motherName,nationalNumber,wantedState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wanted);

        name = findViewById(R.id.name);
        nationalNumber = findViewById(R.id.nationalnumber);
        motherName = findViewById(R.id.mothername);
        wantedState = findViewById(R.id.wantedState);
        WantedModel wanted = (WantedModel) getIntent().getSerializableExtra("wanted");

        name.setText(wanted.name);
        nationalNumber.setText(wanted.national_number);
        motherName.setText(wanted.motherName);
        wantedState.setText(wanted.wantedState);
    }
}