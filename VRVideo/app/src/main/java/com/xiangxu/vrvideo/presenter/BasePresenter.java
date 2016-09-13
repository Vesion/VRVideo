package com.xiangxu.vrvideo.presenter;

import android.content.Context;

import com.xiangxu.vrvideo.viewer.viewinterface.BaseViewInterface;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by xuxiang on 2016/9/7.
 */

public abstract class BasePresenter<T extends BaseViewInterface> {
    protected T mViewInterface;
    protected Context mContext;

    protected CompositeSubscription mCompositeSubscription;

    public BasePresenter(T viewInterface) {
        this.mViewInterface = viewInterface;
        mContext = viewInterface.getContext();
        mCompositeSubscription = new CompositeSubscription();
    }

    public abstract void create();

    public abstract void destroy();

    public T getAttachedView() {
        return mViewInterface;
    }


}
