package com.example.administrator.searchpicturetool.view.viewHolder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.model.bean.NewRecommendContent;
import com.example.administrator.searchpicturetool.model.bean.RecommendContent;
import com.example.administrator.searchpicturetool.view.activity.SearchActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Created by wenhuaijun on 2016/2/7 0007.
 */
public class RecommendContentViewHolder extends BaseViewHolder<NewRecommendContent> implements View.OnClickListener {
    private SimpleDraweeView simpleDraweeView1;
    private CardView cardView1;
    private TextView title1;
    private TextView content1;
    NewRecommendContent recommendContent;
    public RecommendContentViewHolder(ViewGroup parent) {
        super(parent, R.layout.itemview_newrecommend);
        simpleDraweeView1=$(R.id.recomend_img1);
        title1 =$(R.id.recommend_title1);
        content1 =$(R.id.recommend_content1);
        cardView1 =$(R.id.recommend_cardview1);
    }

    @Override
    public void setData(NewRecommendContent data) {

        super.setData(data);
            recommendContent = data;
            simpleDraweeView1.setImageURI(Uri.parse(recommendContent.getImageUrl()));
            title1.setText(recommendContent.getTitle());
            content1.setText(recommendContent.getContent());
            cardView1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()){
            case R.id.recommend_cardview1:
                bundle.putString("search",recommendContent.getTitle());
                bundle.putString("imagUrl",recommendContent.getImageUrl());
                break;
        }
        Intent intent = new Intent();
        intent.putExtra("search", bundle);
        intent.setClass(getContext(), SearchActivity.class);
        getContext().startActivity(intent);
    }
}
