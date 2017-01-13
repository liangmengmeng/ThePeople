package com.people.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * 1.
 * 2.梁萌萌
 * 3.2017/1/6
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<String> fm_tab;
    private ArrayList<Fragment> fm_list;

    public ViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fm_list, ArrayList<String> fm_tab) {
        super(fm);
        this.fm_list=fm_list;
        this.fm_tab=fm_tab;
    }

    @Override
    public Fragment getItem(int position) {
        return fm_list.get(position);
    }

    @Override
    public int getCount() {
        return fm_list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fm_tab.get(position%fm_tab.size());
    }
}
