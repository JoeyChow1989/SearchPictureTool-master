package com.example.administrator.searchpicturetool.model;

import android.content.Context;

import com.example.administrator.searchpicturetool.config.API;
import com.example.administrator.searchpicturetool.db.DBManager;
import com.example.administrator.searchpicturetool.model.bean.DownloadImg;
import com.example.administrator.searchpicturetool.model.bean.NetImage;

import java.util.ArrayList;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by wenhuaijun on 2016/1/31 0031.
 */
public class SqlModel {
    //存下载图片信息到数据库
    public static void addDownloadImg(Context context,NetImage netImage,String fileName){
       // DBManager manager = new DBManager(context);
        DownloadImg hasDownloadImg= new DownloadImg(fileName,netImage.getLargeImg(),netImage.getHeight(),netImage.getWidth());
        DBManager.getInstance(context).addHasDownload(hasDownloadImg);
    }
    //从download数据库中删除一个图片信息
    public static void deleteDownloadImgByname(Context context,String name){
       // DBManager manager = new DBManager(context);
        DBManager.getInstance(context).deleteHasDownload(name);
    }
    //批量删除选中的已下载图片
    public static Observable<String> deleteDownloadImgs(final Context context, final ArrayList<DownloadImg> imgs){
        Observable<String> observable = Observable.just("")
                .map(new Func1<String,String>() {
                    @Override
                    public String call(String s) {
                      //  DBManager manager = new DBManager(context);
                        DBManager.getInstance(context).deleteDownloadPictures(imgs);
                        return API.status.success+"";
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    //存收藏图片信息到数据库
    public static void addCollectImg(Context context,NetImage netImage){
       // DBManager manager = new DBManager(context);
        DBManager.getInstance(context).addHasCollect(netImage);
    }
    //从collect数据库删除一张图片信息
    public static void deleteCollectImgByUrl(Context context,String largeUrl){
        //DBManager manager = new DBManager(context);
        DBManager.getInstance(context).deleteHasCollect(largeUrl);
    }
    //批量删除选中的已下载图片
    public static Observable<String> deleteCollectImgs(final Context context, final ArrayList<NetImage> imgs){
        Observable<String> observable = Observable.just("")
                .map(new Func1<String,String>() {
                    @Override
                    public String call(String s) {
                       // DBManager manager = new DBManager(context);
                        DBManager.getInstance(context).deleteCollectPictures(imgs);
                        return API.status.success+"";
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return observable;
    }

    //从数据库获取所有收藏图片的信息
    public static Observable<ArrayList<NetImage>> getCollectImgs(final Context context){
        Observable<ArrayList<NetImage>> observable = Observable.just("").map(new Func1<String, ArrayList<NetImage>>() {
            @Override
            public ArrayList<NetImage> call(String s) {
               // DBManager manager = new DBManager(context);
                return DBManager.getInstance(context).queryHasCollectImgs();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return observable;
    }
    //从数据库获取所有下载图片的信息
    public static  Observable<ArrayList<DownloadImg>> getDownloadImgs(final Context context){
        Observable<ArrayList<DownloadImg>> observable = Observable.just("").map(new Func1<String, ArrayList<DownloadImg>>() {
            @Override
            public ArrayList<DownloadImg> call(String s) {
              //  DBManager manager = new DBManager(context);
                return DBManager.getInstance(context).queryHasDownloadImgs();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return  observable;
    }
}
