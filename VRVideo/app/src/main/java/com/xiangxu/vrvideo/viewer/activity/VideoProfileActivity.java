package com.xiangxu.vrvideo.viewer.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.xiangxu.vrvideo.R;
import com.xiangxu.vrvideo.util.LLog;
import com.xiangxu.vrvideo.viewer.unity.UnityPlayerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoProfileActivity extends FullScreenActivity
    implements  View.OnClickListener {

    public static final String VIDEO_PROFILE_ID = "video_profile_id";

    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.play_fab) FloatingActionButton mPlayFab;
    @BindView(R.id.play_button)
    TextView mPlayButton;

    String mVideoID;

    public static Intent getStartIntent(Context context, String id) {
        Intent intent = new Intent(context, VideoProfileActivity.class);
        intent.putExtra(VIDEO_PROFILE_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_profile);
        ButterKnife.bind(this);

        // get intent extra
        mVideoID = getIntent().getStringExtra(VIDEO_PROFILE_ID);
        LLog.d(mVideoID);

        mCollapsingToolbarLayout.setTitle("无锡太湖");

        // toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoProfileActivity.this.onBackPressed();
                overridePendingTransition(R.anim.nothing, R.anim.slide_out_left);
            }
        });

        mPlayButton.setOnClickListener(this);
        mPlayFab.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_button:
            case R.id.play_fab:
                LLog.d("open unity");
                Intent intent = new Intent(this, UnityPlayerActivity.class);
                startActivity(intent);
                break;
        }
    }
}
