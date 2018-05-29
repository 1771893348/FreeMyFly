/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.wgw.freemyfly.dbmodel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 1、支持多用户数据储存
 * 2、支持 Sqlite数据库升级
 * 3、支持传入 Sql 语句建表
 * 4、支持 SQLiteDatabase 基本操作。比如：execSQL、rawQuery、insert等等
 * 5、解决了数据库并发问题
 *
 * Created by wgw on 2018/5/25.
 */

public class DataBaseOpenHelper extends SQLiteOpenHelper {
    private static Map<String, DataBaseOpenHelper> dbMaps = new HashMap<String, DataBaseOpenHelper>();
    private OnSqliteUpdateListener onSqliteUpdateListener;
    /**
     * 建表语句列表
     */
    private List<String> createTableList;
    private String nowDbName;

    private DataBaseOpenHelper(Context context, String dbName, int dbVersion, List<String> tableSqls) {
        super(context, dbName, null, dbVersion);
        nowDbName = dbName;
        createTableList = new ArrayList<String>();
        createTableList.addAll(tableSqls);
    }

    /**
     *
     * @Title: getInstance
     * @Description: 获取数据库实例
     * @param @param context
     * @param @param userId
     * @param @return
     * @return DataBaseOpenHelper
     * @author
     */
    public static DataBaseOpenHelper getInstance(Context context, String dbName, int dbVersion, List<String> tableSqls) {
        DataBaseOpenHelper dataBaseOpenHelper = dbMaps.get(dbName);
        if (dataBaseOpenHelper == null) {
            dataBaseOpenHelper = new DataBaseOpenHelper(context, dbName, dbVersion, tableSqls);
        }
        dbMaps.put(dbName, dataBaseOpenHelper);
        return dataBaseOpenHelper;
    };

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (String sqlString : createTableList) {
            db.execSQL(sqlString);
        }
    }

    /**
     *
     * @Title: execSQL
     * @Description: Sql写入
     * @param @param sql
     * @param @param bindArgs
     * @return void
     * @author
     */
    public void execSQL(String sql, Object[] bindArgs) {
        DataBaseOpenHelper dataBaseOpenHelper = dbMaps.get(nowDbName);
        synchronized (dataBaseOpenHelper) {
            SQLiteDatabase database = dataBaseOpenHelper.getWritableDatabase();
            database.execSQL(sql, bindArgs);
        }
    }

    /**
     *
     * @Title: rawQuery
     * @Description:
     * @param @param sql查询
     * @param @param bindArgs
     * @param @return
     * @return Cursor
     * @author
     */
    public Cursor rawQuery(String sql, String[] bindArgs) {
        DataBaseOpenHelper dataBaseOpenHelper = dbMaps.get(nowDbName);
        synchronized (dataBaseOpenHelper) {
            SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
            Cursor cursor = database.rawQuery(sql, bindArgs);
            return cursor;
        }
    }

    /**
     *
     * @Title: insert
     * @Description: 插入数据
     * @param @param table
     * @param @param contentValues 设定文件
     * @return void 返回类型
     * @author
     * @throws
     */
    public void insert(String table, ContentValues contentValues) {
        DataBaseOpenHelper dataBaseOpenHelper = dbMaps.get(nowDbName);
        synchronized (dataBaseOpenHelper) {
            SQLiteDatabase database = dataBaseOpenHelper.getWritableDatabase();
            database.insert(table, null, contentValues);
        }
    }

    /**
     *
     * @Title: update
     * @Description: 更新
     * @param @param table
     * @param @param values
     * @param @param whereClause
     * @param @param whereArgs 设定文件
     * @return void 返回类型
     * @throws
     */
    public void update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        DataBaseOpenHelper dataBaseOpenHelper = dbMaps.get(nowDbName);
        synchronized (dataBaseOpenHelper) {
            SQLiteDatabase database = dataBaseOpenHelper.getWritableDatabase();
            database.update(table, values, whereClause, whereArgs);
        }
    }
    /**
     *
     * @Title: delete
     * @Description:删除
     * @param @param table
     * @param @param whereClause
     * @param @param whereArgs
     * @return void
     * @author
     */
    public void delete(String table, String whereClause, String[] whereArgs) {
        DataBaseOpenHelper dataBaseOpenHelper = dbMaps.get(nowDbName);
        synchronized (dataBaseOpenHelper) {
            SQLiteDatabase database = dataBaseOpenHelper.getWritableDatabase();
            database.delete(table, whereClause, whereArgs);
        }
    }

    /**
     *
     * @Title: query
     * @Description: 查
     * @param @param table
     * @param @param columns
     * @param @param selection
     * @param @param selectionArgs
     * @param @param groupBy
     * @param @param having
     * @param @param orderBy
     * @return void
     * @author
     */
    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having,
                        String orderBy) {
        DataBaseOpenHelper dataBaseOpenHelper = dbMaps.get(nowDbName);
        synchronized (dataBaseOpenHelper) {
            SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
            // Cursor cursor = database.rawQuery("select * from "
            // + TableName.TABLE_NAME_USER + " where userId =" + userId, null);
            Cursor cursor = database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
            return cursor;
        }
    }
    /**
     *
     * @Description:查
     * @param table
     * @param columns
     * @param selection
     * @param selectionArgs
     * @param groupBy
     * @param having
     * @param orderBy
     * @param limit
     * @return
     * Cursor
     * @exception:
     * @author:
     */
    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having,
                        String orderBy,String limit) {
        DataBaseOpenHelper dataBaseOpenHelper = dbMaps.get(nowDbName);
        synchronized (dataBaseOpenHelper) {
            SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
            // Cursor cursor = database.rawQuery("select * from "
            // + TableName.TABLE_NAME_USER + " where userId =" + userId, null);
            Cursor cursor = database.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
            return cursor;
        }
    }

    /**
     *
     * @Description 查询，方法重载,table表名，sqlString条件
     * @param @return
     * @return Cursor
     * @author
     */
    public Cursor query(String tableName, String sqlString) {
        DataBaseOpenHelper dataBaseOpenHelper = dbMaps.get(nowDbName);
        synchronized (dataBaseOpenHelper) {
            SQLiteDatabase database = dataBaseOpenHelper.getReadableDatabase();
            Cursor cursor = database.rawQuery("select * from " + tableName + " " + sqlString, null);

            return cursor;
        }
    }

    /**
     * @see android.database.sqlite.SQLiteOpenHelper#close()
     */
    public void clear() {
        DataBaseOpenHelper dataBaseOpenHelper = dbMaps.get(nowDbName);
        dataBaseOpenHelper.close();
        dbMaps.remove(dataBaseOpenHelper);
    }

    /**
     * onUpgrade()方法在数据库版本每次发生变化时都会把用户手机上的数据库表删除，然后再重新创建。<br/>
     * 一般在实际项目中是不能这样做的，正确的做法是在更新数据库表结构时，还要考虑用户存放于数据库中的数据不会丢失,从版本几更新到版本几。(非
     * Javadoc)
     *
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        if (onSqliteUpdateListener != null) {
            onSqliteUpdateListener.onSqliteUpdateListener(db, arg1, arg2);
        }
    }

    public void setOnSqliteUpdateListener(OnSqliteUpdateListener onSqliteUpdateListener) {
        this.onSqliteUpdateListener = onSqliteUpdateListener;
    }
}
