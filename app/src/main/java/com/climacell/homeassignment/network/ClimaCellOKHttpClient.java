package com.climacell.homeassignment.network;

import android.content.Context;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

public class ClimaCellOKHttpClient {

    public ClimaCellOKHttpClient(){ }


    public OkHttpClient getCacheClient(Context context){
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(context.getCacheDir(), cacheSize);
        return new OkHttpClient.Builder()
                // Add cache
                .cache(cache)
                .build();
    }


}
