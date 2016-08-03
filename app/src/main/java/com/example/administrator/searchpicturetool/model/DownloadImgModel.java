package com.example.administrator.searchpicturetool.model;

import com.example.administrator.searchpicturetool.config.API;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by wenhuaijun on 2015/11/12 0012.
 * 该Model已废除，已改用从数据库获取已下载的图片文件信息
 */

@Deprecated
public class DownloadImgModel {
    @Deprecated
    public static List<File> getImageListFromFile(){
        ArrayList<File> mArrayList = new ArrayList<>();
        File file = new File(API.imgPath);
        if (file.mkdir()){
            return mArrayList;
        }
        mArrayList = new ArrayList<File>(Arrays.asList(file.listFiles()));
        ArrayList<File> deleteFiles = new ArrayList<File>();
        for (File mFile :mArrayList){
            if(mFile.isDirectory()){
                deleteFiles.add(mFile);
            }
        }
        mArrayList.removeAll(deleteFiles);
        return mArrayList;
    }
    //Rxjava链式获取图片List
    public static Observable<List<File>> getImageListFromFile2(){
        return Observable.just(API.imgPath)

                .map(new Func1<String, File>() {

                    @Override
                    public File call(String imagesPath) {

                        return new File(imagesPath);
                    }
                })
                .map(new Func1<File, List<File>>() {
                    @Override
                    public List<File> call(File file) {
                        List<File> files = new ArrayList<File>();
                        if (file.mkdir()) {
                            return files;
                        }
                        files = new ArrayList<File>(Arrays.asList(file.listFiles()));
                        //移除图片文件夹里面的文件夹
                        ArrayList<File> deleteFiles = new ArrayList<File>();
                        for (File mFile : files) {
                            if (mFile.isDirectory()) {
                                deleteFiles.add(mFile);
                            }
                        }
                        files.remove(deleteFiles);
                        return files;
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
