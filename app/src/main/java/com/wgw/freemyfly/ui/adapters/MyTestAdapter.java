/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.wgw.freemyfly.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wgw.freemyfly.R;
import com.wgw.freemyfly.resyclerviewmodel.BaseAdapter;
import com.wgw.freemyfly.resyclerviewmodel.BaseHolder;
import com.wgw.freemyfly.ui.beans.Person;

import java.util.List;

/**
 * Created by wgw on 2018/6/7.
 */

public class MyTestAdapter extends BaseAdapter<Person> {

    private OnItemClickListener mOnItemClickListener;



    public MyTestAdapter(Integer itemId, List<Person> list) {
        super(itemId, list);
    }

    @Override
    protected void convert(BaseHolder holder, Person item) {
        holder.setText(R.id.Itemtitle,item.getUserAge()+"").setText(R.id.Itemtext,item.getUserName()).setImageResource(R.id.ItemImage,R.mipmap.ic_launcher);

    }

    @Override
    protected void OnListener(BaseHolder holder, Person item, int position) {
        if (null != mOnItemClickListener){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                mOnItemClickListener.onItemClick(v,position);
                }
            });
        }

    }

    public interface OnItemClickListener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view,int position);

    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener)
    {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
