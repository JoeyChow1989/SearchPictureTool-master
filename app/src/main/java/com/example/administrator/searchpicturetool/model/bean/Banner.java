package com.example.administrator.searchpicturetool.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by wenhuaijun on 2016/2/15 0015.
 */
@Deprecated
public class Banner extends BmobObject{
    private String introduce;
    private String searchTip;
    private String imageUrl;

    public String getSearchTip() {
        return searchTip;
    }

    public void setSearchTip(String searchTip) {
        this.searchTip = searchTip;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
