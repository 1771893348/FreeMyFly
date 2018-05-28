/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.wgw.freemyfly.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wgw.freemyfly.R;
import com.wgw.freemyfly.commons.WGWHandler;
import com.wgw.freemyfly.ui.base.MVPBaseActivity;
import com.wgw.freemyfly.ui.presenter.ChatPresenter;
import com.wgw.freemyfly.ui.view.IChatView;

import butterknife.BindView;

/**
 * Created by wgw on 2018/5/28.
 */

public class ChatActivity extends MVPBaseActivity<IChatView,ChatPresenter> implements IChatView{
    public Button btn_connect;

    @BindView(R.id.btn_disconnect)
    public Button btn_disconnect;

    @BindView(R.id.tv_receive_message)
    public TextView tv_receive_message;

    @BindView(R.id.btn_Receive)
    public Button btn_Receive;

    @BindView(R.id.et_edit)
    public EditText et_edit;

    @BindView(R.id.btn_send)
    public Button btn_send;

    private ChatPresenter mChatPresenter;

    private StringBuffer stringBuffer;

    private Handler mHandler = new WGWHandler(this){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    tv_receive_message.setText(stringBuffer.toString());
                    break;
            }
        }

    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stringBuffer = new StringBuffer();
        initListener();
    }

    private void initListener() {
        mChatPresenter.setOnclickListener(btn_connect);
        mChatPresenter.setOnclickListener(btn_disconnect);
        mChatPresenter.setOnclickListener(btn_Receive);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (null != mHandler){
            mHandler.removeCallbacksAndMessages(null);
        }
        super.onDestroy();
    }

    @Override
    protected void initView() {
        btn_connect = (Button)findViewById(R.id.btn_connect);
        mChatPresenter = new ChatPresenter();
        mChatPresenter.attachView(ChatActivity.this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_chat;
    }

    @Override
    protected ChatPresenter createPresenter() {
        return null;
    }

    @Override
    public EditText getSendMessage() {
        return null;
    }

    @Override
    public void showReceiveMessage(String msg) {
        stringBuffer.append(msg);
        mHandler.sendEmptyMessage(0);
    }


}
