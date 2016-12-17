package com.example.lab.mylifelogger;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lab on 16. 12. 17.
 */
public class DownloadJson extends AsyncTask<String, String, String> {
    private String jsonName_time;
    private String jsonName_lat;
    private String jsonName_lgt;
    @Override
    protected String doInBackground(String... arg0){
        try {
            return getData((arg0[0]));
        } catch (Exception e){
            return "Json download failed";
        }
    }
    /* Json Parsing Part */
    @Override
    protected void onPostExecute(String result){
        try {
            JSONArray jArray = new JSONArray(result);
            String[] parsedData_1 = new String[jArray.length()];
            String[] parsedData_2 = new String[jArray.length()];
            String[] parsedData_3 = new String[jArray.length()];
            jsonName_time = "time";
            jsonName_lat = "lat";
            jsonName_lgt = "lgt";
            CheckInfo.data = "";
            JSONObject json = null;
            for(int i = 0; i < jArray.length(); i++){
                json = jArray.getJSONObject(i);
                if (json != null) {
                    parsedData_1[i] = json.getString(jsonName_time);
                    parsedData_2[i] = json.getString(jsonName_lat);
                    parsedData_3[i] = json.getString(jsonName_lgt);
                    CheckInfo.data += parsedData_1[i] + "@" + parsedData_2[i] + "#" + parsedData_3[i] + "&";
                }
            }
        } catch(JSONException e) {

            e.printStackTrace();
        }
    }
    /* URL Connecting and Getting JSON data part */
    private String getData(String strurl) {
        StringBuilder sb = new StringBuilder();

        try {
            BufferedInputStream bis = null;
            URL url = new URL(strurl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            int responseCode;


            responseCode = con.getResponseCode();

            if(responseCode == 200) {
                bis = new BufferedInputStream(con.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(bis, "UTF-8"));
                String line = null;

                while((line = reader.readLine()) != null)
                    sb.append(line);
                bis.close();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}

