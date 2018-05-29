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

import java.util.ArrayList;

/**
 * Created by wgw on 2018/5/25.
 */

public class WgwDBHelper  {

    private final static  int DBVersion = 1;
    private final static  String DBName = "wgw.db";//数据库
    private final static String TB_USERINFO = "userInfo";//标明


    public void createDB(Context context){
        ArrayList<String> sqlTable = new ArrayList<String>();
        sqlTable.add("create TABLE "+TB_USERINFO+"");
        DataBaseOpenHelper.getInstance(context,DBName,DBVersion,sqlTable);
    }



}
