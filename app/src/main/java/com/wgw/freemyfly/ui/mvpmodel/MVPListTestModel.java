/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.wgw.freemyfly.ui.mvpmodel;

import com.wgw.freemyfly.commons.WGWLog;
import com.wgw.freemyfly.ui.beans.Person;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by wgw on 2018/6/10.
 */

public class MVPListTestModel {

    public Observable<ArrayList<Person>> initGetData(){

       return Observable.create(new ObservableOnSubscribe<ArrayList<Person>>() {

            @Override
            public void subscribe(ObservableEmitter<ArrayList<Person> > emitter) throws Exception {
                WGWLog.d("ObservableOnSubscribe",""+Thread.currentThread().getName());
                ArrayList<Person> personList = new ArrayList<Person>();
                Person person;
                int i=0;
                for (;i<10;i++){
                    person = new Person();
                    person.setUserName("wgw"+i);
                    person.setUserAge(i);
                    person.setSex(0);
//                    mPersons.add(person);
                    personList.add(person);

                }
                emitter.onNext(personList);
            }
        });
    }
}
