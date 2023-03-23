package com.example.assigment.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.adapter.FragmentViewHolder;

import com.example.assigment.fragment.FragmentCaNhan;
import com.example.assigment.fragment.FragmentHome;
import com.example.assigment.fragment.FragmentTop10;

import java.util.List;

public class ViewPager_Main extends FragmentStateAdapter {
    public ViewPager_Main(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return FragmentHome.getInstance();
            case 1:
                return FragmentTop10.getInstance();
            case 2:
                return FragmentCaNhan.getInstance();
            default:
                return FragmentHome.getInstance();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
