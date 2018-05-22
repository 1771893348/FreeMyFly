/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.wgw.freemyfly.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wgw.freemyfly.R;
import com.wgw.freemyfly.ui.base.MVPBaseActivity;
import com.wgw.freemyfly.ui.presenter.LoginPresenter;
import com.wgw.freemyfly.ui.view.iLoginview;

import butterknife.BindView;

public class LoginActivity extends MVPBaseActivity<iLoginview,LoginPresenter> implements iLoginview{

    private LoginPresenter mLoginPresenter;

    private EditText EtUserName;

    private EditText EtUserPwd;

    private Button BtUserLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginPresenter = new LoginPresenter();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter createPresenter() {
        return mLoginPresenter;
    }

    @Override
    protected void initView() {
        EtUserName = findViewById(R.id.et_userName);
        EtUserPwd = findViewById(R.id.et_userPwd);
        BtUserLogin = findViewById(R.id.bt_userLogin);
    }

    @Override
    public EditText getUserName() {
        return EtUserName;
    }

    @Override
    public EditText getUserPwd() {
        return EtUserPwd;
    }

    public void login(View view){

    }
}
