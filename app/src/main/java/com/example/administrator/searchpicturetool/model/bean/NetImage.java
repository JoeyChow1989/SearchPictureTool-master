package com.example.administrator.searchpicturetool.model.bean;

import java.io.Serializable;

/**
 * Created by wenhuaijun on 2015/11/2 0002.
 */
public abstract class NetImage implements Serializable {

    public abstract String getThumbImg();

    public abstract String getLargeImg();

    public abstract int getWidth();

    public abstract int getHeight();

}
