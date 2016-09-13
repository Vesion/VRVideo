package com.xiangxu.vrvideo.presenter;

import com.xiangxu.vrvideo.model.Video;
import com.xiangxu.vrvideo.viewer.viewinterface.VideoListViewInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuxiang on 2016/9/7.
 */

public class VideoListPresenter extends BasePresenter<VideoListViewInterface> {

    private List<Video> mVideos;

    public VideoListPresenter(VideoListViewInterface viewInterface) {
        super(viewInterface);
    }

    @Override
    public void create() {
        mVideos = new ArrayList<>();
    }

    @Override
    public void destroy() {
        mCompositeSubscription.clear();
    }

    public List<Video> getVideos() {
        return mVideos;
    }

    public void loadData(String category) {

        // load data from server

        Video v = new Video();
        v.setId("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        v.setName("无锡太湖");
        v.setDescription("太湖位于长江三角洲的南缘，古称震泽、具区，又名五湖、笠泽，是中国五大淡水湖之一。");
        v.setFavors(12);
        v.setComments(190);

        mVideos.add(v);
        mVideos.add(v);
        mVideos.add(v);

        // call viewer to update UI
        mViewInterface.updateVidelListView();
    }
}
