package com.example.administrator.searchpicturetool.presenter.fragmentPresenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.administrator.searchpicturetool.model.GetImagelistModel;
import com.example.administrator.searchpicturetool.model.bean.NetImage;
import com.example.administrator.searchpicturetool.presenter.activitPresenter.SearchActivityPresenter;
import com.example.administrator.searchpicturetool.view.fragment.SearchFragment;
import com.example.administrator.searchpicturetool.view.activity.ShowLargeImgActivity;
import com.jude.beam.expansion.list.BeamListFragmentPresenter;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.utils.JUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by wenhuaijun on 2015/11/3 0003.
 */
public class SerachFragmentListPresenter extends BeamListFragmentPresenter<SearchFragment,NetImage> implements RecyclerArrayAdapter.OnItemClickListener{
    private String tab;
    private ArrayList<NetImage> netImages;

    @Override
    protected void onCreate(SearchFragment view, Bundle savedState) {
        super.onCreate(view, savedState);
        getAdapter().setOnItemClickListener(SerachFragmentListPresenter.this);
    }

    @Override
    protected void onCreateView(SearchFragment view) {
        super.onCreateView(view);
        view.getListView().getRecyclerView().setHasFixedSize(false);
        view.getListView().setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        tab =view.getArguments().getString("search");
        netImages = new ArrayList<NetImage>();
        onRefresh();

    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        GetImagelistModel.getImageList(tab,0)
                .map(Arrays::asList)
                .doOnNext(new Action1<List<NetImage>>() {
                    @Override
                    public void call(List<NetImage> netImages) {
                        SerachFragmentListPresenter.this.netImages = new ArrayList<NetImage>(netImages);
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
                        SerachFragmentListPresenter.this.netImages.addAll(netImages);
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
        getView().getActivity().startActivityForResult(intent,100);
    }
}
