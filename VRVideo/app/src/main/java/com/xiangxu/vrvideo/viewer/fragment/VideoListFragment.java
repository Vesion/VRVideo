package com.xiangxu.vrvideo.viewer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiangxu.vrvideo.R;
import com.xiangxu.vrvideo.model.entity.Video;
import com.xiangxu.vrvideo.model.entity.VideoCategory;
import com.xiangxu.vrvideo.presenter.VideoListPresenter;
import com.xiangxu.vrvideo.viewer.activity.VideoProfileActivity;
import com.xiangxu.vrvideo.viewer.viewinterface.VideoListViewInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VideoListFragment extends SwipeRefreshFragment implements VideoListViewInterface {

    private static final String VIDEO_TYPE = "TopUserType";

    @BindView(R.id.video_list_view) RecyclerView mVideoListView;

    VideoCategory mVideoCategory;
    VideoListPresenter mVideoListPresenter;
    Adapter mAdapter;
    onVideoItemClickListener onVideoItemClickListener;

    public VideoListFragment() { }

    public static VideoListFragment newInstance(VideoCategory type) {
        VideoListFragment fragment = new VideoListFragment();
        Bundle args = new Bundle();
        args.putString(VIDEO_TYPE, type.name());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mVideoCategory = VideoCategory.valueOf(getArguments().getString(VIDEO_TYPE));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // setup presenter to load data
        mVideoListPresenter = new VideoListPresenter(this);
        mVideoListPresenter.create();

        // setup adapter
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mAdapter = new Adapter(mVideoListPresenter.getVideos());
        mVideoListView.setLayoutManager(layoutManager);
        mVideoListView.setAdapter(mAdapter);
        onVideoItemClickListener = new onVideoItemClickListener() {
            @Override
            public void onVideoItemSelected(String videoId) {
                startActivity(VideoProfileActivity.getStartIntent(getActivity(), videoId));
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.nothing);
            }
        };

        // recyclerview drag up add-on
        mVideoListView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;
            int totalItemCount;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == totalItemCount) {
                    dragUpToLoad();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                totalItemCount = layoutManager.getItemCount();
            }
        });

        // load data when coming in
        pullDownToRefresh();
    }

    @Override
    public void pullDownToRefresh() {
        // load data
        mVideoListPresenter.loadData(mVideoCategory.toString().toLowerCase());
    }

    public void dragUpToLoad() {
        mVideoListPresenter.loadData(mVideoCategory.toString().toLowerCase());
    }

    // called by presenter
    @Override
    public void updateVideoListView() {
        mAdapter.notifyDataSetChanged();
    }


    public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        List<Video> videoList;

        public Adapter(List<Video> videoList) {
            this.videoList = videoList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_video_item_card, parent, false);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (onVideoItemClickListener != null) {
                        onVideoItemClickListener.onVideoItemSelected(videoList.get((Integer) v.getTag()).getId());
                    }
                }
            });
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.itemView.setTag(position);
            Video video = videoList.get(position);

            // fill video model into view holder
            holder.mTitle.setText(video.getName());
            // use glide to load image
            //
            holder.mDescription.setText(video.getDescription());
            holder.mFavors.setText(String.format(getResources().getString(R.string.favor_video_list_item), video.getFavors()));
            holder.mComments.setText(String.format(getResources().getString(R.string.comment_video_list_item), video.getComments()));

        }

        @Override
        public int getItemCount() {
            return videoList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.preview_video_list_item) ImageView mImageView;
            @BindView(R.id.title_video_list_item) TextView mTitle;
            @BindView(R.id.description_video_list_item) TextView mDescription;
            @BindView(R.id.favors_video_list_item) TextView mFavors;
            @BindView(R.id.comments_video_list_item) TextView mComments;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

    public interface onVideoItemClickListener {
        void onVideoItemSelected(String videoId);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mVideoListPresenter.destroy();
    }
}
