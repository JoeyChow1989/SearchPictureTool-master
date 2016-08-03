package com.example.administrator.searchpicturetool.model.service;

import com.example.administrator.searchpicturetool.config.API;
import com.example.administrator.searchpicturetool.model.SosoSearcher;
import com.example.administrator.searchpicturetool.model.bean.ImageJoy;
import com.example.administrator.searchpicturetool.model.bean.ImageJoyResult;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wenhuaijun on 2016/1/25 0025.
 * 服务器接口
 */

public interface Service {
    @GET("http://pic.sogou.com/pics")
    Observable<SosoSearcher.SosoImage.WallImageResult> getImageList(

            @Query("reqType") String ajax,
            @Query("reqFrom") String result,
            @Query("query") String word,
            @Query("start" ) int page);

    @GET(API.LAIFU_JOY_IMAGE)
    Observable<ImageJoy[]> getImageJoy();

}
