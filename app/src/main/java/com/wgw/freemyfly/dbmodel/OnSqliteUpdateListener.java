/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.wgw.freemyfly.dbmodel;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by wgw on 2018/5/25.
 */

public interface OnSqliteUpdateListener {
    public void onSqliteUpdateListener(SQLiteDatabase db, int oldVersion, int newVersion);
}
