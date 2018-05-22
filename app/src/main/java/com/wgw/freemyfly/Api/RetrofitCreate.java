/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.wgw.freemyfly.Api;

/**
 * Created by wgw on 2018/5/22.
 */

public class RetrofitCreate  {
    private static iRetrofitApi miRetrofit;
    public RetrofitCreate(){

    }
    public iRetrofitApi getiRetrofitSingleton(){
        if (miRetrofit == null){
            synchronized (miRetrofit){
                if (miRetrofit == null){
                    miRetrofit =  new RetrofitApi().getRetrofit().create(iRetrofitApi.class);
                }
            }
        }

        return  miRetrofit;
    }
}
