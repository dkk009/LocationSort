package com.restaurant.deepak.restaurant.network;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 *
 */
public class JsonRequest extends Request<JSONObject> {

    private Response.Listener<JSONObject> mListener;
    public JsonRequest(int method, String url, Response.ErrorListener listener) {
        super(method, url, listener);
    }


    public JsonRequest(int method, String url, JSONObject jsonRequest,
                       Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
    }
    @Override
    public String getBodyContentType() {
        return "application/json";
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {

            String header = HttpHeaderParser.parseCharset(response.headers, "ISO-8859-1");
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            if (jsonString.length() == 0) {
                return Response.success(null, HttpHeaderParser.parseCacheHeaders(response));
            }
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        mListener.onResponse(response);
    }
}
