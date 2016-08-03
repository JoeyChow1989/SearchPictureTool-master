package com.example.administrator.searchpicturetool.model;

import com.example.administrator.searchpicturetool.model.bean.NetImage;

/**
 * Created by wenhuaijun on 2015/11/2 0002.
 */
@Deprecated
public class NetImgModel {
    /*public static void getImageList(String word,final int page, final NetImageListCallback callback){
        final SosoSearcher searcher = new SosoSearcher();
        RequestManager.getInstance().get(searcher.getUrl(word, page), searcher.getHeader(), new RequestListener() {
            @Override
            public void onRequest() {

            }

            @Override
            public void onSuccess(String response) {
                    NetImage[] imgs = searcher.getImageList(response.trim());
                    callback.onSuccess(imgs);
            }

            @Override
            public void onError(String errorMsg) {
                    callback.onError(errorMsg);
            }
        },true);
    }*/
    public interface NetImageListCallback{
        public void onSuccess( NetImage[] imgs);
        public void onError(String error);
    }
}
