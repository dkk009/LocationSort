package com.restaurant.deepak.restaurant.utility;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import com.restaurant.deepak.restaurant.CouponDunia;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 *
 */
public class LocationUtility {

    public static float findDistance(Location startLocation,Location endLocation) {
        float results [] = new float[1];
        Location.distanceBetween(startLocation.getLatitude(), startLocation.getLongitude(),
                endLocation.getLatitude(), endLocation.getLongitude(), results);
        Log.d("loc","StartLoc:" + "Lat-" +startLocation.getLatitude() +",Longi:" +
                startLocation.getLongitude() +",EndLoc:" +  "Lat-" +endLocation.getLatitude() +",Longi:" +
                endLocation.getLongitude());
        Log.d("loc","Distance:" + results[0]);
        return results[0];
    }

    public static String getAddressFromLatLang(Location location) throws IOException {
        if(location == null) {
            return "";
        }
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(CouponDunia.getInstance(), Locale.getDefault());

        addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

        return addresses.isEmpty() == true ? "" : addresses.get(0).getAddressLine(0).concat(", ").concat(addresses.get(0).getAddressLine(1));
    }
}
