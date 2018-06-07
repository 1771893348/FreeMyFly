/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.wgw.freemyfly.resyclerviewmodel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wgw on 2018/6/7.
 */

public class BaseAdapter<T> extends RecyclerView.Adapter{
    private List<T> mList ;
    private int itemViewId;
    public BaseAdapter(Integer itemId ,List<T> list){
        itemViewId = itemId;
        mList = list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemViewId, parent, false);
        BaseHolder holder = new BaseHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        T item = mList.get(position);
        convert((BaseHolder) holder,item);
        OnListener((BaseHolder) holder,item,position);
    }

    protected void OnListener(BaseHolder holder, T item, int position) {
    }

    protected void convert(BaseHolder holder,T item){

    }
    @Override
    public int getItemCount() {
        return mList.size();
    }
}
