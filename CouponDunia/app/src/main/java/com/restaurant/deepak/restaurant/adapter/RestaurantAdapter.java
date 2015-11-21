package com.restaurant.deepak.restaurant.adapter;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.restaurant.deepak.restaurant.R;

/**
 *
 */
public class RestaurantAdapter  extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>{


    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {

        private View mView;
        private ImageView mImgRestaurant;
        private TextView mTxtName;
        private TextView mTxtCategories;
        private TextView mTxtLocation;
        private RadioButton mRadioFavorite;
        private TextView mTxtOffer;
        public RestaurantViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            initView();
        }

        private void initView() {
            mImgRestaurant = (ImageView)mView.findViewById(R.id.img_restaurant);
            mTxtName = (TextView) mView.findViewById(R.id.txt_restaurant_name);
            mTxtCategories = (TextView) mView.findViewById(R.id.txt_category);
            mTxtLocation = (TextView) mView.findViewById(R.id.txt_location);
            mTxtOffer = (TextView) mView.findViewById(R.id.txt_number_of_offer);
        }
    }
}
