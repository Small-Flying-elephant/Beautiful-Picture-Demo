package com.example.hwx631346.beautifulpicturedemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hwx631346.beautifulpicturedemo.fragment.GuideFragment;

import java.util.ArrayList;

public class PagerAdapter extends FragmentPagerAdapter {
    private  ArrayList<Integer> mViewList;
    public PagerAdapter(FragmentManager fm, ArrayList<Integer> mViewList) {
        super(fm);
        this.mViewList = mViewList;
    }

    @Override
    public Fragment getItem(int position) {

        GuideFragment gf = new GuideFragment();
        gf.setImgId(mViewList.get(position));
        return gf;
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }
}
