package com.example.administrator.searchpicturetool.model.bean;

/**
 * Created by wenhuaijun on 2016/1/31 0031.
 */
public class NetImageImpl extends NetImage{
    private String thumbUrl;
    private String largeUrl;
    private int thumb_width;
    private int thumb_height;

    public NetImageImpl() {
    }

    public NetImageImpl(String thumbUrl, String largeUrl, int thumb_width, int thumb_height) {
        this.thumbUrl = thumbUrl;
        this.largeUrl = largeUrl;
        this.thumb_width = thumb_width;
        this.thumb_height = thumb_height;
    }



    public void setLargeUrl(String largeUrl) {
        this.largeUrl = largeUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public void setThumb_width(int thumb_width) {
        this.thumb_width = thumb_width;
    }

    public void setThumb_height(int thumb_height) {
        this.thumb_height = thumb_height;
    }

    @Override
    public String getThumbImg() {
        return thumbUrl;
    }

    @Override
    public String getLargeImg() {
        return largeUrl;
    }

    @Override
    public int getWidth() {
        return thumb_width;
    }

    @Override
    public int getHeight() {
        return thumb_height;
    }
}
