package com.example.administrator.searchpicturetool.config;

import com.example.administrator.searchpicturetool.R;
import com.jude.beam.expansion.list.ListConfig;

/**
 * Created by Wenhuaijun on 2016/5/13 0013.
 */
public class Constant {
    public static ListConfig getUnloadMoreConfig(){
        ListConfig listConfig =new ListConfig();
        return  listConfig.setRefreshAble(true)
                .setNoMoreAble(false)
                .setLoadmoreAble(false)
                .setErrorAble(true)
                .setContainerErrorAble(true)
                .setContainerErrorRes(R.layout.view_net_error)
                .setContainerProgressRes(R.layout.page_progress)
                .setLoadMoreRes(R.layout.page_loadmore);
    } public static ListConfig getloadMoreConfig(){
        ListConfig listConfig =new ListConfig();
        return  listConfig.setRefreshAble(true)
                .setNoMoreAble(true)
                .setLoadmoreAble(true)
                .setErrorAble(true)
                .setContainerErrorAble(true)
                .setContainerErrorRes(R.layout.view_net_error)
                .setContainerProgressRes(R.layout.page_progress)
                .setLoadMoreRes(R.layout.page_loadmore);
    }
}
