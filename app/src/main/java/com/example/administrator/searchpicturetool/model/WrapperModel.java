package com.example.administrator.searchpicturetool.model;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;

import com.example.administrator.searchpicturetool.config.API;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by wenhuaijun on 2016/1/23 0023.
 */
public class WrapperModel {
    public static Observable<Integer> getSetWallWrapperObservable(Bitmap bitmap, final Context context){
        Observable<Bitmap> observable = Observable.just(bitmap);
        return observable.map(new Func1<Bitmap, Integer>() {
            @Override
            public Integer call(Bitmap bitmap) {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
                try {
                    wallpaperManager.setBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                    return API.status.error;
                }
                return API.status.success;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<Integer> getSetLockWrapperObservable(Bitmap bitmap, final Context context){
        Observable<Bitmap> observable = Observable.just(bitmap);
        return observable.map(new Func1<Bitmap, Integer>() {
            @Override
            public Integer call(Bitmap bitmap) {
                WallpaperManager mWallManager =WallpaperManager.getInstance(context);
                Class class1 =mWallManager.getClass();
                Method setWallPaperMethod = null;
                try {
                    setWallPaperMethod = class1.getMethod("setBitmapToLockWallpaper",Bitmap.class);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                    return API.status.error;
                }
                try {
                    setWallPaperMethod.invoke(mWallManager,bitmap);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return API.status.error;
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    return API.status.error;
                }
                return API.status.success;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
