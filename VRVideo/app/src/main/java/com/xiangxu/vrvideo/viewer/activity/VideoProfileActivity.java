package com.xiangxu.vrvideo.viewer.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xiangxu.vrvideo.R;
import com.xiangxu.vrvideo.util.LLog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoProfileActivity extends AppCompatActivity {

    public static final String VIDEO_PROFILE_ID = "video_profile_id";

    @BindView(R.id.toolbar) Toolbar mToolbar;

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

        setSupportActionBar(mToolbar);
        mToolbar.setTitle(getTitle());
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoProfileActivity.this.onBackPressed();
                overridePendingTransition(R.anim.nothing, R.anim.slide_out_left);
            }
        });

        mVideoID = getIntent().getStringExtra(VIDEO_PROFILE_ID);
        LLog.d(mVideoID);
    }
}
