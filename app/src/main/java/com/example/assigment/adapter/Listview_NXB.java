package com.example.assigment.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.assigment.R;
import com.example.assigment.model.NXB;
import com.example.assigment.model.TacGia;

import java.util.ArrayList;

public class Listview_NXB extends BaseAdapter {
    private Context context;
    private ArrayList<NXB> ds;
    public Listview_NXB(Context context, ArrayList<NXB> ds)
    {
        this.context = context;
        this.ds = ds;
    }
    @Override
    public int getCount() {
        return ds.size();
    }

    @Override
    public Object getItem(int i) {
        return ds.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            view = inflater.inflate(R.layout.json_items,viewGroup,false);
        }
        TextView id = view.findViewById(R.id.id);
        TextView name = view.findViewById(R.id.name);
        ImageView avatar = view.findViewById(R.id.avatar);
        id.setText(String.valueOf(ds.get(i).getMaNXB()));
        name.setText(ds.get(i).getTenNXB());
        Glide.with(context).load(context.getResources().getIdentifier(ds.get(i).getImages(),"mipmap",context.getPackageName())).circleCrop().into(avatar);
        return view;
    }
}
