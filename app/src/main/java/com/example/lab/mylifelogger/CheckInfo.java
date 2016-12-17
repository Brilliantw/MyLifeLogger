package com.example.lab.mylifelogger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

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
        if(!data.equals(""));
        {
            for (String e : sp1) {
                String[] entry = e.split("@");
                Log.e("data : ", e);
                if(entry.length > 1 && entry[1].split("#").length > 1)
                m_Adapter.add(new ListItem(entry[0], entry[1].split("#")[0], entry[1].split("#")[1]));
            }
        }
    }
}

