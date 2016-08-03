package com.example.administrator.searchpicturetool.presenter.activitPresenter;

import com.example.administrator.searchpicturetool.R;
import com.example.administrator.searchpicturetool.view.activity.SearchActivity;
import com.jude.beam.bijection.Presenter;

import java.util.Random;

/**
 * Created by wenhuaijun on 2015/11/3 0003.
 */
public class SearchActivityPresenter extends Presenter<SearchActivity>{

    private int[] bgImgs ={
            R.drawable.bg_1,
            R.drawable.bg_2,
            R.drawable.bg_3,
            R.drawable.bg_4,
            R.drawable.bg_5,
            R.drawable.bg_6,
            R.drawable.bg_7,
            R.drawable.bg_8,
            R.drawable.bg_9,
    };
    public int getBgImg(){
        return bgImgs[new Random().nextInt(bgImgs.length)];
    }

    public void gotoUp(int position){
        getView().getSearchFragment().getListView().scrollToPosition(position);
    }
}
