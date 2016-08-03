package com.example.administrator.searchpicturetool.presenter.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.view.ViewGroup;

import com.example.administrator.searchpicturetool.model.bean.NewRecommendContent;
import com.example.administrator.searchpicturetool.model.bean.RecommendContent;
import com.example.administrator.searchpicturetool.model.bean.RecommendTip;
import com.example.administrator.searchpicturetool.view.viewHolder.RecommendContentViewHolder;
import com.example.administrator.searchpicturetool.view.viewHolder.RecommendTipVewHolder;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by wenhuaijun on 2016/2/7 0007.
 */
public class RecommendAdapter extends RecyclerArrayAdapter<NewRecommendContent>{
    private int tip=0;
    private int content =1;
    public RecommendAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==tip){
            return   new RecommendTipVewHolder(parent);
        }else if(viewType==content){
            return new RecommendContentViewHolder(parent);
        }
        return  null;
    }


    @Override
    public void OnBindViewHolder(BaseViewHolder holder, int position) {
        super.OnBindViewHolder(holder, position);
    }

    @Override
    public int getViewType(int position) {

        if(getItem(position).isJustType()){
            return tip;
        }else{
            return content;
        }
        /* if(getItem(position) instanceof RecommendContent){
            return  content;
        } else if(getItem(position) instanceof RecommendTip) return  tip;
       *//* if(position==0){

        }
        return  content;*//*
        return  -1;
    }*/
    }

    public class  TipSpanSizeLookUp extends GridSpanSizeLookup{

        public TipSpanSizeLookUp() {
            //列数默认为2
            super(2);
        }

        @Override
        public int getSpanSize(int position) {
            if (position < getHeaderCount()||position>=getCount()+getHeaderCount()) {
                return 2;
            }else{
                if (getItem(position-1).isJustType()) {
                    //该Tip item占2格
                    return 2;
                } else {
                    //默认该Content item占1格
                    return 1;
                }
            }
        }
    }
    public TipSpanSizeLookUp obtainTipSpanSizeLookUp(){
        return new TipSpanSizeLookUp();
    }


}
