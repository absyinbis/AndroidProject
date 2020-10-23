package com.example.libyaproject;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Conn {

    public static String sendHttpRequest(String params,String link){
        String result = "";
        try {
            Log.e("start conn", params);
            URL url = new URL(link);

            HttpURLConnection connection;

            connection = (HttpURLConnection) url.openConnection();
            //connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            wr.write(params);
            wr.flush();
            ////////////////////////////////////////////

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                result = result.concat(inputLine);

            }
            Log.e("end conn", result);
            in.close();


        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        }
        if (result.length() == 0) {
            result = "HTTP_REQUEST_FAILED";
        }
        return result;
    }

}