package com.example.assigment.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment.R;
import com.example.assigment.adapter.Recycle_Grid_Sach;
import com.example.assigment.adapter.Recycle_List_Sach;
import com.example.assigment.model.Sach;
import com.example.assigment.view.MainActivity;

import java.util.ArrayList;

public class FragmentTop10 extends Fragment {
    RecyclerView list_top;
    Recycle_List_Sach apdater;
    public FragmentTop10()
    {

    }
    public static FragmentTop10 getInstance()
    {
        FragmentTop10 fragmentTop10 = new FragmentTop10();
        Bundle bundle = new Bundle();
        fragmentTop10.setArguments(bundle);
        return fragmentTop10;
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
        return inflater.inflate(R.layout.fragment_top10,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list_top = view.findViewById(R.id.recy_topsanpham);
        getData();
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void getData()
    {
        ArrayList<Sach> ds = ((MainActivity)getActivity()).getTop();
        apdater = new Recycle_List_Sach(getContext(),ds);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        list_top.setLayoutManager(linearLayoutManager);
        list_top.setAdapter(apdater);
    }
}
