package com.example.administrator.searchpicturetool.model.service;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
@Deprecated
/**
 * 对服务器请求的Observer的修改
 */
public class DefaultTransform<T> implements Observable.Transformer<T, T> {
    @Override
    public Observable<T> call(Observable<T> tObservable) {
        return tObservable.delay(2, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread());
    }
}
