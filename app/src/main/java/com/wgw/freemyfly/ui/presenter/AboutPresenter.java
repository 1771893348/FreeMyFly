/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.wgw.freemyfly.ui.presenter;

import com.wgw.freemyfly.ui.base.BasePresenter;
import com.wgw.freemyfly.ui.view.iAboutview;

/**
 * Created by wgw on 2018/5/16.
 */

public class AboutPresenter extends BasePresenter<iAboutview> {

    private iAboutview miAboutview;
    @Override
    protected void attachView(iAboutview iAboutview) {
        miAboutview = iAboutview;
    }


}
