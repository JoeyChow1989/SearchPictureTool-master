package com.example.administrator.searchpicturetool.model.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by wenhuaijun on 2016/2/7 0007.
 */
@Deprecated
public class RecommendTip extends BmobObject{
    private double type;
    private String tip;
    public RecommendTip(double type) {
        this.type =type;
    }

    public RecommendTip(double type,String tip) {
        this.tip = tip;
        this.type =type;
    }

    public double getType() {
        return type;
    }

    public void setType(double type) {
        this.type = type;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
