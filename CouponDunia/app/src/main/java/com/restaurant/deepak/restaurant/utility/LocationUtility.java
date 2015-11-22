package com.restaurant.deepak.restaurant.utility;

import android.location.Location;
import android.util.Log;

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
}
