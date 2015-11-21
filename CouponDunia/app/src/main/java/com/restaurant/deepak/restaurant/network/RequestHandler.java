package com.restaurant.deepak.restaurant.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

import com.google.gson.Gson;
import com.restaurant.deepak.restaurant.CouponDunia;
import com.restaurant.deepak.restaurant.interfaces.IDataModel;

import org.json.JSONObject;

import java.util.HashMap;

/**
 *
 */
public class RequestHandler {

    private static final int INITIAL_TIMEOUT_MS = 30000;
    private static Context mContext;
    private static RequestQueue sRequestQueue;
    private static RequestHandler sRequestHandler;
    private static RequestQueue sImageRequestQueue;

    public interface RequestCompletedCallback {
        void onSuccess(IDataModel response);

        void onFailure(VolleyError error);

    }

    public interface ImageRequestCompletedCallback {
        void onSuccess(Bitmap bitmap, String url);

        void onFailure(VolleyError error);
    }


    public static RequestHandler getInstance() {
        if (sRequestQueue == null) {
            sRequestQueue = Volley.newRequestQueue(CouponDunia.getInstance(), new HurlStack());

        }

        if (sRequestHandler == null)
            sRequestHandler = new RequestHandler();
        return sRequestHandler;
    }

    public static RequestQueue getImageRequestQueue() {

        if (sImageRequestQueue == null) {
            sImageRequestQueue =Volley.newRequestQueue(CouponDunia.getInstance(),new HurlStack());
        }
        return sImageRequestQueue;
    }

    public static RequestQueue getRequestQueue() {

        if (sRequestQueue != null) {
            return sRequestQueue;
        } else {
            throw new IllegalStateException("RequestQueue not initialized");
        }
    }

    public static void cancelRequest(String tag) {

        if (sRequestQueue != null) {
            sRequestQueue.cancelAll(tag);
        }
    }

    public void sendJsonRequest(final int method, final String url, HashMap<String, String> reqHeader, final Class cls,
                                final RequestCompletedCallback requestCompletedCallback) {


        Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("resp", "onResponse:" + response);
                Gson gson = new Gson();
                requestCompletedCallback.onSuccess((IDataModel) gson.fromJson(String.valueOf(response),cls));
              //  requestCompletedCallback.onSuccessResp((IDataModel) gson.fromJson(String.valueOf(response), cls));
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("resp", "onErrorResponse:" + error);
                requestCompletedCallback.onFailure(error);
            }
        };
        JsonRequest request = new JsonRequest(method,url,null,listener,errorListener);

        request.setRetryPolicy(new DefaultRetryPolicy(INITIAL_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        sRequestQueue.add(request);
    }
}
