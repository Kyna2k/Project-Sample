package com.example.assigment.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment.R;
import com.example.assigment.adapter.Recycle_Grid_Sach;
import com.example.assigment.model.Sach;
import com.example.assigment.view.MainActivity;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    RecyclerView listSach;
    Recycle_Grid_Sach recycle_grid_sach;
    public FragmentHome()
    {

    }
    public static FragmentHome getInstance()
    {
        FragmentHome fragmentHome = new FragmentHome();
        Bundle bundle = new Bundle();
        fragmentHome.setArguments(bundle);
        return fragmentHome;
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
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Sach> ds = ((MainActivity)getActivity()).getAllSach();
        listSach = view.findViewById(R.id.recy_sanpham);
        recycle_grid_sach = new Recycle_Grid_Sach(getContext(),ds);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        listSach.setAdapter(recycle_grid_sach);
        listSach.setLayoutManager(gridLayoutManager);
    }

}
