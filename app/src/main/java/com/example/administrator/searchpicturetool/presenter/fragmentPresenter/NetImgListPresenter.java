package com.example.administrator.searchpicturetool.presenter.fragmentPresenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.model.GetImagelistModel;
import com.example.administrator.searchpicturetool.model.bean.NetImage;
import com.example.administrator.searchpicturetool.view.activity.ShowLargeImgActivity;
import com.example.administrator.searchpicturetool.view.fragment.NetImgFragment;
import com.jude.beam.expansion.list.BeamListFragmentPresenter;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.functions.Action1;

/**
 * Created by wenhuaijun on 2015/11/2 0002.
 */

public class NetImgListPresenter extends BeamListFragmentPresenter<NetImgFragment,NetImage> implements RecyclerArrayAdapter.OnItemClickListener{
    private String tab;
    private ArrayList<NetImage> netImages;

    @Override
    protected void onCreate(@NonNull NetImgFragment view, Bundle savedState) {
        super.onCreate(view, savedState);
        tab =view.getResources().getStringArray(R.array.search_tab)[view.getArguments().getInt("tab")];
        onRefresh();
        getAdapter().setOnItemClickListener(NetImgListPresenter.this);
    }

    @Override
    protected void onCreateView(NetImgFragment view) {
        super.onCreateView(view);
        view.getListView().setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        GetImagelistModel.getImageList(tab,0)
                .map(Arrays::asList)
                .doOnNext(new Action1<List<NetImage>>() {
                    @Override
                    public void call(List<NetImage> netImages) {
                        NetImgListPresenter.this.netImages = new ArrayList<NetImage>(netImages);
                    }
                })
                .unsafeSubscribe(getRefreshSubscriber());
    }
    @Override
    public void onLoadMore() {
        super.onLoadMore();
        GetImagelistModel.getImageList(tab, getCurPage())
                .map(Arrays::asList)
                .doOnNext(new Action1<List<NetImage>>() {
                    @Override
                    public void call(List<NetImage> netImages) {
                        NetImgListPresenter.this.netImages.addAll(netImages);
                    }
                })
                .unsafeSubscribe(getMoreSubscriber());
    }
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent();
        intent.putExtra("position", position);
        intent.putExtra("netImages", netImages);
        intent.setClass(getView().getContext(), ShowLargeImgActivity.class);
        getView().getActivity().startActivityForResult(intent, 100);
    }

}
