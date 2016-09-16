package com.xiangxu.vrvideo.viewer.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.xiangxu.vrvideo.R;
import com.xiangxu.vrvideo.util.LLog;

import butterknife.BindView;

public class SwipeRefreshFragment extends Fragment {

    public @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout mSwipeRefreshLayout;

    public SwipeRefreshFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.refresh_progress_2,
                R.color.refresh_progress_1,
                R.color.refresh_progress_3);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullDownToRefresh();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    public void pullDownToRefresh() {

    }
}
