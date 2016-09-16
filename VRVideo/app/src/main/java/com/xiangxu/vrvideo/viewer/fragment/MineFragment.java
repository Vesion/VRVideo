package com.xiangxu.vrvideo.viewer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiangxu.vrvideo.R;
import com.xiangxu.vrvideo.common.Constants;
import com.xiangxu.vrvideo.util.LLog;
import com.xiangxu.vrvideo.util.SharedUtil;
import com.xiangxu.vrvideo.viewer.activity.SignInUpActivityActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineFragment extends Fragment
    implements View.OnClickListener {

    @BindView(R.id.toolbar) Toolbar mToolbar;

    @BindView(R.id.user_account_layout) LinearLayout mUserAccountLayout;
    @BindView(R.id.user_avatar_img) ImageView mUserAvatar;
    @BindView(R.id.user_name_text) TextView mUserNameText;
    @BindView(R.id.user_phone_text) TextView mUserPhoneText;
    @BindView(R.id.user_account_enter) ImageView mUserAccountEnter;

    @BindView(R.id.places_item) TextView mPlacesItem;
    @BindView(R.id.collects_item) TextView mCollectsItem;
    @BindView(R.id.settings_item) TextView mSettingsItem;
    @BindView(R.id.about_item) TextView mAboutItem;
    @BindView(R.id.feedback_item) TextView mFeedbackItem;
    @BindView(R.id.commend_item) TextView mCommendItem;
    @BindView(R.id.share_item) TextView mShareItem;

    boolean mHasLogged;

    public MineFragment() {
        // Required empty public constructor
    }

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);

        // toolbar
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_fragment_mine);

        mHasLogged = SharedUtil.getBoolean(getActivity(), Constants.HAS_LOGGED);
        if (mHasLogged) {

        } else {
            mUserAvatar.setImageResource(R.drawable.default_avatar_l);
            mUserNameText.setText(R.string.name_no_logged_fragment_mine);
            mUserPhoneText.setVisibility(View.INVISIBLE);
        }

        // set up listeners
        mUserAccountLayout.setOnClickListener(this);
        mUserAvatar.setOnClickListener(this);
        mPlacesItem.setOnClickListener(this);
        mCollectsItem.setOnClickListener(this);
        mSettingsItem.setOnClickListener(this);
        mAboutItem.setOnClickListener(this);
        mFeedbackItem.setOnClickListener(this);
        mCommendItem.setOnClickListener(this);
        mShareItem.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_avatar_img:
                break;
            case R.id.user_account_layout:
                if (mHasLogged) {

                } else {
                    Intent intent = new Intent(getActivity(), SignInUpActivityActivity.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.nothing);
                }
                break;
            case R.id.places_item:

                break;
            case R.id.collects_item:

                break;
            case R.id.settings_item:

                break;
            case R.id.about_item:

                break;
            case R.id.feedback_item:

                break;
            case R.id.commend_item:

                break;
            case R.id.share_item:

                break;
        }
    }
}
