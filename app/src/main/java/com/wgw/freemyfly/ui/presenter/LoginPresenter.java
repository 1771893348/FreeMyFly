package com.wgw.freemyfly.ui.presenter;

import com.wgw.freemyfly.ui.base.BasePresenter;
import com.wgw.freemyfly.ui.view.iLoginview;

/**
 * Created by wgw on 2018/5/16.
 */

public class LoginPresenter extends BasePresenter<iLoginview> {

    private iLoginview miLoginview;
    @Override
    protected void attachView(iLoginview iLoginview) {
        miLoginview = iLoginview;
    }

    public void userLogin(){
        String userName = miLoginview.getUserName().toString();
        String userPwd = miLoginview.getUserPwd().toString();
    }
}
