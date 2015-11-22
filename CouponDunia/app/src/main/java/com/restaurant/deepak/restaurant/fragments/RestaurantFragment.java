package com.restaurant.deepak.restaurant.fragments;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.restaurant.deepak.restaurant.R;
import com.restaurant.deepak.restaurant.adapter.RestaurantAdapter;
import com.restaurant.deepak.restaurant.constants.Constants;
import com.restaurant.deepak.restaurant.location.LocationTracker;
import com.restaurant.deepak.restaurant.models.Restaurant;
import com.restaurant.deepak.restaurant.models.RestaurantListResp;
import com.restaurant.deepak.restaurant.utility.LocationUtility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 */
public class RestaurantFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private ArrayList<Restaurant> mRestaurantListResp;
    private LocationTracker mLocationTracker;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reastaurant_list,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_restaurant);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {

        mRestaurantListResp = (ArrayList<Restaurant>) getArguments().get(Constants.DATA);
        int sortType = getArguments().getInt(Constants.SORT_ORDER);
        if(null != mRestaurantListResp) {
            switch (sortType) {
                case Constants.SORT_LOCATION:
                    sortBasedOnLocation();
                    break;
                case Constants.SORT_NEARBY:
                    sortBasedOnCurrentLocation();
                    break;
                case Constants.SORT_OFFER:
                    sortBasedOnOffer();
                    break;
            }
            populateData();
        }
    }

    private void sortBasedOnCurrentLocation() {
        mLocationTracker = new LocationTracker(getActivity());
        mLocationTracker.getLocation();
        if(!mLocationTracker.isLocationServiceEnabled()) {
            Toast.makeText(getActivity(),"Please Enable Location Service",Toast.LENGTH_SHORT).show();
            return;
        }else {
            if(calculateDistance()) {
                sortBasedOnLocation();
                populateData();
            }else {
                Toast.makeText(getActivity(),"Location based Sorting Failed",Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void populateData() {
        RestaurantAdapter restaurantAdapter = new RestaurantAdapter(mRestaurantListResp);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(restaurantAdapter);
    }

    public void sortBasedOnLocation() {
        Collections.sort(mRestaurantListResp, new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant lhs, Restaurant rhs) {
                if (lhs.getDistance() > rhs.getDistance()) {
                    return 1;
                } else if (lhs.getDistance() < rhs.getDistance()) {
                    return -1;
                }
                return 0;
            }
        });
    }

    public boolean calculateDistance() {
        Location startLocation = mLocationTracker.getmLastKnownLocation();
        if(null == startLocation) {
            return false;
        }
        for(Restaurant restaurant : mRestaurantListResp) {
            Location endLocation = new Location(LocationManager.NETWORK_PROVIDER);
            endLocation.setLatitude(Double.valueOf(restaurant.getLatitude()));
            endLocation.setLongitude(Double.valueOf(restaurant.getLongitude()));
            float dist = LocationUtility.findDistance(startLocation, endLocation);
            restaurant.setDistance((double)dist );

        }
        return true;
    }
    public void sortBasedOnOffer() {
        Collections.sort(mRestaurantListResp, new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant lhs, Restaurant rhs) {
                if(lhs.getNumCoupons() > rhs.getNumCoupons()) {
                    return 1;
                }else if(lhs.getNumCoupons() < rhs.getNumCoupons()) {
                    return -1;
                }
                return 0;
            }
        });
    }
}
