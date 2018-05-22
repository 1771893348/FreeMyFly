package com.wgw.freemyfly.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;


/**
 * Created by wgw on 2018/5/9.
 */

public abstract class MVPBaseActivity<V,T extends BasePresenter<V>> extends Activity{
    protected T mPresenterr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(createPresenter() != null){
            mPresenterr = createPresenter();
            mPresenterr.attachView((V)this);
        }
        setContentView(provideContentViewId());
        ButterKnife.bind(this);
        initView();
    }

    protected abstract void initView();

    //设置布局文件Layout
    protected abstract int provideContentViewId();
    //设置Presenter
    protected  abstract T  createPresenter();
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
        super.onDestroy();
    }


}
