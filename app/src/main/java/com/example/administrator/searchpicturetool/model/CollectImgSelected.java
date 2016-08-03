package com.example.administrator.searchpicturetool.model;

import com.example.administrator.searchpicturetool.model.bean.DownloadImg;
import com.example.administrator.searchpicturetool.model.bean.NetImage;

import java.util.ArrayList;

/**
 * Created by wenhuaijun on 2016/2/2 0002.
 * 用于图片被选中模式
 */
public class CollectImgSelected {
    public static ArrayList<NetImage> selectedImgs;

    public static ArrayList<NetImage> getSelectedImgs() {
        if(selectedImgs ==null){
            selectedImgs = new ArrayList<NetImage>();
        }
        return selectedImgs;
    }
    public static  void add(NetImage img){
        getSelectedImgs().add(img);
    }
    public static void remove(NetImage img){
        getSelectedImgs().remove(img);
    }
    public static int size() {
        return getSelectedImgs().size();
    }
}
