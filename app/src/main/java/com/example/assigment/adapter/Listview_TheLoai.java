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
import com.example.assigment.model.TheLoai;

import java.util.ArrayList;

public class Listview_TheLoai extends BaseAdapter {
    private Context context;
    private ArrayList<TheLoai> ds;
    public Listview_TheLoai(Context context, ArrayList<TheLoai> ds)
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
        avatar.setVisibility(View.GONE);
        id.setText(String.valueOf(ds.get(i).getMatheloai()));
        name.setText(ds.get(i).getTentheloai());
        return view;
    }
}
