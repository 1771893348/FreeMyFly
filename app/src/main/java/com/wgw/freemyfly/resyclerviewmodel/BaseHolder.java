/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.wgw.freemyfly.resyclerviewmodel;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

/**
 * Created by wgw on 2018/6/7.
 */

public class BaseHolder extends RecyclerView.ViewHolder{

    private HashMap<Integer, View> mViews = new HashMap<>();
//    private View itemView;
    public BaseHolder(View itemView) {

        super(itemView);
//        this.itemView = itemView;
    }

    /**
     *获取控件的方法
     */
    public<T> T getView(Integer viewId){
        //根据保存变量的类型 强转为该类型
        View view = mViews.get(viewId);
        if(view==null){
            view= itemView.findViewById(viewId);
            //缓存
            mViews.put(viewId,view);
        }
        return (T)view;
    }

    /**
     *传入文本控件id和设置的文本值，设置文本
     */
    public BaseHolder setText(Integer viewId, String value){
        TextView textView = getView(viewId);
        if (textView != null) {
            textView.setText(value);
        }
        return this;
    }
    /**
     * 传入图片控件id和资源id，设置图片
     */
    public BaseHolder setImageResource(Integer viewId, Integer resId) {
        ImageView imageView = getView(viewId);
        if (imageView != null) {
            imageView.setImageResource(resId);
        }
        return this;
    }
}

