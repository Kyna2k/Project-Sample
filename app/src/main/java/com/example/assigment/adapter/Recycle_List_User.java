package com.example.assigment.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.assigment.R;
import com.example.assigment.method.call_back;
import com.example.assigment.model.User;

import java.util.ArrayList;
import java.util.Date;

import kotlin.jvm.internal.Lambda;

public class Recycle_List_User extends RecyclerView.Adapter<Recycle_List_User.ViewHolder> {
    private ArrayList<User> ds;
    private Context context;
    private call_back callback_recycle;
    public Recycle_List_User(Context context, ArrayList<User> ds,call_back callback_recycle)
    {
        this.context = context;
        this.ds = ds;
        this.callback_recycle = callback_recycle;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.recycle_list_user,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(context.getResources().getIdentifier(ds.get(position).getAvatar(),"mipmap",context.getPackageName())).circleCrop().into(holder.avatar);
        holder.id_ne.setText(String.valueOf(ds.get(position).getId()));
        holder.username.setText(ds.get(position).getEmail());
        holder.tenuser.setText(ds.get(position).getHoTen());
        holder.email.setText(ds.get(position).getEmail());
        switch (ds.get(position).getType())
        {
            case 0:
                holder.type.setText("ADMIN");
                holder.type.setBackground(context.getDrawable(R.drawable.background_title_ne));
                break;
            case 1:
                holder.type.setText("THỦ THƯ");
                holder.type.setBackground(context.getDrawable(R.drawable.background_theo_user2));
                break;
            case 2:
                holder.type.setText("MEMBER");
                holder.type.setBackground(context.getDrawable(R.drawable.background));
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return ds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id_ne,type,username,tenuser,email;
        ImageView avatar;
        CardView card_thongtinuser;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_ne = itemView.findViewById(R.id.id_ne);
            type = itemView.findViewById(R.id.type);
            username = itemView.findViewById(R.id.username);
            tenuser = itemView.findViewById(R.id.tenuser);
            email = itemView.findViewById(R.id.email);
            avatar = itemView.findViewById(R.id.avatar);
            card_thongtinuser = itemView.findViewWithTag("card_thongtinuser");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback_recycle.camonban_thanhcong(id_ne);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    callback_recycle.lancuoi_toi_code_fontend(id_ne);
                    return true;
                }
            });
        }
    }
}
