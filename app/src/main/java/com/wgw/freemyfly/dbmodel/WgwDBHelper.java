/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.wgw.freemyfly.dbmodel;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wgw on 2018/5/25.
 */

public class WgwDBHelper extends SQLiteOpenHelper {

    private final static  int DBVersion = 1;
    private final static  String DBName = "wgw.db";//数据库
    private final static String TB_USERINFO = "userInfo";//标明

    public WgwDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public WgwDBHelper(Context context) {
        super(context, DBName, null, DBVersion, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists "+ TB_USERINFO +" (Id integer primary key,userName text,userAge Integer , sex Integer ,)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " +TB_USERINFO;
        db.execSQL(sql);
        onCreate(db);
    }
}
