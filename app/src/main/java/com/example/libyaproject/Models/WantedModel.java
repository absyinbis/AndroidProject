package com.example.libyaproject.Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class WantedModel implements Serializable {
    public String name,national_number,wantedState,motherName;

    public WantedModel(String name, String national_number, String wantedState, String motherName) {
        this.name = name;
        this.national_number = national_number;
        this.wantedState = wantedState;
        this.motherName = motherName;

    }

    public static WantedModel fromjson(String json) {

        try {
            JSONObject wanted = new JSONObject(json);
            String name = wanted.getString("name");
            String motherName = wanted.getString("motherName");
            String nationalNumber = wanted.getString("nationalNumber");
            String wantedState = wanted.getString("wantedState");
            return new WantedModel(
                    name,
                    nationalNumber,
                    wantedState,
                    motherName
            );
        } catch (JSONException e) {
            return null;
        }
    }
}
