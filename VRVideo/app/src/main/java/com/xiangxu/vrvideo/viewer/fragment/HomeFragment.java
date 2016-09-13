package com.xiangxu.vrvideo.viewer.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.xiangxu.vrvideo.R;
import com.xiangxu.vrvideo.viewer.activity.SearchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends SwipeRefreshFragment {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.roll_view_pager) RollPagerView mRollPagerView;

    public HomeFragment() {}

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        // toolbar
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        setHasOptionsMenu(true);
        mToolbar.setTitle(R.string.title_fragment_home);

        // search view
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_search) {
                    // open search activity
                    startActivity(new Intent(getContext(), SearchActivity.class));
                    getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.nothing);
                }
                return true;
            }
        });

        // roll view pager
        mRollPagerView.setPlayDelay(4000);
        mRollPagerView.setAnimationDurtion(600);
        mRollPagerView.setHintView(new ColorPointHintView(getContext(), Color.WHITE, Color.LTGRAY));
        mRollPagerView.setAdapter(new RollPagerAdapter(mRollPagerView));

        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_home, menu);
    }

    class RollPagerAdapter extends LoopPagerAdapter {
        private int[] imgs = {
                R.drawable.demo_video_preview,
                R.drawable.demo_video_preview,
                R.drawable.demo_video_preview,
                R.drawable.demo_video_preview
        };

        public RollPagerAdapter(RollPagerView viewPager) {
            super(viewPager);
        }

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }


        @Override
        public int getRealCount() {
            return imgs.length;
        }
    }
}
