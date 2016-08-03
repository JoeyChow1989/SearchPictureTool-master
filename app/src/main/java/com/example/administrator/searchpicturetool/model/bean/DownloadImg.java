package com.example.administrator.searchpicturetool.model.bean;

import java.io.Serializable;

/**
 * Created by wenhuaijun on 2016/1/31 0031.
 */
public class DownloadImg implements Serializable{
    //本地文件名
    private String name;
    private String largUrl;
    private int height;
    private int width;
    public DownloadImg(){

    }
    public DownloadImg(String fileName, String largUrl, int height, int width) {
        this.name = fileName;
        this.largUrl = largUrl;
        this.height = height;
        this.width = width;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLargUrl() {
        return largUrl;
    }

    public void setLargUrl(String largUrl) {
        this.largUrl = largUrl;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
