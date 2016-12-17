package com.example.lab.mylifelogger;

import java.io.OutputStreamWriter;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.io.OutputStreamWriter;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.net.URLConnection;
        import java.net.URLEncoder;

public class CheckInfo extends AppCompatActivity {
    public static String data = "";

    ItemAdapter m_Adapter;
    ListView m_ListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_info);


        m_Adapter = new ItemAdapter();
        m_ListView = (ListView) findViewById(R.id.listview);
        m_ListView.setAdapter(m_Adapter);
        String[] sp1 = data.split("&");
        for(String e : sp1){
            String[] entry = e.split("@");
            m_Adapter.add(new ListItem(entry[0], entry[1].split("%")[0], entry[1].split("%")[1]));
        }
    }
}

