/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.wgw.freemyfly;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.text.format.Time;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Properties;
import java.util.TreeSet;

/**
 * Created by wgw on 2018/6/6.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler{
    public static String TAG = "wgw_CrashHeadler";
    public static boolean DEBUG=true;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private static CrashHandler INSTANCE;
    private Context mContext;
    private Properties mDeviceCrashInfo = new Properties();
    private static final String VERSION_NAME="versionName";
    private static final String VERSION_CODE="versionCode";
    private static final String STACK_TRACE="STACK_TRACE";
    private static final String CRASH_REPORTER_EXTENSION = ".log";
    private static  Object syncRoot = new Object();

    private CrashHandler(){}
    public static CrashHandler getInstance(){
        if (INSTANCE == null){
            synchronized(syncRoot){
                if(INSTANCE == null){
                    INSTANCE = new CrashHandler();
                }
            }
        }
        return INSTANCE;
    }

    public void init(Context context){
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if(!hanleException(e)&&mDefaultHandler!=null){
            mDefaultHandler.uncaughtException(t,e);
        }else{

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e1) {
                Log.e("wgw_Handler","error",e);
            }
            android.os.Process.killProcess(android.os.Process.myTid());
            System.exit(10);
        }
    }

    private boolean hanleException(Throwable e){
        if(e == null){
            return true;
        }
        final String msg = e.getLocalizedMessage();
        if(msg == null){
            return false;
        }
        new Thread(){
            @Override
            public void run() {
                Looper.prepare();
                if(DEBUG){
                    Log.d(TAG, "异常信息->"+msg);
                    Toast toast = Toast.makeText(mContext, "程序出错，即将退出:\r\n" + msg,
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                    //保存错误报告文件
//                    LogToFile.w("my",msg);**//这句话可以先注释掉，这是我单独写的一个log写入类,下面已提供了该类**
                }
//                MsgPrompt.showMsg(mContext, "程序出错啦", msg+"\n点确认退出");
                Looper.loop();
            }
        }.start();
        //收集设备信息
        collectCrashDeviceInfo(mContext);
        //保存错误报告文件
        //saveCrashInfoToFile(ex);
        //发送错误报告到服务器
        //sendCrashReportsToServer(mContext);
        return true;
    }

    /**
     * 在程序启动时候, 可以调用该函数来发送以前没有发送的报告
     */
    public void sendPreviousReportsToServer() {
        sendCrashReportsToServer(mContext);
    }

    /**
     * 把错误报告发送给服务器,包含新产生的和以前没发送的.
     * @param ctx
     */
    private void sendCrashReportsToServer(Context ctx) {
        String[] crFiles = getCrashReportFiles(ctx);
        if (crFiles != null && crFiles.length > 0) {
            TreeSet<String> sortedFiles = new TreeSet<String>();
            sortedFiles.addAll(Arrays.asList(crFiles));
            for (String fileName : sortedFiles) {
                File cr = new File(ctx.getFilesDir(), fileName);
                postReport(cr);
                cr.delete();// 删除已发送的报告
            }
        }
    }
    private void postReport(File file) {
        // TODO 发送错误报告到服务器
    }
    /**
     * 获取错误报告文件名
     * @param ctx
     * @return
     */
    private String[] getCrashReportFiles(Context ctx) {
        File filesDir = ctx.getFilesDir();
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(CRASH_REPORTER_EXTENSION);
            }
        };
        return filesDir.list(filter);
    }

    /**
     * 保存错误信息到文件中
     * @param ex
     * @return
     */
    private String saveCrashInfoToFile(Throwable ex) {
        Writer info = new StringWriter();
        PrintWriter printWriter = new PrintWriter(info);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        String result = info.toString();
        printWriter.close();
        mDeviceCrashInfo.put("EXEPTION", ex.getLocalizedMessage());
        mDeviceCrashInfo.put(STACK_TRACE, result);
        try {
            //long timestamp = System.currentTimeMillis();
            Time t = new Time("GMT+8");
            t.setToNow(); // 取得系统时间
            int date = t.year * 10000 + t.month * 100 + t.monthDay;
            int time = t.hour * 10000 + t.minute * 100 + t.second;
            String fileName = "crash-" + date + "-" + time + CRASH_REPORTER_EXTENSION;
            FileOutputStream trace = mContext.openFileOutput(fileName,
                    Context.MODE_PRIVATE);
            mDeviceCrashInfo.store(trace, "");
            trace.flush();
            trace.close();
            return fileName;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing report file...", e);
        }
        return null;
    }

    /**
     * 收集程序崩溃的设备信息
     *
     * @param ctx
     */
    public void collectCrashDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                mDeviceCrashInfo.put(VERSION_NAME,
                        pi.versionName == null ? "not set" : pi.versionName);
                mDeviceCrashInfo.put(VERSION_CODE, ""+pi.versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Error while collect package info", e);
        }
        //使用反射来收集设备信息.在Build类中包含各种设备信息,
        //例如: 系统版本号,设备生产商 等帮助调试程序的有用信息
        //具体信息请参考后面的截图
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                mDeviceCrashInfo.put(field.getName(), ""+field.get(null));
                if (DEBUG) {
                    Log.d(TAG, field.getName() + " : " + field.get(null));
                }
            } catch (Exception e) {
                Log.e(TAG, "Error while collect crash info", e);
            }
        }
    }

}
