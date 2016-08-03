package com.example.administrator.searchpicturetool.view.fragment;

import android.view.ViewGroup;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.config.Constant;
import com.example.administrator.searchpicturetool.model.bean.DownloadImg;
import com.example.administrator.searchpicturetool.presenter.fragmentPresenter.DownloadListPresenter;
import com.example.administrator.searchpicturetool.view.viewHolder.DownloadImgViewHolder;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListFragment;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by wenhuaijun on 2015/11/1 0001.
 */
@RequiresPresenter(DownloadListPresenter.class)
public class DownloadFragment extends BeamListFragment<DownloadListPresenter,DownloadImg>{
    @Override
    protected ListConfig getConfig() {
        return Constant.getUnloadMoreConfig();
    }
    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new DownloadImgViewHolder(parent);
    }
}
