
package com.restaurant.deepak.restaurant.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.restaurant.deepak.restaurant.interfaces.IDataModel;

public class RestaurantListResp implements IDataModel{

    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("data")
    @Expose
    private ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
    @SerializedName("hash")
    @Expose
    private String hash;

    /**
     * 
     * @return
     *     The status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The restaurantList
     */
    public ArrayList<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    /**
     * 
     * @param restaurantList
     *     The restaurantList
     */
    public void setRestaurantList(ArrayList<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    /**
     * 
     * @return
     *     The hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * 
     * @param hash
     *     The hash
     */
    public void setHash(String hash) {
        this.hash = hash;
    }



    public RestaurantListResp clone() {
        try {
            return (RestaurantListResp)super.clone();
        }catch (Exception e) {

        }
        return this;
    }
}
