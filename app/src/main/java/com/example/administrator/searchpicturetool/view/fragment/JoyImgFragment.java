package com.example.administrator.searchpicturetool.view.fragment;

import android.view.ViewGroup;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.model.bean.ImageJoy;
import com.example.administrator.searchpicturetool.presenter.fragmentPresenter.JoyImgFragmentPresenter;
import com.example.administrator.searchpicturetool.view.viewHolder.JoyImageViewHolder;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListFragment;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by wenhuaijun on 2016/2/13 0013.
 */
@RequiresPresenter(JoyImgFragmentPresenter.class)
public class JoyImgFragment extends BeamListFragment<JoyImgFragmentPresenter,ImageJoy>{
    @Override
    protected ListConfig getConfig() {
        return super.getConfig()
                .setRefreshAble(true)
                .setNoMoreAble(true)
                .setLoadmoreAble(false)
                .setErrorAble(true)
                .setContainerErrorAble(true)
                .setContainerErrorRes(R.layout.view_net_error)
                .setContainerProgressRes(R.layout.page_progress)
                .setLoadMoreRes(R.layout.page_loadmore);
    }
    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new JoyImageViewHolder(parent);
    }
}
