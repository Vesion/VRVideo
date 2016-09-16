package com.xiangxu.vrvideo.viewer.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.xiangxu.vrvideo.R;
import com.xiangxu.vrvideo.util.LLog;
import com.xiangxu.vrvideo.viewer.activity.SearchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeFragment extends SwipeRefreshFragment {

    @BindView(R.id.header) LinearLayout mHeaderView;
    @BindView(R.id.address_text) TextView mAddressText;
    @BindView(R.id.search_edit) EditText mSearchEdit;
    @BindView(R.id.scrollview) NestedScrollView mScrollView;
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

        // search edit
        mSearchEdit.setFocusable(false);
        mSearchEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SearchActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.nothing);
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // fade header when scrolling
        mHeaderView.getBackground().setAlpha(0);
        final int pHeight = getResources().getDimensionPixelOffset(R.dimen.roll_height_fragment_home);
        mScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY <= pHeight) {
                    float p = (float)scrollY / pHeight;
                    LLog.d(p);
                    mHeaderView.getBackground().setAlpha((int)(255*p));
                } else {
                    mHeaderView.getBackground().setAlpha(255);
                }
            }
        });
    }

    class RollPagerAdapter extends LoopPagerAdapter {
        private int[] imgs = {
                R.drawable.demo_video_profile_xh,
                R.drawable.demo_video_profile_xh,
                R.drawable.demo_video_profile_xh,
                R.drawable.demo_video_profile_xh
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
