package com.example.lab.mylifelogger;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by lab on 16. 12. 17.
 */

public class InsertData extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        try{
            String time = (String)params[0];
            String lat = (String)params[1];
            String lgt = (String)params[2];

            String link="http://52.78.170.91/myeong/send_data.php";
            String data  = URLEncoder.encode("time", "UTF-8") + "=" + URLEncoder.encode(time, "UTF-8");
            data += "&" + URLEncoder.encode("lat", "UTF-8") + "=" + URLEncoder.encode(lat, "UTF-8");
            data += "&" + URLEncoder.encode("lgt", "UTF-8") + "=" + URLEncoder.encode(lgt, "UTF-8");
            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");

            wr.write( data );
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                sb.append(line);
                break;
            }
            return sb.toString();
        }
        catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }

}