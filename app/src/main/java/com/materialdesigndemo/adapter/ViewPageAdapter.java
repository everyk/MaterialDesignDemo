package com.materialdesigndemo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.materialdesigndemo.fragment.PagerFragment;

/**
 * Created by KHQ on 2017/4/14.
 */

public class ViewPageAdapter extends FragmentPagerAdapter {
    private Context mContext;


    private String[] titles = new String[]{"Tab1", "Tab2", "Tab3", "Tab4", "Tab5"};


    public ViewPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;

    }



    @Override
    public Fragment getItem(int position) {
        return PagerFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles[position];

    }
}
