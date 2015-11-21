package com.restaurant.deepak.restaurant;

import android.app.Application;
import android.content.ComponentCallbacks2;

import com.restaurant.deepak.restaurant.network.ImageDownloader;

/**
 *
 */
public class CouponDunia extends Application {

    private static CouponDunia instance;
    public static CouponDunia getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level >= ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL) {
            ImageDownloader.getInstance().getImageCache().evictAll();
        }
    }

}
