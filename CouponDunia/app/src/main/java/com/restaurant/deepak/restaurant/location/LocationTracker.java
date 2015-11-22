package com.restaurant.deepak.restaurant.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

/**
 *
 */
public class LocationTracker implements LocationListener {

    private boolean isLocationServiceEnabled = false;
    private Context mContext;
    private Location mLastKnownLocation;
    private boolean isGpsEnabled;
    private boolean isNetworkEnabled;
    private LocationManager mLocationManager;

    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute


    public LocationTracker(Context context) {
        mContext = context;
        getLocation();
    }

    public void getLocation() {
        mLocationManager = (LocationManager) mContext
                .getSystemService(Context.LOCATION_SERVICE);
        isGpsEnabled = mLocationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (isNetworkEnabled == false && isGpsEnabled == false) {
            isLocationServiceEnabled = false;
            return;
        } else {

            if(Build.VERSION.SDK_INT >= 23) {
                if (mContext.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && mContext.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
            }
            if (isNetworkEnabled) {
                mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                Log.d("Network", "Network");
                if (mLocationManager != null) {
                    mLastKnownLocation = mLocationManager
                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                }
                isLocationServiceEnabled = true;
            } else if (isGpsEnabled) {

                mLocationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                Log.d("GPS Enabled", "GPS Enabled");
                if (mLocationManager != null) {
                    mLastKnownLocation = mLocationManager
                            .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                }
                isLocationServiceEnabled = true;
            }
        }
    }

    public Location getmLastKnownLocation() {
        return  mLastKnownLocation;
    }
    public boolean isLocationServiceEnabled() {
        return isLocationServiceEnabled;
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastKnownLocation = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void stopLocationTracking() {
        if (null != mLocationManager) {
            if(Build.VERSION.SDK_INT >= 23) {
                if (mContext.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && mContext.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
            }
            mLocationManager.removeUpdates(this);
        }
    }
}
