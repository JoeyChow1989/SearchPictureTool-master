package com.example.administrator.searchpicturetool.model;

import com.example.administrator.searchpicturetool.model.bean.DownloadImg;

import java.util.ArrayList;

/**
 * Created by wenhuaijun on 2016/2/1 0001.
 * 用于图片被选中模式
 */
public class DownloadImgSelected {
    public static ArrayList<DownloadImg> selectedImgs;

    public static ArrayList<DownloadImg> getSelectedImgs() {
        if(selectedImgs ==null){
            selectedImgs = new ArrayList<DownloadImg>();
        }
        return selectedImgs;
    }
    public static  void add(DownloadImg img){
        getSelectedImgs().add(img);
    }
    public static void remove(DownloadImg img){
        getSelectedImgs().remove(img);
    }
    public static int size() {
        return  getSelectedImgs().size();
    }
}
