/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.wgw.freemyfly.commons;


import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;


/**
 * Created by wgw on 2018/5/28.
 */

public class WGWHandler  extends Handler {
    private final WeakReference<Activity> mActivity;

    public WGWHandler( Activity activity){
        mActivity = new WeakReference<Activity>(activity);
    }
    @Override
    public void handleMessage(Message msg) {
        if (mActivity.get() == null) {
            return;
        }
        super.handleMessage(msg);
    }
}
