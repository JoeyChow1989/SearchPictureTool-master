package com.example.administrator.searchpicturetool.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.model.bean.NetImage;
import com.example.administrator.searchpicturetool.presenter.fragmentPresenter.NetImgListPresenter;
import com.example.administrator.searchpicturetool.view.viewHolder.NetImageListViewHolder;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListFragment;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

@RequiresPresenter(NetImgListPresenter.class)
public class NetImgFragment extends BeamListFragment<NetImgListPresenter,NetImage> {

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }

    @Override
        protected ListConfig getConfig() {
            return super.getConfig()
                    .setRefreshAble(true)
                    .setNoMoreAble(true)
                    .setLoadmoreAble(true)
                    .setErrorAble(true)
                    .setContainerErrorAble(true)
                    .setContainerErrorRes(R.layout.view_net_error)
                    .setContainerProgressRes(R.layout.page_progress)
                    .setLoadMoreRes(R.layout.page_loadmore);
        }
    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {

        return new NetImageListViewHolder(parent);
    }


    }
