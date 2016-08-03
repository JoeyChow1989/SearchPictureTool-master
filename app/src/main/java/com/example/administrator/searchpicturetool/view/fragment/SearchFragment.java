package com.example.administrator.searchpicturetool.view.fragment;

import android.view.ViewGroup;
import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.config.Constant;
import com.example.administrator.searchpicturetool.model.bean.NetImage;
import com.example.administrator.searchpicturetool.presenter.fragmentPresenter.SerachFragmentListPresenter;
import com.example.administrator.searchpicturetool.view.viewHolder.NetImageListViewHolder;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListFragment;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by wenhuaijun on 2015/11/3 0003.
 */
@RequiresPresenter(SerachFragmentListPresenter.class)
public class SearchFragment extends BeamListFragment<SerachFragmentListPresenter,NetImage> {

    @Override
    protected ListConfig getConfig() {
        return Constant.getloadMoreConfig().setErrorRes(R.layout.view_net_error2);
    }
    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new NetImageListViewHolder(parent);
    }
}
