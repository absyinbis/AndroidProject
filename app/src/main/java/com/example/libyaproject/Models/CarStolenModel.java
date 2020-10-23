package com.example.libyaproject.Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class CarStolenModel implements Serializable {
    public String structureNumber,plateNumber,vehicleType,vehicleModel,vehicleYear,carState,name,motherName,nationalNumber,wantedState;

    public CarStolenModel(String structureNumber, String plateNumber, String vehicleType, String vehicleModel, String vehicleYear, String motherName, String carState, String name, String nationalNumber, String wantedState) {
        this.structureNumber = structureNumber;
        this.plateNumber = plateNumber;
        this.vehicleType = vehicleType;
        this.vehicleModel = vehicleModel;
        this.vehicleYear = vehicleYear;
        this.motherName = motherName;
        this.carState = carState;
        this.name = name;
        this.nationalNumber = nationalNumber;
        this.wantedState = wantedState;
    }

    public static CarStolenModel fromjson(String json){
        try {
            JSONObject carstolen = new JSONObject(json);
            String structureNumber = carstolen.getString("structureNumber");
            String plateNumber = carstolen.getString("plateNumber");
            String vehicleType = carstolen.getString("vehicleType");
            String vehicleModel = carstolen.getString("vehicleModel");
            String vehicleYear = carstolen.getString("vehicleYear");
            String carState = carstolen.getString("carState");
            String name = carstolen.getString("name");
            String motherName = carstolen.getString("motherName");
            String nationalNumber = carstolen.getString("nationalNumber");
            String wantedState = carstolen.getString("wantedState");
            return new CarStolenModel(
                    structureNumber,
                    plateNumber,
                    vehicleType,
                    vehicleModel,
                    vehicleYear,
                    motherName,
                    carState,
                    name,
                    nationalNumber,
                    wantedState
            );
        } catch (JSONException e) {
            return null;
        }
    }
}
