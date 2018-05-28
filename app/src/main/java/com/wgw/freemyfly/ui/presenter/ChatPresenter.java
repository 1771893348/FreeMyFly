/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.wgw.freemyfly.ui.presenter;

import android.content.Context;
import android.view.View;
import android.widget.Switch;

import com.wgw.freemyfly.R;
import com.wgw.freemyfly.ui.base.BasePresenter;
import com.wgw.freemyfly.ui.mvpmodel.ChatModel;
import com.wgw.freemyfly.ui.view.IChatView;

import java.util.Base64;

/**
 * Created by wgw on 2018/5/28.
 */

public class ChatPresenter extends BasePresenter<IChatView> implements ChatCallBack{
    private ChatModel mChatModel;
    private IChatView mIchatView;
    private Context mContext;
    public ChatPresenter(){

    }
    public ChatPresenter(Context context){
        mContext = context;
    }
    @Override
    public void attachView(IChatView iChatView) {
        mIchatView = iChatView;
        mChatModel = new ChatModel();
        mChatModel.attachCallBack(this);
    }

    public void setOnclickListener(View view){
        view.setOnClickListener(mChatOnclicListener);
    }

    private View.OnClickListener mChatOnclicListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_connect:
                    mChatModel.connectServer();
                    break;
                case R.id.btn_Receive:
                    mChatModel.receiverMsg();
                    break;
                case R.id.btn_send:
                    mChatModel.sendMsg(mIchatView.getSendMessage().toString()+"\n");
                    break;
                case R.id.btn_disconnect:
                    mChatModel.disConnect();
                    break;
            }
        }
    };

    @Override
    public void isConnectServer(boolean isConnect) {
        if (isConnect){
            mIchatView.showReceiveMessage("Server is connect\n");
        }else {
            mIchatView.showReceiveMessage("Server connect fail\n");
        }
    }

    @Override
    public void receiverMsg(String msg) {
        mIchatView.showReceiveMessage(msg+"\n");
    }
}
