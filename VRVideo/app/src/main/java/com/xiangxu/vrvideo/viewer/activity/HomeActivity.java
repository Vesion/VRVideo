package com.xiangxu.vrvideo.viewer.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.xiangxu.vrvideo.R;
import com.xiangxu.vrvideo.viewer.fragment.ExploreFragment;
import com.xiangxu.vrvideo.viewer.fragment.HomeFragment;
import com.xiangxu.vrvideo.viewer.fragment.MineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    HomeFragment mHomeFragment;
    ExploreFragment mExploreFragment;
    MineFragment mMineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mHomeFragment = HomeFragment.newInstance();
        mExploreFragment = ExploreFragment.newInstance();
        mMineFragment = MineFragment.newInstance();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contentContainer, mHomeFragment).commit();

        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_home) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.contentContainer, mHomeFragment).commit();
                } else if (tabId == R.id.tab_explore) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.contentContainer, mExploreFragment).commit();
                } else if (tabId == R.id.tab_account) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.contentContainer, mMineFragment).commit();
                }
            }
        });
    }
}
