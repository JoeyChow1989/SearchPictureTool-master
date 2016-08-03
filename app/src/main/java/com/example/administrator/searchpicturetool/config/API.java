package com.example.administrator.searchpicturetool.config;

import com.example.administrator.searchpicturetool.util.Utils;

/**
 * Created by wenhuaijun on 2015/11/3 0003.
 */
public class API {

    public static final String imgPath= Utils.getSDPath()+"/DownloadPicture";
    //图片搜索接口
    public static final String baseUrl="http://pic.sogou.com";
    //笑话api
    public static final String LAIFU_JOY_IMAGE="http://api.laifudao.com/open/tupian.json";
    public class status{
        public static final int success=200;
        public static final int error=-1;
    }
}
