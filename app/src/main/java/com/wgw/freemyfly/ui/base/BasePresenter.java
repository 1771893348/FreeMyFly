package com.wgw.freemyfly.ui.base;

/**
 * Created by wgw on 2018/5/9.
 */

public abstract class BasePresenter<V> {
    protected abstract void attachView(V v);
}
