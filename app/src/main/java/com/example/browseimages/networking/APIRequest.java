package com.example.browseimages.networking;

import android.content.Context;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;

import com.example.browseimages.utils.Constants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;




public class APIRequest<T> extends JsonRequest<T> implements  Constants.AppConstants {

    private static final String TAG = "APIRequest";

    private final ObjectMapper mMapper = new ObjectMapper();
    private Class<T> mClazz;
    private Map<String, String> mHeaders;
    private Response.Listener<T> mListener;
     Response.ErrorListener mErrorListener;
    private Context mContext;

    private APIRequest(Builder builder) {
        super(builder.mMethod, builder.mUrl, builder.mRequestBody, builder.mListener, builder.mErrorListener);
        mClazz = builder.mClazz;
        mHeaders = builder.mHeaders;
        mListener = builder.mListener;
        mContext = builder.mContext;
        mErrorListener = builder.mErrorListener;
        setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }




    @Override
    public String getBodyContentType() {
        return CONTENT_FORMAT;
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            mMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return Response.success(mMapper.readValue(json, mClazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (JsonMappingException e) {
            return Response.error(new ParseError(e));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonParseException e) {
            return Response.error(new ParseError(e));
        } catch (IOException e) {
            return Response.error(new ParseError(e));
        }
    }

    public static class Builder<T> {
        private final Class<T> mClazz;
        private final Response.Listener<T> mListener;
        private final Context mContext;
        private final String mUrl;
        private final Response.ErrorListener mErrorListener;
        private final int mMethod;
        private Map<String, String> mHeaders = new HashMap<>();
        private String mRequestBody;


        public Builder(Context ctx, int method, Class<T> clazz, String url, Response.Listener<T> listener, Response.ErrorListener errorListener) {
            mContext = ctx;
            mMethod = method;
            mClazz = clazz;
            mUrl = url;
            mListener = listener;
            mErrorListener = errorListener;
        }

        public Builder setHeaders(Map<String, String> headers) {
            mHeaders = headers;
            return this;
        }

        public Builder setRequestBody(String requestBody) {
            mRequestBody = requestBody;
            return this;
        }

        public APIRequest<T> build() {
            return new APIRequest<>(this);
        }


    }

}

