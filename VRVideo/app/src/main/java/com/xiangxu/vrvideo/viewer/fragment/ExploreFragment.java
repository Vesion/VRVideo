package com.xiangxu.vrvideo.viewer.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiangxu.vrvideo.R;
import com.xiangxu.vrvideo.model.VideoCategory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ExploreFragment extends Fragment {

    @BindView(R.id.tabs_explore) TabLayout mTabLayout;
    @BindView(R.id.container) ViewPager mViewPager;

    List<Fragment> mFragments;
    List<String> mTitles;

    public ExploreFragment() { }

    public static ExploreFragment newInstance() {
        ExploreFragment fragment = new ExploreFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mFragments == null) {
            mFragments = new ArrayList<>();
            mTitles = new ArrayList<>();
            // add fragments
            mFragments.add(VideoListFragment.newInstance(VideoCategory.recommend));
            mFragments.add(VideoListFragment.newInstance(VideoCategory.tourism));
            mFragments.add(VideoListFragment.newInstance(VideoCategory.realestate));
            mFragments.add(VideoListFragment.newInstance(VideoCategory.others));
            // add titles
            mTitles.add(getResources().getString(R.string.title1_tab_fragment_explore));
            mTitles.add(getResources().getString(R.string.title2_tab_fragment_explore));
            mTitles.add(getResources().getString(R.string.title3_tab_fragment_explore));
            mTitles.add(getResources().getString(R.string.title4_tab_fragment_explore));
        }
        FragmentManager fm = getChildFragmentManager();
        PagerAdapter pagerAdapter = new PagerAdapter(fm);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.addOnPageChangeListener(getPageChangeListener());

        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    private ViewPager.OnPageChangeListener getPageChangeListener() {
        return new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        };
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
