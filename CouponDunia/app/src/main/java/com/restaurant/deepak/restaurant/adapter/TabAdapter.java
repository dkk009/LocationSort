package com.restaurant.deepak.restaurant.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 *
 */
public class TabAdapter extends PagerAdapter {

    private ArrayList<String> mTabList;
    public TabAdapter() {


    }



    @Override
    public CharSequence getPageTitle(int position) {
        return mTabList.size() > position ? mTabList.get(position) : "";
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return null;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
    public int getItemPosition(Object object){
        return POSITION_NONE;
    }
}
