package com.xiangxu.vrvideo.viewer.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.xiangxu.vrvideo.R;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MineFragment extends Fragment
    implements View.OnClickListener {

    @BindView(R.id.toolbar) Toolbar mToolbar;

    @BindView(R.id.user_avatar_img) ImageView mUserAvatar;
    @BindView(R.id.user_account_text) LinearLayout mUserAccountText;
    @BindView(R.id.user_account_enter) LinearLayout mUserAccountEnter;


    @BindView(R.id.places_item) LinearLayout mPlacesItem;
    @BindView(R.id.favors_item) LinearLayout mFavorsItem;
    @BindView(R.id.settings_item) LinearLayout mSettingsItem;
    @BindView(R.id.about_item) LinearLayout mAboutItem;
    @BindView(R.id.feedback_item) LinearLayout mFeedbackItem;
    @BindView(R.id.commend_item) LinearLayout mCommendItem;
    @BindView(R.id.share_item) LinearLayout mShareItem;

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
        setHasOptionsMenu(true);
        mToolbar.setTitle(R.string.title_fragment_mine);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_avatar_img:

                break;
            case R.id.user_account_text:
            case R.id.user_account_enter:

                break;
            case R.id.places_item:

                break;
            case R.id.favors_item:

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
