package com.restaurant.deepak.restaurant.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.restaurant.deepak.restaurant.R;
import com.restaurant.deepak.restaurant.activities.MainActivity;
import com.restaurant.deepak.restaurant.adapter.CustomFragmentStatePager;
import com.restaurant.deepak.restaurant.constants.Constants;
import com.restaurant.deepak.restaurant.constants.Url;
import com.restaurant.deepak.restaurant.custom_views.SlidingTabLayout;
import com.restaurant.deepak.restaurant.interfaces.IDataModel;
import com.restaurant.deepak.restaurant.interfaces.IntrEventListener;
import com.restaurant.deepak.restaurant.models.Restaurant;
import com.restaurant.deepak.restaurant.models.RestaurantListResp;
import com.restaurant.deepak.restaurant.network.RequestHandler;
import com.restaurant.deepak.restaurant.utility.CommonUtility;

import java.util.ArrayList;

/**
 *
 */
public class MainTabFragment extends BaseFragment implements IntrEventListener{

    private static final int IN_YOUR_CITY_TAB = 0;
    private static final int NEAR_BY_TAB = 1;
    private static final int BEST_OFFER_TAB = 2;

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;
    private Toolbar mToolBar;
    private ProgressBar mProgressBar;
    private SearchView mSearchView;
    private RestaurantListResp mRestaurantListResp;
    private TabAdapter mTabAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_tab_fragment,container,false);
        setHasOptionsMenu(true);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        ((AppCompatActivity)getActivity()).setSupportActionBar((Toolbar) view.findViewById(R.id.toolbar));
        if (((AppCompatActivity)getActivity()) != null) {
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setCustomView(R.layout.search_view);
        }

        mToolBar = (Toolbar) view.findViewById(R.id.toolbar);
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        if(!CommonUtility.isInternetAvailable()) {
            Toast.makeText(getActivity(),"Network Error",Toast.LENGTH_LONG).show();
            return;
        }
        mProgressBar.setVisibility(View.VISIBLE);
        RequestHandler.getInstance().sendJsonRequest(Request.Method.GET, Url.GET_LIST, null, RestaurantListResp.class, new RequestHandler.RequestCompletedCallback() {
            @Override
            public void onSuccess(IDataModel response) {
                handleRequestResp((RestaurantListResp) response);
            }

            @Override
            public void onFailure(VolleyError error) {
                handleRequestFailure(error);
            }
        });
    }

    private void handleRequestResp(RestaurantListResp resp) {
        mProgressBar.setVisibility(View.GONE);
        mRestaurantListResp = resp;
        String[] title = getResources().getStringArray(R.array.tab_names);
        mTabAdapter = new TabAdapter(MainTabFragment.this,title,mRestaurantListResp);
        mViewPager.setAdapter(mTabAdapter);
        mSlidingTabLayout.setViewPager(mViewPager);
    }

    private void handleRequestFailure(VolleyError error) {
        mProgressBar.setVisibility(View.GONE);
    }


   @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        mSearchView = new SearchView(((MainActivity) getActivity()).getSupportActionBar().getThemedContext());
        MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
        MenuItemCompat.setActionView(item, mSearchView);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //  Toast.makeText(getActivity(),query,Toast.LENGTH_SHORT).show();
                if (!TextUtils.isEmpty(query)) {

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateFavorite(boolean status, Restaurant restaurant) {
        for(Restaurant res: mRestaurantListResp.getRestaurantList()) {
            if(res.equals(restaurant)) {
                res.setFavorite(status);
            }
        }
        for(int i =0;i<mTabAdapter.getCount();i++) {

            if(i != mViewPager.getCurrentItem()) {
                RestaurantFragment restaurantFragment = ((RestaurantFragment) mTabAdapter.getItem(i));
                if(null != restaurantFragment)
                restaurantFragment.updateList(restaurant);
            }
        }
       mTabAdapter.notifyDataSetChanged();
    }


    public static class  TabAdapter extends FragmentStatePagerAdapter {

        private static final int NUM_FRAGMENTS = 3;
        private String pageTitle[];

        private RestaurantListResp mRestaurantList;
        private Fragment mFragment;
        private SparseArray<RestaurantFragment> mFragmentList = new SparseArray<>();
        public TabAdapter(MainTabFragment fragment, String[] pageTitle,RestaurantListResp restaurantList) {
            super(fragment.getChildFragmentManager());
            this.pageTitle = pageTitle;
            mRestaurantList = restaurantList;
            mFragment = fragment;
        }

        @Override
        public Fragment getItem(int position) {
            RestaurantFragment baseFragment = new RestaurantFragment();
            Bundle bundle;
            ArrayList<Restaurant> restaurantList;
            switch (position) {
                case IN_YOUR_CITY_TAB:
                    bundle  = new Bundle();
                    restaurantList = new ArrayList<>();
                    for(Restaurant restaurant : mRestaurantList.getRestaurantList()) {
                        restaurantList.add(restaurant.clone());
                    }
                    bundle.putSerializable(Constants.DATA, restaurantList);
                    bundle.putInt(Constants.SORT_ORDER, Constants.SORT_LOCATION);
                    baseFragment.setArguments(bundle);
                    break;
                case NEAR_BY_TAB:
                    bundle = new Bundle();
                    restaurantList = new ArrayList<>();
                    for(Restaurant restaurant : mRestaurantList.getRestaurantList()) {
                        restaurantList.add(restaurant.clone());
                    }
                    bundle.putSerializable(Constants.DATA, restaurantList);
                    bundle.putInt(Constants.SORT_ORDER, Constants.SORT_NEARBY);
                    baseFragment.setArguments(bundle);
                    break;
                case BEST_OFFER_TAB:
                    bundle  = new Bundle();
                    restaurantList = new ArrayList<>();
                    for(Restaurant restaurant : mRestaurantList.getRestaurantList()) {
                        restaurantList.add(restaurant.clone());
                    }
                    bundle.putSerializable(Constants.DATA, restaurantList);
                    bundle.putInt(Constants.SORT_ORDER, Constants.SORT_OFFER);
                    baseFragment.setArguments(bundle);
                    break;
            }
            baseFragment.setEventListener((MainTabFragment)mFragment);
            mFragmentList.put(position, baseFragment);
            return baseFragment;
        }


        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            mFragmentList.remove(position);
            super.destroyItem(container,position,object);

        }

        @Override
        public int getCount() {
            return NUM_FRAGMENTS;
        }

        public RestaurantFragment getRestaurantFragment(int pos) {
            return mFragmentList.get(pos);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pageTitle[position];
        }
    }



}
