/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.wgw.freemyfly.Api;


import com.wgw.freemyfly.App;
import com.wgw.freemyfly.commons.SysCommon;
import com.wgw.freemyfly.utils.StateUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wgw on 2018/5/22.
 */

public class RetrofitApi {

//    private static ReteofitApi mReteofitApi;
    Retrofit retrofitApi;

    RetrofitApi(){
        File httpCacheDirectory = new File(App.mContext.getCacheDir(),"request");
        int cacheSize = 10*1024*1024;//10M
        Cache cache = new Cache(httpCacheDirectory,cacheSize);
        Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = chain->{
            CacheControl.Builder cacheBuilder = new CacheControl.Builder();
            cacheBuilder.maxAge(0, TimeUnit.SECONDS);
            cacheBuilder.maxStale(365,TimeUnit.DAYS);
            CacheControl cacheControl = cacheBuilder.build();
            Request request = chain.request();
            if (!StateUtils.isNetworkAvailable(App.mContext)){
                request = request.newBuilder()
                        .cacheControl(cacheControl)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if(StateUtils.isNetworkAvailable(App.mContext)){
                int maxAge = 0;//read from cache
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control","public,max-age="+maxAge)
                        .build();
            }else {
                int maxStale = 60*60*24*28;
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control","public,only-if-cached,max-stale="+maxStale)
                        .build();
            }
        };
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                .cache(cache).build();
        retrofitApi = new Retrofit.Builder()
                .baseUrl(SysCommon.DAILY_BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit(){
        return retrofitApi;
    }

}
