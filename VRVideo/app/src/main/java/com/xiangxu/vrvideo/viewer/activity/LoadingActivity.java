package com.xiangxu.vrvideo.viewer.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;

import com.xiangxu.vrvideo.R;
import com.xiangxu.vrvideo.common.Constants;
import com.xiangxu.vrvideo.util.SharedUtil;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class LoadingActivity extends FullScreenActivity {

    final int DURATION = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // launch home activity after 2s
        Observable.timer(DURATION, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Intent intent= new Intent(LoadingActivity.this, HomeActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fade_in, R.anim.nothing);
                        finish();
                    }
                });
    }
}
