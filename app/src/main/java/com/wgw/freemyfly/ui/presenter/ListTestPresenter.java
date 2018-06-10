/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.wgw.freemyfly.ui.presenter;

import com.wgw.freemyfly.commons.WGWLog;
import com.wgw.freemyfly.ui.base.BasePresenter;
import com.wgw.freemyfly.ui.beans.Person;
import com.wgw.freemyfly.ui.mvpmodel.MVPListTestModel;
import com.wgw.freemyfly.ui.view.iListTestView;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wgw on 2018/6/10.
 */

public class ListTestPresenter extends BasePresenter<iListTestView> {
    private MVPListTestModel mvpListTestModel;
    private iListTestView miListTestView;
    @Override
    public void attachView(iListTestView listTestView ) {
        miListTestView = listTestView;
        mvpListTestModel = new MVPListTestModel();
    }

    public Disposable initData(){
        Observable<ArrayList<Person>> observable = mvpListTestModel.initGetData();
        return observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<Person>>() {
                    @Override
                    public void accept(ArrayList<Person> persons) throws Exception {
                        WGWLog.d("Consumer",persons.size()+"--"+Thread.currentThread().getName());
                        miListTestView.refreshList(persons);

                    }
                });

    }
}
