package com.example.administrator.searchpicturetool.presenter.activitPresenter;

import com.example.administrator.searchpicturetool.app.APP;
import com.example.administrator.searchpicturetool.model.MoreRecommendModel;
import com.example.administrator.searchpicturetool.model.bean.NewRecommendContent;
import com.example.administrator.searchpicturetool.view.activity.MoreRecommendActivity;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBasePresenter;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.beam.expansion.list.BeamListActivityPresenter;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2016/5/10 0010.
 */

public class MoreAcitivityPresenter extends BeamListActivityPresenter<MoreRecommendActivity,NewRecommendContent>{
    private String tip;
    private float type;
    @Override
    protected void onCreateView(MoreRecommendActivity view) {
        super.onCreateView(view);
        tip =getView().getIntent().getStringExtra("tip");
        type =getView().getIntent().getFloatExtra("type", 0);
        getView().getToolbar().setTitle(tip);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        MoreRecommendModel.getMoreRecommend(APP.getInstance(),tip,type)
                .subscribe(getRefreshSubscriber());
    }
}
