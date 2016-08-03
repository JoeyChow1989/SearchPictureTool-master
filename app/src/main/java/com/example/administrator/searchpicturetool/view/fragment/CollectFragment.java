package com.example.administrator.searchpicturetool.view.fragment;

import android.view.ViewGroup;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.config.Constant;
import com.example.administrator.searchpicturetool.model.bean.NetImage;
import com.example.administrator.searchpicturetool.presenter.fragmentPresenter.CollectListPresenter;
import com.example.administrator.searchpicturetool.view.viewHolder.CollectImageListViewHolder;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListFragment;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.utils.JUtils;

/**
 * Created by wenhuaijun on 2015/11/12 0012.
 */
@RequiresPresenter(CollectListPresenter.class)
public class CollectFragment extends BeamListFragment<CollectListPresenter,NetImage>{
    @Override
    protected ListConfig getConfig() {
        return Constant.getUnloadMoreConfig();
    }

    @Override
    public void showError(Throwable e) {
        super.showError(e);
        JUtils.Log(e.getLocalizedMessage());
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new CollectImageListViewHolder(parent);
    }
}
