package com.example.libyaproject;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Absy {


    public static String url = "https://absyinbis.000webhostapp.com/";

    public static boolean isInternetWorking() {
        boolean success = false;
        try {
            URL url = new URL("https://www.google.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            success = connection.getResponseCode() == 200;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return success;
    }

    public static ArrayList arrayjsonTOarraylist(String jsonarray){
        ArrayList array = new ArrayList();
        try {
            JSONArray news = new JSONArray(jsonarray);
            for (int i =0 ; i<news.length() ; i++){
                array.add(news.getJSONObject(i).getString("text"));
            }

            System.out.println(array);

            return array;
        } catch (JSONException e) {
            return null;
        }
    }

    public static void write(Context context,ArrayList<String> arrayList){
        File tempFile;
        File cDir = context.getCacheDir();

        tempFile = new File(cDir.getPath() + "/" + "textFile.txt") ;

        FileWriter writer=null;
        try {
            writer = new FileWriter(tempFile);
            for (int i = 0 ; i < arrayList.size() ; i++)
            writer.write(arrayList.get(i)+"\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList read(Context context) {
        File tempFile;
        File cDir = context.getCacheDir();
        ArrayList arrayList = new ArrayList();
        tempFile = new File(cDir.getPath() + "/" + "textFile.txt") ;
        String strLine="";
        StringBuilder text = new StringBuilder();
        try {
            FileReader fReader = new FileReader(tempFile);
            BufferedReader bReader = new BufferedReader(fReader);

            while( (strLine=bReader.readLine()) != null  ){
                arrayList.add(strLine.trim());
            }
        } catch (FileNotFoundException e) {
            return arrayList;
        } catch (IOException e) {
            return arrayList;
        }
        System.out.println(arrayList);
        return arrayList;
    }

    public static boolean isValidPassword(String password) {
        return Pattern.matches("",password);
    }
}