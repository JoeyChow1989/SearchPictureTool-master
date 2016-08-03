package com.example.administrator.searchpicturetool.model.bean;

/**
 * Created by wenhuaijun on 2016/2/7 0007.
 */
@Deprecated
public class RecommendContent extends RecommendTip{
    private String imgUrl1;
    private String imgUrl2;
    private String title1;
    private String title2;
    private String content1;
    private String content2;

    public RecommendContent(double type,String imgUrl1, String title1, String content1,String imgUrl2,String title2,String content2) {
        super(type);
        this.imgUrl1 = imgUrl1;
        this.title2 = title2;
        this.title1 = title1;
        this.imgUrl2 = imgUrl2;
        this.content1 = content1;
        this.content2 = content2;
    }

    public String getImgUrl1() {
        return imgUrl1;
    }

    public void setImgUrl1(String imgUrl1) {
        this.imgUrl1 = imgUrl1;
    }

    public String getImgUrl2() {
        return imgUrl2;
    }

    public void setImgUrl2(String imgUrl2) {
        this.imgUrl2 = imgUrl2;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }
}
