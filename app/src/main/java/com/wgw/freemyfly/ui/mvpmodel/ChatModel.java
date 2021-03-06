/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.wgw.freemyfly.ui.mvpmodel;

import android.util.Log;

import com.wgw.freemyfly.Api.OkHttpUtils;
import com.wgw.freemyfly.commons.SysCommon;
import com.wgw.freemyfly.ui.presenter.ChatCallBack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wgw on 2018/5/28.
 */

public class ChatModel {

    private Socket socket;
    private ExecutorService mThreadPool;
    private InputStream is;
    private InputStreamReader isr;
    private BufferedReader br;
    private String response;
    private OutputStream outputStream;
    private ChatCallBack mChatCallBack;
    public void attachCallBack(ChatCallBack chatCallBack){
        mChatCallBack = chatCallBack;
        mThreadPool = Executors.newCachedThreadPool();
    }

    public void connectServer(){
        Log.d("wgw_connectServer","=============");
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(SysCommon.BASE_URL,8080);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (null != socket && socket.isConnected()){
                    mChatCallBack.isConnectServer(true);
                }else {
                    mChatCallBack.isConnectServer(false);
                }
            }
        });

    }

    public void receiverMsg(){

        Log.d("wgw_receiverMsg","=============");
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    if (null == socket){
                        mChatCallBack.receiverMsg("Error:未建立连接");
                        return;
                    }
                    is = socket.getInputStream();
                    isr = new InputStreamReader(is);
                    br = new BufferedReader(isr);
                    response = br.readLine();
                    Log.d("wgw_receiverMsg","============="+response);
                    mChatCallBack.receiverMsg(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void sendMsg(String msg){
        Log.d("wgw_send",msg+socket.isConnected());
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("wgw_send",msg);
                    outputStream = socket.getOutputStream();
                    outputStream.write(msg.getBytes("UTF-8"));
                    outputStream.flush();
                    mChatCallBack.sendMsg(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void disConnect(){
        Log.d("wgw_disConnect","111"+socket.isClosed());
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    if (null != outputStream){
                        outputStream.close();
                    }

                    br.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public void okHttpTesting(){
        Log.d("wgw_okHttpTesting","================");
        OkHttpUtils.okhttp();
    }
}
