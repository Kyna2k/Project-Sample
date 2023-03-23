package com.example.assigment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.assigment.R;
import com.example.assigment.model.User;
import com.example.assigment.view.ChangePassActivity;
import com.example.assigment.view.MainActivity;

public class FragmentCaNhan extends Fragment {
    private ImageView avatar;
    private TextView username,tenuser,type,email;
    private CardView btn_email,doiavatar,doimatkhau,dangxuat,capnhatthongtin;
    private User user;
    public FragmentCaNhan()
    {

    }
    public static FragmentCaNhan getInstance()
    {
        FragmentCaNhan fragmentCaNhan = new FragmentCaNhan();
        Bundle bundle = new Bundle();
        fragmentCaNhan.setArguments(bundle);
        return fragmentCaNhan;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
        {

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thongtincanhan,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        avatar = view.findViewById(R.id.avatar_user);
        username = view.findViewById(R.id.username);
        tenuser = view.findViewById(R.id.tenuser);
        type = view.findViewById(R.id.type);
        email = view.findViewById(R.id.email_ne);
        doimatkhau = view.findViewById(R.id.doimatkhau);
        dangxuat = view.findViewById(R.id.dangxuat);
        capnhatthongtin = view.findViewById(R.id.capnhatthongtin);
        setData();
        dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        doimatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).doimatkhau();
            }
        });
        capnhatthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).capnhatthongtin();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setData();
    }
    public void setData()
    {
        user = ((MainActivity)getActivity()).getUser();
        Glide.with(this).load(getActivity().getResources().getIdentifier(user.getAvatar(),"mipmap",getActivity().getPackageName())).circleCrop().into(avatar);
        username.setText(user.getUsername());
        tenuser.setText(user.getHoTen());
        email.setText(user.getEmail());
        Log.i("fragnemt", "onResume: ");
        int getType = user.getType();
        String setnameType = "";
        switch (getType)
        {
            case 0:
                setnameType = "Loại tài khoảng: Admin";
                break;
            case 1:
                setnameType = "Loại tài khoảng: Thủ thư";
                break;
            default:
                setnameType = "Loại tài khoảng: Member";
                break;
        }
        type.setText(setnameType);
    }
}
