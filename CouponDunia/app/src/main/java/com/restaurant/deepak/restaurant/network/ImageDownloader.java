package com.restaurant.deepak.restaurant.network;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 *
 */
public class ImageDownloader {

    private static ImageDownloader mImageCacheManager;
    private static Bitmap mPlaceHolderBitmap;
    private ImageLoader mImageLoader;
    private BitmapLruCache mBitmapLruCache;

    public static synchronized ImageDownloader getInstance() {
        if(null == mImageCacheManager) {
            mImageCacheManager = new ImageDownloader();

        }
        return mImageCacheManager;
    }

    private ImageDownloader() {


    }
    public synchronized void init() {
        if(null == mBitmapLruCache) {
            mBitmapLruCache = new BitmapLruCache();
        }
        mImageLoader = new ImageLoader(RequestHandler.getInstance().getImageRequestQueue(), mBitmapLruCache);
    }

    public ImageLoader getImageLoader() {
        if(null == mImageLoader) {
            init();
        }
        return mImageLoader;
    }

    public synchronized BitmapLruCache getImageCache() {
        if(null == mBitmapLruCache) {
            mBitmapLruCache = new BitmapLruCache();
        }
        return  mBitmapLruCache;
    }

    public void clearImageCache() {
        if(null != mBitmapLruCache) {
            mBitmapLruCache.evictAll();
        }
    }


    public static class BitmapLruCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache {

        public BitmapLruCache() {
            this(getDefaultLruCacheSize());
        }

        public BitmapLruCache(int sizeInKiloBytes) {
            super(sizeInKiloBytes);
        }

        public static int getDefaultLruCacheSize() {
            final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
            final int cacheSize = maxMemory / 8;

            return cacheSize;
        }

        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getRowBytes() * value.getHeight() / 1024;
        }

        @Override
        public Bitmap getBitmap(String url) {
            return get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            put(url, bitmap);
        }
    }

}
