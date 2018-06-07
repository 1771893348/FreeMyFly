/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.wgw.freemyfly.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.wgw.freemyfly.R;
import com.wgw.freemyfly.commons.WGWLog;
import com.wgw.freemyfly.ui.adapters.MyAdapter;
import com.wgw.freemyfly.ui.beans.Person;
import com.wgw.freemyfly.ui.interfaces.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wgw on 2018/6/5.
 */

public class ListTestActivity extends Activity implements MyItemClickListener{

    private RecyclerView recyclerView;
    private List<Person> mPersons;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        recyclerView = findViewById(R.id.recyclerView);
        mPersons = new ArrayList<>();
        Person person;
        for (int i=0;i<10;i++){
            person = new Person();
            person.setUserName("wgw"+i);
            person.setUserAge(i);
            person.setSex(0);
            mPersons.add(person);
        }
        myAdapter = new MyAdapter(this,mPersons);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,1));
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        myAdapter.setOnclikListener(this);
        recyclerView.setAdapter(myAdapter);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onItemClick(View view, int position) {
        WGWLog.d("onItemClick"+R.id.cell_item,view.getId()+"=="+position);

        switch(view.getId()){
            case R.id.cell_item:
                Toast.makeText(this, "click"+ mPersons.get(position).getUserName(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
}
