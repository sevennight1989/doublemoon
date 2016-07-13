package com.huazhu.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import java.util.List;

/**
 * Created by uiprj on 7/6/16.
 */
public class BasePagerAdapter extends FragmentPagerAdapter {

    private String mTitles[];
    private List<Fragment> mList;
    public BasePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(List<Fragment> list){
        mList = list;
    }

    public void setTitles(String [] titles){
        mTitles = titles;
    }


    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==  ((Fragment)object).getView();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
