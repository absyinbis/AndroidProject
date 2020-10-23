package com.example.libyaproject.Activitys;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.libyaproject.Models.CarStolenModel;
import com.example.libyaproject.R;

public class CarStolenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_stolen);

        TextView structureNumber,plateNumber,vehicleType,vehicleModel,vehicleYear,carState,name,motherName,nationalNumber,wantedState;

        structureNumber = findViewById(R.id.structurenumber);
        plateNumber = findViewById(R.id.platenumber);
        vehicleType = findViewById(R.id.cartype);
        vehicleModel = findViewById(R.id.carmodel);
        vehicleYear = findViewById(R.id.caryear);
        carState = findViewById(R.id.carState);
        name = findViewById(R.id.name);
        motherName = findViewById(R.id.mothername);
        nationalNumber = findViewById(R.id.nationalnumber);
        wantedState = findViewById(R.id.wantedState);

        CarStolenModel carstolen = (CarStolenModel) getIntent().getSerializableExtra("carstolen");

        structureNumber.setText(carstolen.structureNumber);
        plateNumber.setText(carstolen.plateNumber);
        vehicleType.setText(carstolen.vehicleType);
        vehicleModel.setText(carstolen.vehicleModel);
        vehicleYear.setText(carstolen.vehicleYear);
        carState.setText(carstolen.carState);
        name.setText(carstolen.name);
        motherName.setText(carstolen.motherName);
        nationalNumber.setText(carstolen.nationalNumber);
        wantedState.setText(carstolen.wantedState);



    }
}