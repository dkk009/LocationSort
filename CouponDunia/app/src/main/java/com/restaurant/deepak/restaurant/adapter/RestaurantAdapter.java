package com.restaurant.deepak.restaurant.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.restaurant.deepak.restaurant.R;
import com.restaurant.deepak.restaurant.interfaces.IntrEventListener;
import com.restaurant.deepak.restaurant.models.Category;
import com.restaurant.deepak.restaurant.models.Restaurant;
import com.restaurant.deepak.restaurant.network.ImageDownloader;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

/**
 *
 */
public class RestaurantAdapter  extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>{

    private final IntrEventListener mEventListener;
    private List<Restaurant> mRestaurantList;

    public RestaurantAdapter(List<Restaurant> restaurantList,IntrEventListener listener) {
        mRestaurantList = restaurantList;
        mEventListener = listener;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_restaurant,parent,false);
        RestaurantViewHolder restaurantViewHolder = new RestaurantViewHolder(view);
        return restaurantViewHolder;
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
            holder.bindData(mRestaurantList.get(position),position,mEventListener);
    }

    @Override
    public int getItemCount() {
        return null == mRestaurantList?0:mRestaurantList.size();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {

        private View mView;
        private ImageView mImgRestaurant;
        private TextView mTxtName;
        private TextView mTxtCategories;
        private TextView mTxtLocation;
        private CheckBox mRadioFavorite;
        private TextView mTxtOffer;
        private Context mContext;
        public RestaurantViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            mView = itemView;
            initView();
        }

        private void initView() {
            mImgRestaurant = (ImageView)mView.findViewById(R.id.img_restaurant);
            mTxtName = (TextView) mView.findViewById(R.id.txt_restaurant_name);
            mTxtCategories = (TextView) mView.findViewById(R.id.txt_category);
            mTxtLocation = (TextView) mView.findViewById(R.id.txt_location);
            mTxtOffer = (TextView) mView.findViewById(R.id.txt_number_of_offer);
            mRadioFavorite = (CheckBox) mView.findViewById(R.id.radio_favorite);
        }

        public void bindData(Restaurant restaurant,final int pos,final IntrEventListener listener) {
            mTxtName.setText(restaurant.getBrandName());
            List<Category> categoryList = restaurant.getCategories();
            String categoryStr = "";
            if(null != categoryList) {
                for(Category category : categoryList) {
                    categoryStr = categoryStr + mContext.getString(R.string.dot) + category.getName() +" ";
                }
            }
            mTxtCategories.setText(categoryStr);
            double distance = restaurant.getDistance();
            String unit = " m ";
            if(restaurant.getDistance() > 1000) {
                distance = distance / 1000;
                unit = " km ";
            }

            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            String formattedValue =decimalFormat.format(distance);

            try {
                String distStr = ""+ ((Double)decimalFormat.parse(formattedValue)) + unit + restaurant.getCityName();
                mTxtLocation.setText(distStr);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(restaurant.getNumCoupons() >0) {
                String offerStr = restaurant.getNumCoupons() + " " + (restaurant.getNumCoupons() > 1 ? "Offers" : "Offer");
                mTxtOffer.setText(offerStr);
            }
            mRadioFavorite.setChecked(restaurant.isFavorite());
            mRadioFavorite.setTag(restaurant);
            mRadioFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean status = !((Restaurant)v.getTag()).isFavorite();
                            ((CheckBox)v).setChecked(status);
                    ((Restaurant)v.getTag()).setFavorite(status);
                        listener.updateFavorite(status,((Restaurant)v.getTag()));

                }
            });
            downloadImage(restaurant.getLogoURL());
        }

        public void downloadImage(final String url) {
            ImageDownloader.getInstance().getImageLoader().get(url, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    if (null != mImgRestaurant && null != response.getBitmap()) {
                        mImgRestaurant.setImageBitmap(response.getBitmap());
                    }
                }

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }, mImgRestaurant.getWidth(), mImgRestaurant.getHeight());

            Log.d("image", "Url:" + url);
        }
    }
}
