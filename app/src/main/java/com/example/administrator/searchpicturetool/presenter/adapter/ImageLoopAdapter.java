package com.example.administrator.searchpicturetool.presenter.adapter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.model.bean.Banner;
import com.example.administrator.searchpicturetool.model.bean.NewBanner;
import com.example.administrator.searchpicturetool.view.activity.SearchActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.DynamicPagerAdapter;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.utils.JUtils;

import java.util.List;

/**
 * Created by wenhuaijun on 2016/2/6 0006.
 */
public class ImageLoopAdapter extends DynamicPagerAdapter{
    private List<NewBanner> banners;
    public ImageLoopAdapter() {

    }

    @Override
    public View getView(final ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.view_rollviewpager_, null);
        SimpleDraweeView mSimpleDraweeView =(SimpleDraweeView)view.findViewById(R.id.viewPager_img);
        mSimpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("search",banners.get(position).getSearchTip());
                bundle.putString("imagUrl",banners.get(position).getImageUrl());
                Intent intent = new Intent();
                intent.putExtra("search", bundle);
                intent.setClass(container.getContext(), SearchActivity.class);
                container.getContext().startActivity(intent);
            }
        });
        mSimpleDraweeView.setImageURI(Uri.parse(banners.get(position).getImageUrl()));
        return view;
    }


    @Override
    public int getCount() {
        if(banners!=null){
            return banners.size();
        }else{
            return 0;
        }

    }

    public void setBanners(List<NewBanner> banners) {
        this.banners = banners;
    }

}
