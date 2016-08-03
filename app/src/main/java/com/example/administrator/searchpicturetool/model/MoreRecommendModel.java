package com.example.administrator.searchpicturetool.model;

import android.content.Context;

import com.example.administrator.searchpicturetool.model.bean.NewRecommendContent;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/5/13 0013.
 */
public class MoreRecommendModel {
    public static Observable<List<NewRecommendContent>> getMoreRecommend(final Context app,String tip,float type){
        return  Observable.create(new Observable.OnSubscribe<List<NewRecommendContent>>() {
            @Override
            public void call(Subscriber<? super List<NewRecommendContent>> subscriber) {
                BmobQuery<NewRecommendContent> query = new BmobQuery<>();
                query.order("-createdAt");
                query.addWhereEqualTo("tip",tip);
                query.addWhereNotEqualTo("type", type);
                query.findObjects(app, new FindListener<NewRecommendContent>() {
                    @Override
                    public void onSuccess(List<NewRecommendContent> list) {
                        subscriber.onNext(list);
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(int i, String s) {
                        subscriber.onError(new Throwable(s+"i: "+i));
                        subscriber.onCompleted();
                    }
                });
            }
        });
    }
}
