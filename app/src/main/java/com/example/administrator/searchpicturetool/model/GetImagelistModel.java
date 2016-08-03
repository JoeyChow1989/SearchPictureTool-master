package com.example.administrator.searchpicturetool.model;

import com.example.administrator.searchpicturetool.model.bean.NetImage;
import com.example.administrator.searchpicturetool.model.service.ServiceCilent;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by wenhuaijun on 2016/1/25 0025.
 */
public class GetImagelistModel {
    public static Observable<NetImage[]> getImageList( final String word,final int page){
        Observable<NetImage[]> observable =ServiceCilent.getService()
                .getImageList("ajax", "result", word, page*48)
                .map(new Func1<SosoSearcher.SosoImage.WallImageResult, NetImage[]>() {
                    @Override
                    public NetImage[] call(SosoSearcher.SosoImage.WallImageResult wallImageResult) {

                        return wallImageResult.getData();
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return observable;
    }
}
