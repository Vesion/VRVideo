package com.xiangxu.vrvideo.viewer.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xiangxu.vrvideo.R;
import com.xiangxu.vrvideo.viewer.fragment.LoginFragment;
import com.xiangxu.vrvideo.viewer.fragment.RegisterFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignInUpActivityActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.tabs) TabLayout mTabLayout;
    @BindView(R.id.container) ViewPager mViewPager;

    List<Fragment> mFragments;
    List<String> mTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_up_activity);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.title_activity_sign);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInUpActivityActivity.this.onBackPressed();
                overridePendingTransition(R.anim.nothing, R.anim.slide_out_left);
            }
        });

        if (mFragments == null) {
            mFragments = new ArrayList<>();
            mTitles = new ArrayList<>();
            // add fragments
            mFragments.add(LoginFragment.newInstance());
            mFragments.add(RegisterFragment.newInstance());
            // add titles
            mTitles.add(getResources().getString(R.string.title_fragment_login));
            mTitles.add(getResources().getString(R.string.title_fragment_register));
        }
        FragmentManager fm = getSupportFragmentManager();
        PagerAdapter pagerAdapter = new PagerAdapter(fm);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }

    }
}
