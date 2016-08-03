package com.example.administrator.searchpicturetool.view.viewHolder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.model.bean.NewRecommendContent;
import com.example.administrator.searchpicturetool.model.bean.RecommendTip;
import com.example.administrator.searchpicturetool.view.activity.MoreRecommendActivity;
import com.example.administrator.searchpicturetool.view.activity.SearchActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by wenhuaijun on 2016/2/6 0006.
 */
public class RecommendTipVewHolder extends BaseViewHolder<NewRecommendContent> implements View.OnClickListener {
    private TextView tip;
    private Button btn;
    NewRecommendContent data;
    public RecommendTipVewHolder(ViewGroup parent) {
        super(parent, R.layout.itemview_recommend_tip);
        tip =(TextView)itemView.findViewById(R.id.recommend_tip);
        btn = $(R.id.recommend_btn);
    }

    @Override
    public void setData(NewRecommendContent data) {
        super.setData(data);
        this.data=data;
        tip.setText(data.getTip());
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        /*if(data.getTip().equals("热门搜索")){
            return;
        }*/
       /* Bundle bundle = new Bundle();
        bundle.putString("search",data.getTip());*/
        Intent intent = new Intent();
        intent.putExtra("tip",data.getTip());
        intent.putExtra("type",data.getType());
     //   intent.putExtra("search", bundle);
        intent.setClass(getContext(), MoreRecommendActivity.class);
        getContext().startActivity(intent);
    }
}
