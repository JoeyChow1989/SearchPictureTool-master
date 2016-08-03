package com.example.administrator.searchpicturetool.model.bean;

import com.google.gson.Gson;

import cn.bmob.v3.BmobObject;

/**
 * Created by Wenhuaijun on 2016/5/11 0011.
 */
public class NewRecommendContent extends BmobObject{
    private float type;
    private String tip;
    private String imageUrl;
    private String title;
    private String content;
    private boolean justType;

    public float getType() {
        return type;
    }

    public void setType(float type) {
        this.type = type;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isJustType() {
        return justType;
    }

    public void setJustType(boolean justType) {
        this.justType = justType;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
