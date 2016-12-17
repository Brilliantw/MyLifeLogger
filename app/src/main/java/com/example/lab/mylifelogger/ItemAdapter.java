package com.example.lab.mylifelogger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;



public class ItemAdapter extends BaseAdapter{
    private ArrayList<ListItem> item_List = new ArrayList<>();
    @Override
    public int getCount()
    {
        return item_List.size();
    }
    @Override
    public Object getItem(int position)
    {
        return item_List.get(position);
    }
    @Override
    public long getItemId(int position)
    {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item, parent, false);
        final TextView tv_time = (TextView) convertView.findViewById(R.id.tv_time);
        final TextView tv_lat = (TextView) convertView.findViewById(R.id.tv_lat);
        final TextView tv_lgt = (TextView) convertView.findViewById(R.id.tv_lgt);
        tv_time.setText(item_List.get(position).getTime());
        tv_lat.setText(item_List.get(position).getLat());
        tv_lgt.setText(item_List.get(position).getLgt());
        return convertView;
    }
    public boolean isEmpty(){
        return item_List.isEmpty();
    }
    // 외부에서 아이템 추가 요청 시 사용
    public void add(ListItem item) {
        item_List.add(item);
        notifyDataSetChanged();
    }
}
