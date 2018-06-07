/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.wgw.freemyfly.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wgw.freemyfly.R;
import com.wgw.freemyfly.ui.beans.Person;
import com.wgw.freemyfly.ui.interfaces.MyItemClickListener;

import java.util.List;

/**
 * Created by wgw on 2018/6/5.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private List<Person> mPersons;
    private LayoutInflater mLayoutInflater;
    private MyItemClickListener myItemClickListener;
    public MyAdapter(Context context, List<Person> list){
        mContext = context;
        mPersons = list;
        mLayoutInflater= LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.list_cell,null));
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Person person = mPersons.get(position);
        viewHolder.ItemImage.setImageResource(R.mipmap.ic_launcher);
        viewHolder.Itemtext.setText(person.getUserName());
        viewHolder.Itemtitle.setText(person.getUserAge()+"");

    }

    @Override
    public int getItemCount() {
        return mPersons.size();
    }

    public void setOnclikListener(MyItemClickListener listener){
        myItemClickListener =listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ItemImage;
        private TextView Itemtitle;
        private TextView Itemtext;
        public ViewHolder(View itemView) {
            super(itemView);
            ItemImage = itemView.findViewById(R.id.ItemImage);
            Itemtitle = itemView.findViewById(R.id.Itemtitle);
            Itemtext = itemView.findViewById(R.id.Itemtext);
        }
    }

}
