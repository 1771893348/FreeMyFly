/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.wgw.freemyfly.ui.activity;

import android.app.Activity;
import android.content.Context;
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
import com.wgw.freemyfly.ui.base.MVPBaseActivity;
import com.wgw.freemyfly.ui.beans.Person;
import com.wgw.freemyfly.ui.interfaces.MyItemClickListener;
import com.wgw.freemyfly.ui.presenter.ListTestPresenter;
import com.wgw.freemyfly.ui.view.iListTestView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by wgw on 2018/6/5.
 */

public class ListTestActivity extends MVPBaseActivity<iListTestView,ListTestPresenter> implements MyItemClickListener,iListTestView{

    private RecyclerView recyclerView;
    private List<Person> mPersons;
    private MyAdapter myAdapter;
    private Disposable mSubscriber;
    private Context mContext;
    private ListTestPresenter mListTestPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        mSubscriber = Observable.create(new ObservableOnSubscribe<Integer>() {
//
//            @Override
//            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                WGWLog.d("ObservableOnSubscribe",""+Thread.currentThread().getName());
//                Person person;
//                int i=0;
//                for (;i<10;i++){
//                    person = new Person();
//                    person.setUserName("wgw"+i);
//                    person.setUserAge(i);
//                    person.setSex(0);
//                    mPersons.add(person);
//
//                }
//                emitter.onNext(i);
//            }
//        }).map(new Function<Integer, String>() {
//
//            @Override
//            public String apply(Integer integer) throws Exception {
//                WGWLog.d("map",integer+"--"+Thread.currentThread().getName());
//                return "king"+integer;
//            }
//        }).flatMap(new Function<String, Observable<List<String>>>() {
//            @Override
//            public Observable<List<String>> apply(String s) throws Exception {
//                WGWLog.d("flatMap",s+"--"+Thread.currentThread().getName());
//                List<String> list = new ArrayList<String>();
//                list.add(s);
//                list.add("qq");
//                list.add("ww");
//                list.add("tt");
//                return Observable.fromArray(list);
//            }
//        }).map(new Function<List<String>, String>() {
//
//            @Override
//            public String apply(List<String> strings) throws Exception {
//                WGWLog.d("applyMap",strings.size()+"--"+Thread.currentThread().getName());
//                StringBuilder sb = new StringBuilder();
//
//                for (String s:strings) {
//                        sb.append(s+"-=-");
//                }
//                return sb.toString();
//            }
//        }).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String integer) throws Exception {
//                        WGWLog.d("Consumer",integer+"--"+Thread.currentThread().getName());
//                       if(null != myAdapter){
//                           myAdapter.notifyDataSetChanged();
//                       }
//                    }
//                });

    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        mPersons = new ArrayList<>();
        mContext = this;
        mListTestPresenter = new ListTestPresenter();
        mListTestPresenter.attachView(this);
        myAdapter = new MyAdapter(mContext,mPersons);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,1));
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        myAdapter.setOnclikListener(ListTestActivity.this);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.list_layout;
    }

    @Override
    protected ListTestPresenter createPresenter() {
        return new ListTestPresenter();
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
//        mSubscriber =  Observable.interval(1,3,TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
//            @Override
//            public void accept(Long aLong) throws Exception {
//
//            }
//        });
        mSubscriber = mListTestPresenter.initData();
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
        if (mSubscriber != null&& !mSubscriber.isDisposed()) {
            mSubscriber.dispose();
        }
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

    @Override
    public void refreshList(ArrayList<Person> persons) {
        mPersons.clear();
        mPersons.addAll(persons);
        if(null != myAdapter){
            myAdapter.notifyDataSetChanged();
        }
    }
}
