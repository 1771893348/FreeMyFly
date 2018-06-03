/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.wgw.freemyfly.commons;

import android.util.Log;

/**
 * Created by wgw on 2018/6/3.
 */

public class WGWLog {
    public static boolean isDebug = true;
    private static final String TAG = "wgw_";

    public static void out(String tag, String str){
        if (isDebug){
            System.out.println(TAG+tag+"------"+str);
        }
    }

    public static void d(String tag, String str){
        if (isDebug){
            Log.d(TAG+tag,str);
        }
    }
    public static void e(String tag, String str){
        if (isDebug){
            Log.e(TAG+tag,str);
        }
    }
}
