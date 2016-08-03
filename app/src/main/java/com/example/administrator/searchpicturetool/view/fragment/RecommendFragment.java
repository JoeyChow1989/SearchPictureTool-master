package com.example.administrator.searchpicturetool.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.presenter.fragmentPresenter.RecommendFragmentPresenter;
import com.jude.beam.bijection.BeamFragment;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.easyrecyclerview.EasyRecyclerView;


/**
 * Created by wenhuaijun on 2016/2/5 0005.
 */

@RequiresPresenter(RecommendFragmentPresenter.class)
public class RecommendFragment extends BeamFragment<RecommendFragmentPresenter>{
    public EasyRecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_main,container,false);
        recyclerView = (EasyRecyclerView)view.findViewById(R.id.easy_recyclerview);
        recyclerView.setErrorView(R.layout.view_net_error);
    //    recyclerView.setRefreshing(true);
        return view;
    }
}
