package com.example.administrator.searchpicturetool.config;

/**
 * Created by wenhuaijun on 2016/1/31 0031.
 */
public class MySql {
        public static final String DATABASE_NAME = "searchPicture.db";
        public static final String DownloadTable="download";
        public static final String CollectTable="collect";
        public static final String RecommendTable="recommend";
        public  static final int DATABASE_VERSION = 3;
        // 创建已下载图片表
    public static final String creatDownloadTable="create table if not exists "+ DownloadTable+
            "(id integer primary key autoincrement," +
            "fileName varchar," +
            "largeImgUrl varchar," +
            "height integer," +
            "width integer)";
        //创建已收藏图片表
    public static final String createCollectTable ="create table if not exists "+ CollectTable+
            "(id integer primary key autoincrement," +
            "smallImgUrl varchar," +
            "largeImgUrl varchar," +
            "height integer," +
            "width integer)";
        //创建推荐列表表
        public static final String createRecommendTable ="create table if not exists "+ RecommendTable+
                "(type float," +
                "tip varchar," +
                "imageUrl varchar," +
                "title varchar," +
                "content varchar," +
                "justType boolean)";

}
