package com.example.libyaproject;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.libyaproject.Fragments.NewsFragment;
import com.example.libyaproject.Fragments.SearchFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;


    public PagerAdapter(FragmentManager fm , int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return new SearchFragment();
            case 1 : return new NewsFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
