package com.restaurant.deepak.restaurant.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.restaurant.deepak.restaurant.R;
import com.restaurant.deepak.restaurant.custom_views.SlidingTabLayout;

/**
 *
 */
public class MainTabFragment extends BaseFragment {

    private static final int IN_YOUR_CITY_TAB = 1;
    private static final int NEAR_BY_TAB = 2;
    private static final int BEST_OFFER_TAB = 3;

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;
    private Toolbar mToolBar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_tab_fragment,container,false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mToolBar = (Toolbar) view.findViewById(R.id.toolbar);
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        TextView txtHeader = (TextView) view.findViewById(R.id.txt_header);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }


    public static class  TabAdapter extends FragmentStatePagerAdapter {

        private static final int NUM_FRAGMENTS = 3;
        private String pageTitle[];

        public TabAdapter(FragmentManager fragmentManager, String[] pageTitle) {
            super(fragmentManager);
            this.pageTitle = pageTitle;
        }

        @Override
        public Fragment getItem(int position) {
            BaseFragment baseFragment = null;
            switch (position) {
                case IN_YOUR_CITY_TAB:
                    return baseFragment;
                case NEAR_BY_TAB:
                    return baseFragment;
                case BEST_OFFER_TAB:
                    return baseFragment;
            }
            return baseFragment;
        }

        @Override
        public int getCount() {
            return NUM_FRAGMENTS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pageTitle[position];
        }
    }
}
