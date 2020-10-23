package com.example.libyaproject.Models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class UserModel implements Serializable {
    public String id,name,username,password,phonenumber,access,rq;

    public UserModel(String id, String name, String username, String password,String phonenumber,String access,String rq) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
        this.access = access;
        this.rq = rq;
    }

    public static UserModel fromjson(String json)
    {
        try {
            JSONObject user = new JSONObject(json);
            String id = user.getString("id");
            String name = user.getString("name");
            String username = user.getString("username");
            String password = user.getString("password");
            String phonenumber = user.getString("phonenumber");
            String access = user.getString("access");
            String rq = user.getString("rq");
            return new UserModel(
                    id,
                    name,
                    username,
                    password,
                    phonenumber,
                    access,
                    rq
            );
        } catch (JSONException e) {
            return null;
        }
    }
}
